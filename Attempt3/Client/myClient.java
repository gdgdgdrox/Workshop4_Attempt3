package Attempt3.Client;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class myClient {
    public static void main(String[] args) {
        String[] terms = args[0].split(":");
        String localaddress = terms[0];
        int port = Integer.parseInt(terms[1]);

        try{
            Socket socket = new Socket(localaddress, port);

            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            Scanner scan = new Scanner(System.in);

            while(true){
                String userInput = scan.nextLine();
                dos.writeUTF(userInput);
                dos.flush();

                String msgFromServer = dis.readUTF();

                if (msgFromServer.contains("cookie-text")){
                    String[] splitServerCookie = msgFromServer.split(" ");
                    System.out.println("Server: " + splitServerCookie[1]);
                }
                else
                    System.out.println("Server: " + msgFromServer);
                if (userInput.equals("close"))
                    break;
            }
            scan.close();
            dos.close();
            dis.close();
            socket.close();
        }
        catch (IOException ex){
            ex.printStackTrace();;
        }

    }
}
