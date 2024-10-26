/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x22440482_alexgarbalyauskas_server;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class X22440482_alexgarbalyauskas_server {

    /**
     * @param args the command line arguments
     */
    
    // Storing the events added by the user (for future calendar functionality)
    public static ArrayList<String> events = new ArrayList<>();
    
    public static void main(String[] args) {
        try {
            // Server port 1234
            ServerSocket server = new ServerSocket(1234);
            System.out.println("Server started, waiting for clients...");

            // Accepting many clients in an infinite loop
            while (true) {
                try {
                    // Accepting
                    Socket accept = server.accept(); 
                    
                    // Get the local host
                    InetAddress localAddress = InetAddress.getLocalHost();
                    System.out.println("User has joined: " + localAddress.getHostAddress());
                    
                    // Handling multiple clients in separate threads
                    new Thread(new SelectionThread(accept)).start();
                    
                // Error in connection
                } catch (IOException e) {
                    System.err.println("Error in accepting client connection: " + e.getMessage());
                }
                
            } // while end 
            
        // General errors
        } catch (SocketException e) {
            System.err.println("Socket error: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Socket creation error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error in server: " + e.getMessage()); 
        }
    }   
}