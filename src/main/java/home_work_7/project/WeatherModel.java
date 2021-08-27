package home_work_7.project;


import java.io.IOException;
import java.util.List;

public interface  WeatherModel {
    void getWeather(String selectedCity, Period period) throws IOException;

    //ublic List<Weather> getSavedToDBWeather();
}
