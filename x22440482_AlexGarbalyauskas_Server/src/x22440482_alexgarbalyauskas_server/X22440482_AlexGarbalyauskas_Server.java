/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x22440482_alexgarbalyauskas_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 *
 * @author Alex
 */
public class X22440482_AlexGarbalyauskas_Server {
    //array 

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  {
        // TODO code application logic here
        
        ServerSocket ss = null;
        
        try{
            ss = new ServerSocket(1234);
            System.out.println("Waiting for clients: ");
            
            while(true){
                Socket link = ss.accept();
                System.out.println("Client has joined: " + link.getInetAddress());
                
                // Set up input and output streams
                BufferedReader in = new BufferedReader(new InputStreamReader(link.getInputStream()));
                PrintWriter out = new PrintWriter(link.getOutputStream(), true);
                
                new Thread(new ClientHandler(link)).start();
                
               
                
                String inputLine;
                while ((inputLine = in.readLine()) != null) {
                    System.out.println(": " + inputLine);
                    out.println("Previous Message: " + inputLine); // Echo the message back to the client
                }

                link.close(); // Close the connection
                
  
            }
        }
        catch(IOException e){
            //some error cathes e.g wrong name input
            //add error
            //remove error
            
        }

        
    }
    
}
