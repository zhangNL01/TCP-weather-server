package nl.saxion.tcpServer.serverFunctions.time;

import nl.saxion.tcpServer.serverFunctions.MenuItem;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class TimeByIP implements MenuItem {
    @Override
    public String getIndex() {
        return "TIME";
    }

    @Override
    public String getDescription() {
        return "1 - get current time by IP address";
    }

    @Override
    public String execute(String keyWord) {
        JSONObject jsonObject;
        var result = new StringBuilder();
        try {
            var tempBuilding = new StringBuilder();
            var apiUrl = "http://worldtimeapi.org/api/ip";
            var bufferReader = new BufferedInputStream(new URL(apiUrl).openStream());
            int cp;
            while ((cp = bufferReader.read()) != -1) {
                tempBuilding.append((char) cp);
            }
            jsonObject = new JSONObject(tempBuilding.toString());
            result.append("IP: " + jsonObject.getString("client_ip"));
            result.append(", date: " + jsonObject.getString("datetime"));
        } catch (IOException e) {
            System.out.println("An Error occurred during getting data from internet");
            e.printStackTrace();
        }
        return result.toString();
    }
}
