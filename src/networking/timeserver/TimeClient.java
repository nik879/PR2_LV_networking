package networking.timeserver;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

public class TimeClient {
    public static void main(String[] args) {
        System.out.println("connect with Socket: localhost:1111");
        try (Socket ServerConnection = new Socket("localhost", 1111);
             BufferedReader br = new BufferedReader(new InputStreamReader(ServerConnection.getInputStream()))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("time client exits");
    }
}
