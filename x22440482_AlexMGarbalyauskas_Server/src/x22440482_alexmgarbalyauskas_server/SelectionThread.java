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
public class SelectionThread implements Runnable {
    final Socket clientSocket;
    
    
    //Constructor
    public SelectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    
    @Override
    public void run(){
        try{
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            output.println("Select (chat) or (calendar): ");
            String choice = input.readLine();
            
            
            if (choice.equalsIgnoreCase("chat")){
                //Selecting chat thread 
                new Thread(new ChatThread(clientSocket)).start();
            }
            
            else if(choice.equalsIgnoreCase("calendar")){
                //Selecting calendar thread
                new Thread (new CalendarThread(clientSocket)).start();
                
            }
            
            else {
                output.println("Invalid");
                clientSocket.close();
                
            }
            
            
            
        }
        catch(IOException e){
            
        }
    }
    
}
