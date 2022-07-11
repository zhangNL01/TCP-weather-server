package nl.saxion.tcpServer.server;

import nl.saxion.tcpServer.serverFunctions.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Menu {
    private List<MenuItem> menus;

    public Menu() {
        menus = new ArrayList<>();
    }

    void addMenu(MenuItem menuItem) {
        menus.add(menuItem);
    }

    public String run(String command) {
        var result = "";
        try{
        var strings = command.split("\\s+");
        for (MenuItem item : menus) {
            if (item.getIndex().equals(strings[0])) {
                result = item.execute(strings[1]);
            }
        }}catch (NullPointerException e){
            System.out.println("One user close connection without quit");
        }
        return result;
    }
}
