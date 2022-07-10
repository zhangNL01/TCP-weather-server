package nl.saxion.tcpServer.server;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ServerThread implements Runnable {
    private Menu menu;
    private Socket client;

    public ServerThread(Socket client, Menu menu) {
        this.client = client;
        this.menu = menu;
        Server.clientNum++;
    }

    @Override
    public void run() {
        try {
            var outputToClient = new PrintStream(client.getOutputStream());
            var inputFromClient = new BufferedReader(new InputStreamReader(client.getInputStream()));

            boolean keepRunning = true;
            while (keepRunning) {
                String str = inputFromClient.readLine();
                System.out.println("from client: " + str + " .");
                if ("exit".equals(str) || str == null) {
                    outputToClient.println("Bye.");
                    outputToClient.flush();
                    Server.clientNum--;
                    keepRunning = false;
                } else {
                    outputToClient.println(menu.run(str));
                    outputToClient.flush();
                }
            }
            if (!keepRunning) System.out.println("One socket close");
            outputToClient.close();
            client.close();

        } catch (SocketException e) {
            System.out.println("One client disconnect");
            Server.clientNum--;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}