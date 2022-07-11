package nl.saxion.tcpServer.dataTypes;

import java.util.Date;

public class WeatherData {
    Date dataDate;
    public boolean needRefresh() {
        return (dataDate.getTime() - new Date().getTime()) > (60 * 60 * 1000);
    }
}
