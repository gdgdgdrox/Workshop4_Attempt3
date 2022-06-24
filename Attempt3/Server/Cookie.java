package Attempt3.Server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Cookie {
    
    public static String getCookie(){
        String cookieLine = "";
        LinkedList<String> cookieList = new LinkedList<String>();
        try{
            File cookieFile = new File("Attempt3/Server/cookie_file.txt");
            Scanner scan = new Scanner(cookieFile);

            while (scan.hasNextLine()){
                String line = scan.nextLine();
                cookieList.add(line);
            }

            scan.close();
            Random random = new Random();
            int randomNumber = random.nextInt(cookieList.size());
            cookieLine = cookieList.get(randomNumber);
        }
        catch (FileNotFoundException FNFE){
            System.err.println("File not found");
        }
            return cookieLine;
    

    }
}
