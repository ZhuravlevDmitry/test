package home_work_7.project;

import java.io.IOException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите 1 для получения погоды на сегодня;" +
                    "Введите 5 для прогноза на 5 дней;  Для выхода введите 0:");
            /*Введите 2 для получения данных из базы;*/
            String command = scanner.nextLine();

            //TODO* Сделать метод валидации пользовательского ввода

            if (command.equals("0")) break;
            if (!command.equals("5") && !command.equals("1")) {
                System.out.println("Некорректный ввод");
//                command = scanner.nextLine();
                continue;
            }

            System.out.println("Введите название города: ");
            String city = scanner.nextLine();


            try {
                controller.getWeather(command, city);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
