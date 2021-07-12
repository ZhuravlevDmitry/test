import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

//создаём класс
public class Main {
    // создаем точку входа в программу метод мэйн
    public static void main(String[] args) throws IOException {
        // воспользуемся готовым кодом с сайта где есть туториал - обучение, учебное пособие.
        // Это пошаговое (поэтапное) объяснение какого-либо процесса, инструкция по выполнению
        // чего-либо.
        OkHttpClient okHttpClient = new OkHttpClient();
        //для того чтобы описать какой реквест запрос мы хотим сделать к Апи
        // создаём экземпляр класса реквест  и указываем в нем юрл
        Request request = new Request.Builder()
                .url("https://icanhazdadjoke.com/")
                .header("Accept", "application/json")
                .build();
        // создаём экземпляр класса респонсе и запишем туда ответ на наш запрос
        Response response = okHttpClient.newCall(request).execute();
        System.out.println(response.body().string());
    }
}
