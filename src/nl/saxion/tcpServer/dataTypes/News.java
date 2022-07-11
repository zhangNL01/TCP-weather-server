package nl.saxion.tcpServer.dataTypes;

public class News {
    private String channel;
    private String author;
    private String title;

    public News(String channel, String author, String title) {
        this.channel = channel;
        this.author = author;
        this.title = title;
    }

    @Override
    public String toString() {
        return "News{" +
                "channel='" + channel + '\'' +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
