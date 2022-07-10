package nl.saxion.tcpServer.serverFunctions;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;

public class DataGetter implements Runnable {
    protected String apiKey;
    protected boolean ready;
    protected boolean errored;
    protected String api;
    protected String keyWord;
    protected String condition;
    JSONObject jsonObject;

    public boolean isReady() {
        return this.ready;
    }

    public boolean hasError() {
        return this.errored;
    }

    public JSONObject getData() {
        return this.jsonObject;
    }

    @Override
    public void run() {
        var result = new StringBuilder();
        try {
            var apiUrl = api + keyWord + condition + apiKey;
            var bufferReader = new BufferedInputStream(new URL(apiUrl).openStream());
            int cp;
            while ((cp = bufferReader.read()) != -1) {
                result.append((char) cp);
            }
            jsonObject = new JSONObject(result.toString());
            ready = true;
        } catch (FileNotFoundException e) {
            this.errored = true;
        } catch (IOException e) {
            System.out.println("An Error occurred during getting data from internet");
            e.printStackTrace();
        }
    }
}
