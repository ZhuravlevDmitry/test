/*
 *	Журавлев Дмитрий
 *	Д/З № 4
 */

package home_work_4;
// Создать массив с набором слов (10-20 слов, должны встречаться повторяющиеся).
// Найти и вывести список уникальных слов, из которых состоит массив (дубликаты не считаем).
// Посчитать, сколько раз встречается каждое слово.

//import java.lang.invoke.DelegatingMethodHandle$Holder;

import java.util.*;

public class Book {
    public static void main(String[] args) {

        ArrayList<String> stringArrayList = new ArrayList<>(20);
        stringArrayList.add("Перо");
        stringArrayList.add("домино");
        stringArrayList.add("Перо");
        stringArrayList.add("э");
        stringArrayList.add("я");
        stringArrayList.add("ЯЯЯ");
        stringArrayList.add("Перо");
        System.out.println(stringArrayList);

        HashMap<String, Integer> book = new HashMap<>();

        for (int i = 0; i < stringArrayList.size(); i++) {
                // проверка есть ли значение ключа в нашем случае "слово" в HashMap
                if (!book.containsKey(stringArrayList.get(i))) {
                    // если нет то кладём и присваиваем значение 1
                    book.put(stringArrayList.get(i), 1);
                    // иначе увеличиваем значение на единицу
            } else book.put(stringArrayList.get(i), book.get(stringArrayList.get(i)) + 1);
        }

        System.out.println(book); // Интересно то, что по умолчанию в HashMap есть мне не понятная сортировка?

// Если не выводить колличество повторений, то можно передать уникальные слова (вариант 2)

        Set<String> set = new HashSet<>(stringArrayList);
        stringArrayList.clear();
        stringArrayList.addAll(set);
        System.out.println(stringArrayList);
    }
}
// Вывод в консоль:
// [Перо, домино, Перо, э, я, ЯЯЯ, Перо]
//        {домино=1, Перо=3, э=1, я=1, ЯЯЯ=1}
//        [домино, Перо, э, я, ЯЯЯ]