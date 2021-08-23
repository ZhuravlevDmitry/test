package home_work_6;
/*
 *	Журавлев Дмитрий
 *	Д/З № 6
 */
import okhttp3.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class RequestYandexApi {

    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "api.weather.yandex.ru";
    private static final String FORECASTS = "forecast";
    private static final String VERSION = "v2";
    private static final String NAME_KEY = "X-Yandex-API-Key";
    private static final String API_KEY = "43c2ceed-9602-4588-b736-43d39319dd75";

    private static final String LAT = "lat";
    private static final String LON = "lon";
    private static final String EXTRA = "extra";
    private static final String LIMIT = "limit";
    private static final String HOURS = "hours";


//            "lat=<59.939099>\n" +
//            " & lon=<30.315877>\n" +
//            " & [lang=<ru_RU>]\n" +
//            " & [limit=<1>]\n" +
//            " & [hours=<false>]\n" +
//            " & [extra=<false>]";

    public static void main(String[] args) throws IOException {

        OkHttpClient okHttpClient = new OkHttpClient()
                .newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .followRedirects(true)
                .retryOnConnectionFailure(true)
                .build();

        // Выше мы как бы открыли Постман, теперь нам нужно вбить ЮРЛ с указанием протокола, адреса, раздела
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegments(VERSION)
                .addPathSegments(FORECASTS)
                .addQueryParameter(LAT, "59.939099")
                .addQueryParameter(LON, "30.315877")
                .addQueryParameter(EXTRA, "true")
                .addQueryParameter(HOURS, "true")
                .addQueryParameter(LIMIT, "5")
                .build();

        // Теперь необходимо сформировать запрос который будем выполнять,
        Request requestGet = new Request.Builder()
                .url(httpUrl)
                .addHeader(NAME_KEY, API_KEY)// Отдельно ключ и отдельно значение
                .build();

        // Теперь обращаемся к htpp клиенту чтобы сделать запрос, вызываем  execute в котором хранятся все
        // параметры ответа И ЗАПИСЫВАЕМ этот ответ в responseGet
        Response responseGet = okHttpClient.newCall(requestGet).execute();
        // Далее необходимо считать этот боди
        System.out.println(responseGet.code());
        System.out.println(responseGet.headers());
        System.out.println(responseGet.isSuccessful());
        String body = responseGet.body().string();
        System.out.println(body);
    }
}
// Вывод в консоль
//200
//Content-Type: application/json
//Date: Mon, 23 Aug 2021 18:50:30 GMT
//Transfer-Encoding: chunked
//Vary: Accept-Encoding
//X-Yandex-Req-Id: 1629744630803129-1214020428910462570400017-vla0-6161-3.fpz7tukmt665hnhl
//
//true
//{"now":1629744630,"now_dt":"2021-08-23T18:50:30.820994Z","info":{"n":true,"geoid":2,
// "url":"https://yandex.ru/pogoda/2?lat=59.939099\...

