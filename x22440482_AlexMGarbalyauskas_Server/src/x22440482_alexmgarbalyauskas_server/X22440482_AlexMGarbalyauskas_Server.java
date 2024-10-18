/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x22440482_alexmgarbalyauskas_server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class X22440482_AlexMGarbalyauskas_Server {

    /**
     * @param args the command line arguments
     */
    
    //Storing the events added by the user 
    public static ArrayList<String> events = new ArrayList<>();
            
    public static void main(String[] args) {
        // TODO code application logic here
        try{
            //Server port 1234 
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Server started waiting for clients:  ");
            
            
            //Accepting users
            while(true){
                Socket accept = server.accept(); 
                System.out.println("User has joined: " + accept.getInetAddress());
                //handling multiple users and giving server options 
                new Thread(new SelectionThread(accept)).start(); 
            }
                
        }
        //Error catchers 
        catch(IOException e){
            
            
        }
        
        
        
        
        
        
    }
    
}
