/*
 *	Журавлев Дмитрий
 *	Д/З № 3
 */
package home_work_3;

//Даны классы: Fruit, Apple extends Fruit, Orange extends Fruit.
//
// Класс Box, в который можно складывать фрукты. Коробки условно сортируются по типу фрукта, поэтому в одну
// коробку нельзя сложить и яблоки, и апельсины;
// Сделать метод getWeight(), который высчитывает вес коробки, зная вес одного фрукта и их количество: вес
// яблока – 1.0f, апельсина – 1.5f (единицы измерения не важны);
// Внутри класса Box сделать метод compare(), который позволяет сравнить текущую коробку с той, которую
// подадут в compare() в качестве параметра. true – если их массы равны, false в противоположном случае.
// Можно сравнивать коробки с яблоками и апельсинами;
// Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую. Помним про сортировку
// фруктов: нельзя яблоки высыпать в коробку с апельсинами. Соответственно, в текущей коробке фруктов
// не остается,а в другую перекидываются объекты, которые были в первой;
// Не забываем про метод добавления фрукта в коробку.

public class Box<T extends Fruit> implements Comparable {
    private T fruit;
    private Integer quantity;
    private Double ves;

    @Override
    public String toString() {
        return "Box{" +
                "fruit=" + fruit +
                ", quantity=" + quantity +
                ", ves=" + ves +
                '}';
    }

    public Box(T fruit, Integer quantity, Double ves) {
        this.fruit = fruit;
        this.quantity = quantity;
        this.ves = ves;
    }

    public void getVes(Double ves) {
    }

    public void setVes(Double ves) {
        this.ves = ves;
    }

    public T getFruit() {
        return fruit;
    }

    public void setFruit(T fruit) {
        this.fruit = fruit;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    // Метод кладёт фрукты в коробку и высчитывает её вес
    public void putToBox(Integer quantity) {
        setQuantity(getQuantity() + quantity);
        setVes(getQuantity() * getFruit().getWeight());
    }

    @Override
    public int compareTo(Object o) {

        if ((this.ves - ((Box) o).ves) < 0)
            return -1;
        else if ((this.ves - ((Box) o).ves) > 0)
            return 1;
        else
            return 0;
    }

    public static String printCompare(int i) {
        if (i == -1) return "В первой коробке вес меньше чем во второй";
        else if (i == 1) return "В первой коробке вес больше чем во второй";
        else
            return "Коробки равны";
    }

    public <Fruit> void moveFruitsToNewBox(Box<?> box) {
        if (getFruit().getWeight() != box.getFruit().getWeight()) {
            System.out.println("В коробках разные фрукты! Пересыпать низя:)!");

        } else {
            System.out.println("В коробках одинаковые фрукты! Пересыпаем!");

            setQuantity(getQuantity() + box.getQuantity());
            setVes(getQuantity() * getFruit().getWeight());

            box.setQuantity(0);
            box.setVes(0.0);
        }
    }

    public static void main(String[] args) {

        //Создаём яблоко и задаём вес,
        // Создаём апельсин и задаём вес,
        // Создаём пустые корробки
        // Кладём колличество
        Apple apple = new Apple(1.0);

        Box<Apple> fruitBox1;
        fruitBox1 = new Box<>(apple, 0, 0.0);

        fruitBox1.putToBox(12);
        fruitBox1.putToBox(12);
        System.out.println(fruitBox1);

        Orange orange = new Orange(1.5);
        Box<Orange> fruitBox2 = new Box<>(orange, 0, 0.0);
        fruitBox2.putToBox(15);
        System.out.println(fruitBox2);

        System.out.println(printCompare(fruitBox1.compareTo(fruitBox2)));

        Box<Orange> fruitBox3 = new Box<>(orange, 0, 0.0);
        fruitBox3.putToBox(5);
        System.out.println(fruitBox2);
        System.out.println(fruitBox3);

        fruitBox3.moveFruitsToNewBox(fruitBox2);
        System.out.println(fruitBox2);
        System.out.println(fruitBox3);
    }
}
//    Вывод в консоль:
//Box{fruit=Apple{weight=1.0}, quantity=24, ves=24.0}
//Box{fruit=Orange{weight=1.5}, quantity=15, ves=22.5}

//В первой коробке вес больше чем во второй

//Box{fruit=Orange{weight=1.5}, quantity=15, ves=22.5}
//Box{fruit=Orange{weight=1.5}, quantity=5, ves=7.5}
//В коробках одинаковые фрукты! Пересыпаем!

//Box{fruit=Orange{weight=1.5}, quantity=0, ves=0.0}
//Box{fruit=Orange{weight=1.5}, quantity=20, ves=30.0}


