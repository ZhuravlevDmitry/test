package home_work_7.project;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class UserInterfaceView {
    private Controller controller = new Controller();

    public void runInterface() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Введите 1 для получения погоды на сегодня;" +
                    "Введите 5 для прогноза на 5 дней; Введите 2 для получения данных из базы; Для выхода введите 0:");

            String command = scanner.nextLine();

            //TODO* Сделать метод валидации пользовательского ввода

            if (command.equals("0")) break;

            if (!command.equals("5") && !command.equals("1") && !command.equals("2")) {
                System.out.println("Некорректный ввод");
                continue;
            }
            if (command.equals("5") || command.equals("1")) {
                System.out.println("Введите название города: ");
                String city = scanner.nextLine();

                try {
                    controller.getWeather(command, city);
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
            }
            if (command.equals("2"))
                try {
                    controller.getWeather(command, " ");
                } catch (IOException | SQLException e) {
                    e.printStackTrace();
                }
        }
    }
}

