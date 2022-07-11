package nl.saxion.tcpServer.dataTypes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WeatherForecast extends WeatherData {
    private String city;
    private List<WeatherInfo> forecasts;

    public WeatherForecast(String city){
        this.city = city;
        this.forecasts = new ArrayList<>();
        super.dataDate = new Date();
    }

    public void addAWeather(String description, double temp_min, double temp_max, String dateStr){
        this.forecasts.add(new WeatherInfo(this.city,description,temp_min,temp_max,dateStr));
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        result.append("The forecasts are:/nextLine/");
        forecasts.forEach(weatherInfo -> result.append(weatherInfo + "/nextLine/"));
        return result.toString();
    }
}
