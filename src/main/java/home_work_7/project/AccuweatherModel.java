package home_work_7.project;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import home_work_7.project.entity.Weather;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class AccuweatherModel implements WeatherModel {
    //http://dataservice.accuweather.com/forecasts/v1/daily/1day/349727
    private static final String PROTOKOL = "https";
    private static final String BASE_HOST = "dataservice.accuweather.com";
    private static final String FORECASTS = "forecasts";
    private static final String VERSION = "v1";
    private static final String DAILY = "daily";
    private static final String ONE_DAY = "1day";
    private static final String FIVE_DAYS = "5day";
    private static final String API_KEY = "pXJd8MokcZCdrd2MsoGl2DBZAyCa0zvv";
    private static final String API_KEY_QUERY_PARAM = "apikey";
    private static final String LOCATIONS = "locations";
    private static final String CITIES = "cities";
    private static final String AUTOCOMPLETE = "autocomplete";

    private static final OkHttpClient okHttpClient = new OkHttpClient();
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private DataBaseRepository dataBaseRepository = new DataBaseRepository();

    public void getWeather(String selectedCity, Period period) throws IOException, SQLException {
        switch (period) {
            case NOW:
                HttpUrl httpUrl = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(ONE_DAY)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request = new Request.Builder()
                        .url(httpUrl)
                        .build();

                Response oneDayForecastResponse = okHttpClient.newCall(request).execute();

                String weatherResponse = oneDayForecastResponse.body().string();
                String date = objectMapper.readTree(weatherResponse).get("DailyForecasts").get(0).at("/Date").asText();
                date = date.split("T")[0];
                System.out.println(weatherResponse);
                String temperatureMinimum =objectMapper.readTree(weatherResponse).get("DailyForecasts")
                        .get(0).at("/Temperature").at("/Minimum").at("/Value").asText();
                String temperatureMaximum =objectMapper.readTree(weatherResponse).get("DailyForecasts")
                        .get(0).at("/Temperature").at("/Maximum").at("/Value").asText();
                String link =objectMapper.readTree(weatherResponse).get("DailyForecasts")
                        .get(0).at("/Link").asText();

                double maximum  = Double.parseDouble(temperatureMaximum)*0.3778;
                int valueMaximum = (int) Math.round(maximum);
                double minimum  = Double.parseDouble(temperatureMinimum)*0.3778;
                int valueMinimum = (int) Math.round(minimum);

                System.out.println("В городе - " + selectedCity.toUpperCase() + ", на дату - "  + date + ", ожидается:");
                System.out.println("Минимальная температура - " + valueMinimum + ", градуса(ов) Цельсия");
                System.out.println("Максимальная температура - " + valueMaximum + ", градусоа(ов) Цельсия");
                System.out.println("Подробый прогноз доступен по ссылке - " + link);
                //TODO: сделать человекочитаемый вывод погоды. Выбрать параметры для вывода на свое усмотрение
                //Например: Погода в городе Москва - 5 градусов по цельсию Expect showers late Monday night

                dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity.toUpperCase(), date,
                 valueMinimum, valueMaximum, link));

                break;
            case FIVE_DAYS:
                HttpUrl httpUrl1 = new HttpUrl.Builder()
                        .scheme(PROTOKOL)
                        .host(BASE_HOST)
                        .addPathSegment(FORECASTS)
                        .addPathSegment(VERSION)
                        .addPathSegment(DAILY)
                        .addPathSegment(FIVE_DAYS)
                        .addPathSegment(detectCityKey(selectedCity))
                        .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                        .build();

                Request request1 = new Request.Builder()
                        .url(httpUrl1)
                        .build();

                Response fiveDaysForecastResponse = okHttpClient.newCall(request1).execute();
                String weatherResponseOnFiveDays = fiveDaysForecastResponse.body().string();
                System.out.println(weatherResponseOnFiveDays);


                for (int i = 0; i < 5; i++) {

                    String dateOneDay = objectMapper.readTree(weatherResponseOnFiveDays).get("DailyForecasts").get(i).at("/Date").asText();
                    dateOneDay = dateOneDay.split("T")[0];

                    String temperatureMinimum1 =objectMapper.readTree(weatherResponseOnFiveDays).get("DailyForecasts")
                            .get(i).at("/Temperature").at("/Minimum").at("/Value").asText();
                    String temperatureMaximum1 =objectMapper.readTree(weatherResponseOnFiveDays).get("DailyForecasts")
                            .get(i).at("/Temperature").at("/Maximum").at("/Value").asText();
                    String link1 =objectMapper.readTree(weatherResponseOnFiveDays).get("DailyForecasts")
                            .get(i).at("/Link").asText();

                    double maximum1 = Double.parseDouble(temperatureMaximum1)*0.3778;
                    int valueMaximum1 = (int) Math.round(maximum1);
                    double minimum1  = Double.parseDouble(temperatureMinimum1)*0.3778;
                    int valueMinimum1 = (int) Math.round(minimum1);

                    System.out.println("В городе - " + selectedCity.toUpperCase() + ", на дату - "  + dateOneDay + ", ожидается:");
                    System.out.println("Минимальная температура - " + valueMinimum1 + ", градуса(ов) Цельсия");
                    System.out.println("Максимальная температура - " + valueMaximum1 + ", градусоа(ов) Цельсия");
                    System.out.println("Подробый прогноз доступен по ссылке - " + link1);

                    dataBaseRepository.saveWeatherToDataBase(new Weather(selectedCity.toUpperCase(), dateOneDay,
                            valueMinimum1, valueMaximum1, link1));
                }
                //TODO*: реализовать вывод погоды на 5 дней
                break;
        }
    }

    @Override
    public List<Weather> getSavedToDBWeather() {
        return dataBaseRepository.getSavedToDBWeather();
    }

    private String detectCityKey(String selectCity) throws IOException {
        //http://dataservice.accuweather.com/locations/v1/cities/autocomplete
        HttpUrl httpUrl = new HttpUrl.Builder()
                .scheme(PROTOKOL)
                .host(BASE_HOST)
                .addPathSegment(LOCATIONS)
                .addPathSegment(VERSION)
                .addPathSegment(CITIES)
                .addPathSegment(AUTOCOMPLETE)
                .addQueryParameter(API_KEY_QUERY_PARAM, API_KEY)
                .addQueryParameter("q", selectCity)
                .build();

        Request request = new Request.Builder()
                .url(httpUrl)
                .get()
                .addHeader("accept", "application/json")
                .build();

        Response response = okHttpClient.newCall(request).execute();
        String responseString = response.body().string();

        String cityKey = objectMapper.readTree(responseString).get(0).at("/Key").asText();
        return cityKey;
    }
}
