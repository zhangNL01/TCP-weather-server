package nl.saxion.tcpServer.serverFunctions.weather;

import nl.saxion.tcpServer.dataTypes.WeatherData;
import nl.saxion.tcpServer.serverFunctions.MenuItem;

import java.io.FileNotFoundException;
import java.net.SocketException;
import java.util.HashMap;

public abstract class WeatherMenuItem implements MenuItem {
    HashMap<String, WeatherData> cache;
    final int maxWaitTime = 2000;
    final int waitInterval = 500;

    public WeatherMenuItem() {
        this.cache = new HashMap<>();
    }

    @Override
    public String getIndex() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String execute(String city) {
        if (cache.containsKey(city) && !cache.get(city).needRefresh())
            return cache.get(city).toString();
        else {
            try {
                cache.put(city, pullWeather(city));
                return cache.get(city).toString();
            } catch (SocketException e) {
                return "Timeout on pulling weather data. Please check your internet.";
            } catch (FileNotFoundException e) {
                return "City not found! please check the spelling.";
            }
        }
    }

    abstract WeatherData pullWeather(String city) throws FileNotFoundException, SocketException;

    boolean getFinished(WeatherDataGetter weatherGetter) throws SocketException, FileNotFoundException {
        var waitTime = maxWaitTime;
        if (!weatherGetter.hasError()) {
            while (!weatherGetter.isReady() && waitTime != 0) {

                try {
                    Thread.sleep(waitInterval);
                    waitTime -= waitInterval;
                    System.out.println("Still waiting for response");
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        } else {
            throw new FileNotFoundException("City not found");
        }
        if (!weatherGetter.isReady()) throw new SocketException("Connection broken");
        return true;
    }
}
