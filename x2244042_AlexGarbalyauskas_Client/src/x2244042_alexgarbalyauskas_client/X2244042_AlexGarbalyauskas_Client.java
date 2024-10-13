/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x2244042_alexgarbalyauskas_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class X2244042_AlexGarbalyauskas_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        String name;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter your name: ");
        name = scanner.nextLine();
        
        try{
                 
        //connection established
        Socket s = new Socket(InetAddress.getLocalHost(), 1234);
        System.out.println("Connected: ");
        
        //input output streams
        PrintWriter out = new PrintWriter(s.getOutputStream(), true);
        BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));
        
        //send name to server
        out.println(name);
        String inputLine;
        
        while(true){
            System.out.println("Enter Message or ('Exit' to leave)");
            inputLine = scanner.nextLine();
           if (inputLine.equalsIgnoreCase("Exit")){
               break;
               
           }
           else if (inputLine.equalsIgnoreCase("exit")){
            break;
               
           }
           out.println(inputLine);
           String response = in.readLine();
           System.out.println("Response" + response);
            
        }
        
        scanner.close();
        s.close();
        
        
        }
        
        
       
        catch(IOException e){
            
        }
        
    }
    
}
