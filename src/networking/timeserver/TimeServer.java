package networking.timeserver;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.*;
import java.time.LocalDateTime;

public class TimeServer {
    static int counter=0;
    public static void main(String[] args) {

        System.out.println("Server is starting on port 1111...");
        try (ServerSocket ss = new ServerSocket(1111)) {
            while (counter<=5) {
                System.out.println("Port is binded, waiting for Clients");
                System.out.println("Until now there where "+counter+" client connections");
                try (Socket Client = ss.accept();
                     BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(Client.getOutputStream()))) {
                    LocalDateTime ldt = LocalDateTime.now();
                    System.out.println("sending local Date and Time (" + ldt + ") to Client");
                    bw.write("from Server: "+ldt.toString());
                    bw.newLine();
                    bw.flush();
                    counter++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
