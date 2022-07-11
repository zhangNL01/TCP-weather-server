package nl.saxion.tcpServer.serverFunctions.weather;

import nl.saxion.tcpServer.dataTypes.WeatherInfo;
import nl.saxion.tcpServer.serverFunctions.MenuItem;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class CurrentWeather extends WeatherMenuItem implements MenuItem {
    private final String api = "http://api.openweathermap.org/data/2.5/weather?q=";

    public CurrentWeather() {
        super.cache = new HashMap<>();
    }

    @Override
    public String getIndex() {
        return "TODAYWEATHER";
    }

    @Override
    public String getDescription() {
        return "2 - Get current weather by city name";
    }

    WeatherInfo pullWeather(String city) throws SocketException, FileNotFoundException {
        var weatherGetter = new WeatherDataGetter(api, city);
        weatherGetter.run();

        if (getFinished(weatherGetter)) {
            System.out.println("Pull data succeed!");
        }

        return translateToWeather(weatherGetter.getData());
    }

    private WeatherInfo translateToWeather(JSONObject pulledData) {
        return new WeatherInfo(pulledData.getString("name"),
                pulledData.getJSONArray("weather").getJSONObject(0).getString("description"),
                pulledData.getJSONObject("main").getDouble("temp_min"),
                pulledData.getJSONObject("main").getDouble("temp_max"),
                new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss ").format(new Date())
                );

    }
}
