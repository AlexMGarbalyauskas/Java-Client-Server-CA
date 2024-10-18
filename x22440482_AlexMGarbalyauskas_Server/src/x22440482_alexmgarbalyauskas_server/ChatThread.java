/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package x22440482_alexmgarbalyauskas_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Alex
 */
public class ChatThread implements Runnable {
    final Socket clientSocket;

    public ChatThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run(){
        try {
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            
            //Asking for the clients name 
            output.println("Enter your name: ");
            String username = input.readLine();
            System.out.println(username + "has joined: ");
            
            String messages;
            while ((messages = input.readLine()) != null){
                System.out.println(username + ": " + messages);
                
                //send back
                output.println("You ( " + username + " ) " + " said " + messages);
                
            }
            
        }
        
        catch(IOException e){
            
        }
        
        
        
        
    }
    
}
