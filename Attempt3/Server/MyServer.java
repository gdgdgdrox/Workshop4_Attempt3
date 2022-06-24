package Attempt3.Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer{
    public static ServerSocket server = null;
    public static Socket socket = null;
    public static boolean close = false;
    public static int portNumber;
    public static String fileToAccess;

    public static void main(String[] args) {
        if (args.length > 0){
            portNumber = Integer.parseInt(args[0]);
            fileToAccess = args[1];
        }
        
        //start server
        MyServer.startServer(portNumber);
        
    }
    
    public static void startServer(int port){
        try{
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("Client has connected");
 
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());

            while(!close){
                String msgFromClient = dis.readUTF();
                System.out.println("Client: " + msgFromClient);
                if (msgFromClient.equals("get-cookie")){
                    dos.writeUTF("cookie-text " + Cookie.getCookie());
                    dos.flush();
                }
                else if (msgFromClient.equals("close")){
                    close = true;
                    dos.writeUTF("Closing connection. Goodbye");
                    dos.flush();
                    dis.close();
                    dos.close();
                    socket.close();
                }
                else {
                    dos.writeUTF("I dont understand your request: " + msgFromClient);
                    dos.flush();
                }
            }
        }
        catch (IOException ex){
            ex.printStackTrace();
        }
        
    }

}