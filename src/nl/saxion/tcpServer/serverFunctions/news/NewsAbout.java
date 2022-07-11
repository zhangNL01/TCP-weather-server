package nl.saxion.tcpServer.serverFunctions.news;

import nl.saxion.tcpServer.dataTypes.News;
import nl.saxion.tcpServer.dataTypes.NewsList;
import nl.saxion.tcpServer.serverFunctions.MenuItem;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class NewsAbout implements MenuItem {
    HashMap<String, NewsList> cache;
    final int maxWaitTime = 2000;
    final int waitInterval = 500;

    public NewsAbout() {
        this.cache = new HashMap<>();
    }

    @Override
    public String getIndex() {
        return "NEWS";
    }

    @Override
    public String getDescription() {
        return "4 - Get related news'titles by a keyword";
    }

    @Override
    public String execute(String keyWord) {
        var newsGetter = new NewsGetter(keyWord);
        newsGetter.run();
        if (getFinished(newsGetter)) {
            System.out.println("Pull data succeed!");
        }else{
            System.out.println("Timeout.");
            return "Timeout while downloading newses, please check connecting status.";
        }

        return translateToList(newsGetter.getData(), keyWord).toString();
    }

    private NewsList translateToList(JSONObject pulledData, String city) {
        var jsonArray = pulledData.getJSONArray("articles");
        var result = new NewsList(city);
        for (int i = 0; i < jsonArray.length(); i++) {
            addANews(jsonArray.getJSONObject(i), result);
        }
        return result;
    }

    private void addANews(JSONObject object, NewsList newsList) {
        String author;
        try{
            author = object.getString("author");
        }catch (JSONException e){
            author = "unknow author";
        }
        var aNews = new News(
                object.getJSONObject("source").getString("name"),
                author,
                object.getString("title")
                );
        newsList.addNews(aNews);
    }

    boolean getFinished(NewsGetter newsGetter) {
        var waitTime = maxWaitTime;
        if (!newsGetter.hasError()) {
            while (!newsGetter.isReady() && waitTime != 0) {
                try {
                    Thread.sleep(waitInterval);
                    waitTime -= waitInterval;
                    System.out.println("Downloading news...");
                } catch (InterruptedException ie) {
                    System.out.println(ie);
                }
            }
        }
        if (!newsGetter.isReady()) return false;
        return true;
    }
}
