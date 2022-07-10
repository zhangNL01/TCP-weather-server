package nl.saxion.tcpServer.serverFunctions.news;

import nl.saxion.tcpServer.serverFunctions.DataGetter;

public class NewsGetter extends DataGetter implements Runnable {
    public NewsGetter(String input) {
        super.api = "http://newsapi.org/v2/everything?q=";
        super.keyWord = input ;
        super.condition = "&from=2020-06-19&sortBy=popularity";
        super.ready = false;
        super.errored = false;
        super.apiKey = "&apiKey=cddaec57a1a54a04b547509656335e26";
    }
}