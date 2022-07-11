package nl.saxion.tcpServer.dataTypes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WeatherInfo extends WeatherData {
    private String city;
    private double temp_max;
    private double temp_min;
    private String description;
    private Date date;

    public WeatherInfo(String city, String description, double temp_min,double temp_max,String dateStr) {
        this.city = city;
        this.description = description;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
        try {
            this.date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateStr);
        } catch (ParseException e) {
            date = new Date();
        }
        super.dataDate = new Date();
    }

    @Override
    public String toString() {
        return "Weather at city: \'" + city + "\'" +
                " on the time: " + new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ").format(date) +
                " is: \'" + description + "\'," +
                " temperature from " + temp_min + " to " + temp_max +
                '}';
    }
}
