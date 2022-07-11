package nl.saxion.tcpServer.serverFunctions;

public interface MenuItem {

    String getIndex();

    String getDescription();

    String execute(String keyWord);
}