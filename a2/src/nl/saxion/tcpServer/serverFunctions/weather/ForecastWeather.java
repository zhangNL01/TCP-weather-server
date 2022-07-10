package nl.saxion.tcpServer.serverFunctions.weather;

import nl.saxion.tcpServer.dataTypes.WeatherForecast;
import nl.saxion.tcpServer.serverFunctions.MenuItem;
import org.json.JSONObject;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.util.HashMap;

public class ForecastWeather extends WeatherMenuItem implements MenuItem {
    private final String api = "http://api.openweathermap.org/data/2.5/forecast?q=";

    public ForecastWeather() {
        super.cache = new HashMap<>();
    }

    @Override
    public String getIndex() {
        return "WEATHERFORECAST";
    }

    @Override
    public String getDescription() {
        return "3 - Get forecast weathers by city name";
    }

    WeatherForecast pullWeather(String city) throws FileNotFoundException, SocketException {
        var weatherGetter = new WeatherDataGetter(api, city);
        weatherGetter.run();

        if (getFinished(weatherGetter)) {
            System.out.println("Pull data succeed!");
        }

        return translateToList(weatherGetter.getData(), city);
    }

    private WeatherForecast translateToList(JSONObject pulledData, String city) {
        var jsonArray = pulledData.getJSONArray("list");
        var result = new WeatherForecast(city);
        for(int i = 0; i < jsonArray.length(); i++){
            addAForecast(jsonArray.getJSONObject(i),result);
        }
        return result;
    }

    private void addAForecast(JSONObject oneGroupData, WeatherForecast forecastList) {
        forecastList.addAWeather(oneGroupData.getJSONArray("weather").getJSONObject(0).getString("description"),
                oneGroupData.getJSONObject("main").getDouble("temp_min"),
                oneGroupData.getJSONObject("main").getDouble("temp_max"),
                oneGroupData.getString("dt_txt")
        );
    }
}
