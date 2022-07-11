package nl.saxion.tcpServer;

import nl.saxion.tcpServer.client.Client;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class Main {
    public static void main(String[] args){
        Socket socket = null;
        try {
            socket = new Socket();
            socket.connect(new InetSocketAddress("127.0.0.1", 20006), 1000);
            socket.setSoTimeout(20000);
        } catch (ConnectException e) {
            System.out.println("Failed to connect to server. Please reconnect later");
            System.exit(-1);
        } catch (IOException e) {
            System.out.println("Error occurred when detecting socket.");
            System.exit(-1);
        }
        var client0 = new Client(socket);
        client0.run();

    }
}
