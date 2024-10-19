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
import java.net.SocketException;



/**
 *
 * @author Alex
 */
public class SelectionThread implements Runnable {
    final Socket clientSocket;

    public SelectionThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {

        try {
            //Input and Output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            //Client chooses between chat or calendar
            output.println("Select (chat) or (calendar): ");
            String choice = input.readLine();

            //User selects chat
            if (choice.equalsIgnoreCase("chat")) {
                new Thread(new ChatThread(clientSocket)).start();
                
            } else if (choice.equalsIgnoreCase("calendar")) {
                output.println("Dublin event list: press a key to continue: ");
                new Thread(new CalendarThread(clientSocket)).start(); 
                
            } else {
                output.println("Invalid choice. Connection will be closed.");
                clientSocket.close();
            }
        } 
        //General Errors
        catch (IOException e) {
            System.err.println("I/O error occurred: " + e.getMessage());
        }
        catch(Exception e){
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }
} 