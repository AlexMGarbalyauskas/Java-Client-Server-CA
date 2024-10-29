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
import java.util.ArrayList;

/**
 *
 * @author Alex
 */
public class CalendarThread implements Runnable {
    private final Socket clientSocket;
    
    // shared ArrayList
    private static ArrayList<String> eventList = new ArrayList<>(); 
     
    // Constructor
    public CalendarThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
    
    @Override
    public void run() {
        
        try {
            // Input and output streams
            BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true);
            
            // Starting input
            output.println("Dublin event list: press key 'a' to continue: ");

            // Infinite loop 
            while (true) {
                
                // User selection action of either add, remove, display, or exit
                String action;
                
                // Ask for user input (add, remove, display, or stop)
                output.println("Enter 'add', 'remove', 'display', or 'STOP' to exit:");
                action = input.readLine();
                
                // User adds an event to the list 
                if (action.equalsIgnoreCase("add")) {
                    // Synching the thread if multiple users are to use it, making sure the outputs come in order of each other 
                    synchronized (eventList) {
                        // Asking the user to add events 
                        output.println("Enter the event name to add: or 'exit': ");
                        String event = input.readLine();
                        
                        // User is stuck in adding until they exit 
                        while (!event.equalsIgnoreCase("exit")) {
                            eventList.add(event); 
                            output.println("Event '" + event + "' added.");
                            
                            // Server feedback 
                            System.out.println("User added event to list: " + event); 
                            
                            // Asking for more events or to exit from loop
                            output.println("Enter another event or 'exit'");
                            event = input.readLine();
                        } // while end
                    } // synch end
                    
                    // User clarity, returned back to menu 
                    output.println("returned to menu");
                  
                // User decides to remove an event from list 
                } else if (action.equalsIgnoreCase("remove")) {
                    // Synch to make sure it's ordered
                    synchronized (eventList) {
                        output.println("Enter the event name to remove: or 'exit' to menu");
                        String event = input.readLine();
                        
                        while (!event.equalsIgnoreCase("exit")) {
                            if (eventList.remove(event)) {
                                output.println("Event '" + event + "' removed: ");
                                
                                // Server feedback
                                System.out.println("User removed event from list: " + event); 
                              
                                // Event could not be found in list 
                            } else {
                                output.println("Event not found: ");
                            }
                            output.println("Enter another event to remove: or 'exit' to menu: ");
                            event = input.readLine(); 
                        } // while end
                    } // sync end
                    
                    output.println("returned to menu: "); 
                  
                // User chooses display action
                } else if (action.equalsIgnoreCase("display")) {
                    synchronized (eventList) {
                        // No events in list 
                        if (eventList.isEmpty()) {
                            output.println("No events in the calendar: ");
                        } else {
                            output.println("Current events:");
                            for (String event : eventList) {
                                output.println(" ( " + event + " ) " + "press 'a' to continue: ");
                                
                                // Server feedback
                                System.out.println("User picked display: " + eventList);
                            }
                        }
                    }
                  
                // User decides to end connection
                } else if (action.equalsIgnoreCase("STOP")) {
                    output.println("Exiting calendar...");
                    
                    // Server feedback
                    System.out.println("TERMINATED");
                    break;
                    
                // Continue key if response back is blank 
                } else if (action.equalsIgnoreCase("a")) {
                    output.println("pick");
                  
                // Invalid option chosen by user 
                } else {
                     throw new IncorrectActionException("Invalid option: Try again: ");         
                }
            }
            
            // Close the connection
            clientSocket.close();
          
        // General errors
        } catch (IOException e) {
            System.err.println("CalendarThread error: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Unexpected error in calendar thread" + e.getMessage());
        }
    } // run end
    
    // Method for incorrect action 
    public class IncorrectActionException extends Exception {
        public IncorrectActionException(String message) {
            super(message);
        }   
    } // incorrectaction end 
}