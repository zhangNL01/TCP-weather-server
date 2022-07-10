package nl.saxion.tcpServer.serverFunctions.weather;

import nl.saxion.tcpServer.serverFunctions.DataGetter;

public class WeatherDataGetter extends DataGetter implements Runnable {
    public WeatherDataGetter(String api, String city) {
        super.api = api;
        super.keyWord = city;
        super.condition = "";
        super.ready = false;
        super.errored = false;
        super.apiKey = "&appid=382824d2ce5a1c36c2ac11f00e0bfaa7";
    }
}
