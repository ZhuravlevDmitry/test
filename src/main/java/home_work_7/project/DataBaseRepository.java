package home_work_7.project;

import home_work_7.project.entity.Weather;

import java.io.Reader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DataBaseRepository {

    private String insertWeather = "insert into weather1 (city, date1, valueMinimum1, valueMaximum1, link) values (?, ?, ?, ?,?)";
    private String getWeather = "select * from weather1";
    private static final String DB_PATH = "jdbc:sqlite:geekbrain.db";
//подключаем драйвер к проекту, пишем код не привязываясь к конкретному методу, и будет выполняться сразу при старте программы
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public boolean saveWeatherToDataBase(Weather weather) throws SQLException {
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
            saveWeather.setString(1, weather.getCity());
            saveWeather.setString(2, weather.getDate1());
            saveWeather.setDouble(3, weather.getValueMinimum1());
            saveWeather.setDouble(4, weather.getValueMaximum1());
            saveWeather.setString(5, weather.getLink());
            return saveWeather.execute();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        throw new SQLException("Сохранение погоды в базу данных не выполнено!");
    }
// этот метод применим на 5 дней
//    public void saveWeatherToDataBase(List<Weather> weatherList) throws SQLException {
//        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
//            PreparedStatement saveWeather = connection.prepareStatement(insertWeather);
//            for (Weather weather : weatherList) {
//                saveWeather.setString(1, weather.getCity());
//                saveWeather.setString(2, weather.getLocalDate());
//                saveWeather.setDouble(3, weather.getTemperature());
//                saveWeather.addBatch();
//            }
//            saveWeather.executeBatch();
//        } catch (SQLException throwables) {
//            throwables.printStackTrace();
//        }
//    }


       //TODO: реализовать этот метод получения данных из таблицы погоды
//Публичный метод который возвращает список элементов погоды

    public List<Weather> getSavedToDBWeather() {
        List<Weather> weathers = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(DB_PATH)) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(getWeather);
            while (resultSet.next()) {
                System.out.print(resultSet.getInt("id"));
                System.out.print(" ");
                System.out.print(resultSet.getString("city"));
                System.out.print(" ");
                System.out.print(resultSet.getString("date1"));
                System.out.print(" ");
                System.out.print(resultSet.getDouble("valueMinimum1"));
                System.out.print(" ");
                System.out.print(resultSet.getDouble("valueMaximum1"));
                System.out.print(" ");
                System.out.print(resultSet.getString("link"));
                System.out.println(" ");
                weathers.add(new Weather(resultSet.getString("city"),
                        resultSet.getString("date1"),
                        resultSet.getDouble("valueMinimum1"),
                        resultSet.getDouble("valueMaximum1"),
                        resultSet.getString("link")));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return weathers;
    }

//    public static void main(String[] args) throws SQLException {
//        DataBaseRepository dataBaseRepository = new DataBaseRepository();
////        dataBaseRepository.saveWeatherToDataBase(new Weather("Москва", "12.12.12", 14,
////                21, "fff"));
//        System.out.println(dataBaseRepository.getSavedToDBWeather());
//    }
}