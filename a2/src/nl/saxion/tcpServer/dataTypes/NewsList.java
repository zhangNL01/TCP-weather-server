package nl.saxion.tcpServer.dataTypes;

import java.util.ArrayList;
import java.util.List;

public class NewsList {
    private String keyWord;
    private List<News> newsList;

    public NewsList(String word){
        this.keyWord = word;
        this.newsList = new ArrayList<>();
    }

    public void addNews(News aNews){
        newsList.add(aNews);
    }

    @Override
    public String toString() {
        var result = new StringBuilder();
        result.append("NewsList{/nextLine/" +"keyWord='" + keyWord + "\'/nextLine/" +                ", newsList=/nextLine/");
        for (News news: newsList){
            result.append(news.toString()+"/nextLine/");
        }
        return result.toString();
    }
}
