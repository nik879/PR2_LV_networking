package networking.pingpongserver;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PingPongServer {
    private static int counter=0;
    public static void main(String[] args) {
        System.out.println("Starting Server at port 3333...");
        try (ServerSocket ss = new ServerSocket(3333)) {
            while (true) {
                Socket client = ss.accept();
                System.out.println("Client connection was accepted...");
                PingPongHandler ph = new PingPongHandler(client);
                Thread t1 = new Thread(ph);
                t1.start();
                counter++;
                System.out.println("connection number "+counter);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

