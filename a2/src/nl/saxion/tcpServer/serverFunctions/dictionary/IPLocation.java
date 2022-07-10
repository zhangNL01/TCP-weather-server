package nl.saxion.tcpServer.serverFunctions.dictionary;

import nl.saxion.tcpServer.serverFunctions.MenuItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.URL;

public class IPLocation implements MenuItem {
    @Override
    public String getIndex() {
        return "IPLOCATION";
    }

    @Override
    public String getDescription() {
        return "5 - get location by ip";
    }

    @Override
    public String execute(String keyWord) {
        JSONObject jsonObject;
        var result = new StringBuilder();
        try {
            var tempBuilding = new StringBuilder();
            var apiUrl = "http://ipwhois.app/json/" + keyWord;
            var bufferReader = new BufferedInputStream(new URL(apiUrl).openStream());
            int cp;
            while ((cp = bufferReader.read()) != -1) {
                tempBuilding.append((char) cp);
            }
            jsonObject = new JSONObject(tempBuilding.toString());
            result.append("IP: " + jsonObject.getString("ip"));
            result.append(", is from: " + jsonObject.getString("city"));
            result.append(", in " + jsonObject.getString("country"));
        } catch (IOException e) {
            System.out.println("An Error occurred during getting data from internet");
            e.printStackTrace();
        }catch (JSONException e){
            return "Invalid input, please check";
        }
        return result.toString();
    }
}
