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
public class CalendarThread implements Runnable {
    private Socket clientSocket;

    public CalendarThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
      
    @Override
    public void run(){
        try{
            //Input and Output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            
        }
        
        catch(IOException e){
            
        }
    }
    
}
