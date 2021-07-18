/*
 *	Журавлев Дмитрий
 *	Д/З № 2
 */

package home_work_2;

import java.util.Arrays;

class MyArraySizeException extends Exception {
    public MyArraySizeException(String message) {
    }
}

public class Main {

    public static void main(String[] args) {
// Создаём массивы
        String[][] myArray = {
                {"One", "Two", "Three", "Four"},
                {"Five", "Six", "Seven", "Eight"},
                {"Nine", "Ten", "Eleven", "Twelve"},
                {"Thirteen", "Fourteen", "Fifteen", "Sixteen"}
        };

        String[][] myArray1 = {
                {"One", "Two", "Fourteen"},
                {"Five", "Six"}
        };

        String[][] myArray2 = {
                {"One", "Two", "Three", "Four"},
                {"Five", "Six", "Seven", "Eight"},
                {"Nine", "Ten", "Eleven", "Twelve"},
                {"Thirteen", "Fourteen", "Fifteen", "Sixteen", "Sixteen"},
                {"Thirteen", "Fourteen", "Fifteen", "Sixteen"},
                {"Thirteen", "Fourteen", "Fifteen", "Sixteen"}
        };

        String[][] myArray3 = {
                {"1", "2", "3", "4"},
                {"4", "5", "6", "7"},
                {"8", "9", "10", "11"},
                {"12", "13", "14", "16"}
        };

        //Вызываем метод Метод

        Metod(myArray);
        Metod(myArray1);
        Metod(myArray2);
        Metod(myArray3);
    }

    public static void Metod(String[][] Array) {
        int i, j;
        System.out.println("\nОбрабатываем массив:" + Arrays.deepToString(Array));
// проверяем размер массива
        try {
            if (Array[0].length != 4 || Array.length != 4)
//Если не совпадает создаём  исключение MyArraySizeException
                throw new MyArraySizeException("Размерность массива не 4х4! " + " В массиве - " + Array.length + " строк");
        }
        // Если поймали исключения MyArraySizeException, то выводим предупреждение и выходим из Метода
        catch (MyArraySizeException e) {
            System.out.println("Размерность массива не 4х4 - MyArraySizeException" + " В массиве - " + Array.length + " строк");
            return;
        }
// Преобразуем массив
        Integer ArrayModify[][] = new Integer[4][4];

        int Summ = 0;
        int flag = 0;
        outerloop:
        for (i = 0; i <= 3; i++) {
            for (j = 0; j <= 3; j++) {
                //Пробуем преобразовать значения, если получилось - суммируем

                try {
                    ArrayModify[i][j] = Integer.parseInt(Array[i][j]);
                    Summ = Summ + ArrayModify[i][j];

                    // Если поймали исключения NumberFormatException, то выводим предупреждение и прерываем цыкл (можно return и без flag)
                } catch (NumberFormatException MyArrayDataException) {
                    System.out.println("В массиве присутствует некорректное значение - MyArrayDataException:  " + Array[i][j]
                            + " - в ячейке [" + i + "][" + j + "]");
                    flag = 1;
                    break outerloop;
                }
            }
        }
        if (flag == 0)
            System.out.println("Сумма:" + Summ);
    }

}


/* Вывод в консоль:


Обрабатываем массив:[[One, Two, Three, Four], [Five, Six, Seven, Eight], [Nine, Ten, Eleven, Twelve], [Thirteen, Fourteen, Fifteen, Sixteen]]
В массиве присутствует некорректное значение - MyArrayDataException:  One - в ячейке [0][0]

Обрабатываем массив:[[One, Two, Fourteen], [Five, Six]]
Размерность массива не 4х4 - MyArraySizeException В массиве - 2 строк

Обрабатываем массив:[[One, Two, Three, Four], [Five, Six, Seven, Eight], [Nine, Ten, Eleven, Twelve], [Thirteen, Fourteen, Fifteen, Sixteen, Sixteen], [Thirteen, Fourteen, Fifteen, Sixteen], [Thirteen, Fourteen, Fifteen, Sixteen]]
Размерность массива не 4х4 - MyArraySizeException В массиве - 6 строк

Обрабатываем массив:[[1, 2, 3, 4], [4, 5, 6, 7], [8, 9, 10, 11], [12, 13, 14, 16]]
Сумма:125

Process finished with exit code 0
*/
