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
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class CalendarThread implements Runnable {
    private final Socket clientSocket;
    //shared ArrayList
    private static ArrayList<String> eventList = new ArrayList<>(); 

    public CalendarThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run(){
         try {
            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);

            
            while (true) {
                String action;
                // Ask for user input (add, remove, display, or stop)
                output.println("Enter 'add', 'remove', 'display', or 'STOP' to exit:");
                action = input.readLine();

                if (action.equalsIgnoreCase("add")) {
                    output.println("Enter the event name to add:");
                    String event = input.readLine();
                    synchronized (eventList) {
                        eventList.add(event);
                    }
                    output.println("Event '" + event + "' added.");

                } else if (action.equalsIgnoreCase("remove")) {
                    output.println("Enter the event name to remove:");
                    String event = input.readLine();
                    synchronized (eventList) {
                        if (eventList.remove(event)) {
                            output.println("Event '" + event + "' removed.");
                        } else {
                            output.println("Event not found.");
                        }
                    }

                } else if (action.equalsIgnoreCase("display")) {
                    synchronized (eventList) {
                        if (eventList.isEmpty()) {
                            output.println("No events in the calendar.");
                        } else {
                            output.println("Current events:");
                            for (String event : eventList) {
                                output.println("- " + event);
                            }
                        }
                    }

                } else if (action.equalsIgnoreCase("STOP")) {
                    output.println("Exiting calendar...");
                    break;

                } else {
                    output.println("Invalid option. Try again.");
                }
            }
            // Close the connection
            clientSocket.close();
        } catch (IOException e) {
            System.err.println("CalendarThread error: " + e.getMessage());
        }
    }       
}
    
