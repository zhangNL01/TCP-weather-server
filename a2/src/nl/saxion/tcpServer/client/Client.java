package nl.saxion.tcpServer.client;

import java.io.*;
import java.net.Socket;

public class Client implements Runnable {
    Socket socket;
    private final int maxWaitTime = 2000;
    private final int waitInterval = 100;

    public Client(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (var inputFromUser = new BufferedReader(new InputStreamReader(System.in));
             var outputToServer = new PrintStream(socket.getOutputStream());
             var inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));) {

            var shouldQuit = false;

            System.out.print("Welcome to the server");

            while (!shouldQuit) {
                System.out.println("\nthe supported commands are:");
                for (Commands value : Commands.values()) {
                System.out.println(value.toString());
            }
                var text = inputFromUser.readLine();
                var components = text.split("\\s+");

                try {
                    var command = Commands.valueOf(components[0].toUpperCase());
                    text += " Netherlands";
                    switch (command) {
                        case TIME:
                            outputToServer.println(text);
                            System.out.println(inputFromServer.readLine());
                            break;
                        case TODAYWEATHER:
                        case WEATHERFORECAST:
                        case NEWS:
                        case IPLOCATION:
                            outputToServer.println(text);
                            System.out.println(inputFromServer.readLine().replaceAll("/nextLine/","\n"));
                            break;
                        case QUIT:
                            outputToServer.println("exit");
                            outputToServer.flush();
                            System.out.println(inputFromServer.readLine());
                            shouldQuit = true;
                            break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Invalid command, please check spelling");
                }

            }
        } catch (IOException iox) {
            System.out.println(iox);
        }
    }
}