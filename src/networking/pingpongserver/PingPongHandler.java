package networking.pingpongserver;

import java.io.*;
import java.net.Socket;

public class PingPongHandler implements Runnable{
    private Socket client;

    public PingPongHandler(Socket client) {
        this.client = client;
    }

    public static void processClient(Socket client) {
        System.out.println("Starting to process Client");
        try (BufferedReader br = new BufferedReader(new InputStreamReader(client.getInputStream()));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()))) {
            String incommingMessage;
            while ((incommingMessage=br.readLine())!=null) {
//                String incommingMessage = br.readLine();
//                if(incommingMessage.equalsIgnoreCase("quit")) break;
                System.out.println("Sending Message to Client");
                switch (incommingMessage) {
                    case "ping":
                        bw.write("pong");
                        break;
                    case "pong":
                        bw.write("ping");
                        break;
                    default:
                        bw.write("error");
                }
                bw.newLine();
                bw.flush();
            }
//            System.out.println("connection refused by client");
//            System.out.println("closing serversocket");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        processClient(client);
    }
}

