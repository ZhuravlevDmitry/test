
/*
 *	Журавлев Дмитрий
 *	Д/З № 3
 */

package home_work_3;

//Написать метод, который меняет два элемента массива местами (массив может быть любого ссылочного типа);

import java.util.Arrays;

public class NumberBox<T extends Object> {
    private T[] array;

    public T[] getArray() {
        return array;
    }

    public void setArray(T[] array) {
        this.array = array;
    }

    @Override
    public String toString() {
        return "NumberBox{" +
                "array=" + Arrays.toString(array) +
                '}';
    }

    public NumberBox(T... array) {
        this.array = array;
    }

    public static <U> void getChangeElements(NumberBox<U> numberBox) {
        U x;
        x = numberBox.getArray()[0];
        numberBox.getArray()[0] = numberBox.getArray()[1];
        numberBox.getArray()[1] = x;
    }

    public static void main(String[] args) {
        NumberBox<Integer> integerNumberBox1 = new NumberBox<>(2, 3, 4, 5, 6, 7, 2);
        NumberBox<String> integerNumberBox2 = new NumberBox<>("2", "3,4,5,6,7,2");

        getChangeElements(integerNumberBox2);
        System.out.println(integerNumberBox2);

        getChangeElements(integerNumberBox1);
        System.out.println(integerNumberBox1);
    }
}
//    Вывод из консоли:
//    NumberBox{array=[3,4,5,6,7,2, 2]}
//    NumberBox{array=[3, 2, 4, 5, 6, 7, 2]}
//
//    Process finished with exit code 0
