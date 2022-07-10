package nl.saxion.tcpServer.server;
import nl.saxion.tcpServer.serverFunctions.dictionary.IPLocation;
import nl.saxion.tcpServer.serverFunctions.news.NewsAbout;
import nl.saxion.tcpServer.serverFunctions.time.TimeByIP;
import nl.saxion.tcpServer.serverFunctions.weather.CurrentWeather;
import nl.saxion.tcpServer.serverFunctions.weather.ForecastWeather;

import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    static long clientNum = 0;
    private long MAX_CLIENT;
    private Menu menu;

    public Server(long max){
        this.MAX_CLIENT = max;
        this.menu = new Menu();
        menu.addMenu(new CurrentWeather());
        menu.addMenu(new ForecastWeather());
        menu.addMenu(new NewsAbout());
        menu.addMenu(new TimeByIP());
        menu.addMenu(new IPLocation());
    }

    public void severRun()throws Exception {
        System.out.println("Server started!");
        ServerSocket server = new ServerSocket(20006);
        Socket client;
        while (clientNum < MAX_CLIENT) {
            client = server.accept();
            System.out.println("Sever created!");
            new Thread(new ServerThread(client,menu)).start();
            System.out.println("new client from \'" + client.getLocalAddress() +"\' connect, total clients number: " + clientNum);
        }
        server.close();
    }

    public static void main(String[] args) {
        var server = new Server(5);
        try {
            server.severRun();
        } catch (Exception e) {
            System.out.println("Error occurred during creating server, please check network and system environment");
            e.printStackTrace();
        }
    }
}
