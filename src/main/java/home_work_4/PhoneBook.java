/*
 *	Журавлев Дмитрий
 *	Д/З № 4
 */

package home_work_4;

// Написать простой класс Телефонный Справочник, который хранит в себе список фамилий и телефонных номеров.
// В этот телефонный справочник с помощью метода add() можно добавлять записи, а с помощью метода get() искать
// номер телефона по фамилии.Следует учесть, что под одной фамилией может быть несколько телефонов
// (в случае однофамильцев), тогда при запросе такой фамилии должны выводиться все телефоны.
// Желательно не добавлять лишний функционал (дополнительные поля (имя, отчество, адрес),
// взаимодействие с пользователем через консоль и т.д). Консоль использовать только для вывода результатов проверки
// телефонного справочника.

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class PhoneBook {

    @Override
    public String toString() {
        return "PhoneBook{" +
                "phoneBook=" + phoneBook +
                '}';
    }

    // Создаём HashMap передаём в качестве ключа ФИО в качестве значения массив номеров
    public HashMap<String, ArrayList<String>> phoneBook = new HashMap<>();

    public void add(String name, String number) {
        // Создаём переменную в которую либо достаём номера по фамилии либо создаём новый массив для номеров новой фамилии
        ArrayList<String> telefon = phoneBook.getOrDefault(name, new ArrayList<>());

        if (phoneBook.containsKey(name) && telefon.contains(number)) {
            System.out.println("Попытка добавить существующие значение! Отклонено!");
            return;
        }
        //Добавляю в существующий или новый массив новый телефон
        telefon.add(number);
        //Дополняю HashMap
        phoneBook.put(name, telefon);
    }

    // Метод возвращает номера для ФИО
    public Serializable get(String name) {
        String a = "В справочнике отсутствует фамилия" + " - " + name;
        if (phoneBook.get(name)==null){
           // System.out.println("В справочнике отсутствует фамилия - " + name);
           return a; 
        }
        return phoneBook.get(name);
    }

    public static void main(String[] args) {

        // Создаём эллемент класса
        PhoneBook phoneBook = new PhoneBook();
        //Вызываем метод. Добавляем пару ключ - значение и если ФИО уже существует то добавляем только телефон
        phoneBook.add("Иванов", "9787884");
        phoneBook.add("Петров", "9787883");
        phoneBook.add("Петров", "9787883");
        phoneBook.add("Иванов", "97834");

        System.out.println(phoneBook);
        System.out.println(phoneBook.get("Иванов"));
        System.out.println(phoneBook.get("MMM"));
    }
}

//    Вывод в консоль:
//        Попытка добавить существующие значение! Отклонено!
//        PhoneBook{phoneBook={Иванов=[9787884, 97834], Петров=[9787883]}}
//        [9787884, 97834]
//        В справочнике отсутствует фамилия - MMM
