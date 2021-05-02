package networking.pingpongserver;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class PingPongClient {
    public static void main(String[] args) {
        try (Socket ServerCon = new Socket("localhost", 3333);
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(ServerCon.getOutputStream()));
             BufferedReader br = new BufferedReader(new InputStreamReader(ServerCon.getInputStream()));
             BufferedReader cli = new BufferedReader(new InputStreamReader(System.in))) {
            System.out.println("connected with socket localhost:3333");
            String Eingabe;
            while (!(Eingabe= cli.readLine()).equals(null)) {


                if (Eingabe.equals("quit")) {
                    System.out.println("Client wants to quit connection");
                    break;
                }
                bw.write(Eingabe);
                System.out.println("Client sends "+Eingabe);
                bw.newLine();
                bw.flush();
                System.out.println("Server sends "+br.readLine());
//                String Response = cli.readLine();
//                bw.write(Response);
//                bw.newLine();
//                bw.flush();
            }


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
