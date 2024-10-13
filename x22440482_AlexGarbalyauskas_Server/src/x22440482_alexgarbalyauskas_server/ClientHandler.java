/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package x22440482_alexgarbalyauskas_server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author Alex
 */
public class ClientHandler implements Runnable{
    private Socket clientSocket;
    private String clientName;

    public ClientHandler(Socket socket) {
        this.clientSocket = socket;
        this.clientName = clientName;
    }
    
    @Override
    public void run(){
        
        try{
            //Get Data and convert it 
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            
            clientName = input.readLine();
            System.out.println(clientName + "says: ");
            
        
        }
        
        catch(Exception e){
            
        }
        
        
        
        
        //gets clients names
        
        //client says plus message display 
        
        //exit 
        
        
        
    }
    
    public void Add(){
        
    }
    
    public void Remove(){
        
    }
    
    
    
}
