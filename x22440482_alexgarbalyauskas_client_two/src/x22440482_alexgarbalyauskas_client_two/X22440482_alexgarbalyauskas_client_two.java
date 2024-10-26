/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x22440482_alexgarbalyauskas_client_two;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class X22440482_alexgarbalyauskas_client_two {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // Establish server connection and port 1234
            Socket s = new Socket(InetAddress.getLocalHost(), 1234); 
            
            // Input and Output streams
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            
            // Printing of inputs 
            Scanner scanner = new Scanner(System.in);
           
            // Choosing chat or calendar 
            System.out.println(input.readLine()); 
            String choice = scanner.nextLine();
            
            // Activates your choice
            output.println(choice);
            
            // Chat selection 
            if (choice.equalsIgnoreCase("chat")) {
                // Asking for the client's name 
                System.out.println(input.readLine());
                String username = scanner.nextLine();
                output.println(username); 
                
                // Reads users messages and prints them on the next line
                String message;
                while (true) {
                    System.out.println("Enter chat message or 'STOP': ");
                    message = scanner.nextLine(); 
                    
                    // If user decides to leave they enter STOP
                    if (message.equalsIgnoreCase("STOP")) {
                        break;
                    }
                    
                    // Prints the input message 
                    output.println(message);
                    System.out.println("Server: " + input.readLine());
                }
             
            // Calendar selection
            } else if (choice.equalsIgnoreCase("calendar")) {
                // Prints the server's response 
                String response;
                while (true) {
                    response = input.readLine(); 
                    System.out.println("Server: " + response);
                    
                    if (response.equalsIgnoreCase("Exiting calendar: ")) {
                        break;
                    }
                    
                    String action = scanner.nextLine();
                    output.println(action);
                    
                    // If user decides to leave 
                    if (action.equalsIgnoreCase("STOP")) {
                        break;
                    }
                    
                    // Prints the server's response
                    response = input.readLine();
                    System.out.println("Server: " + response);
                }
                
            // If user has a wrong input selection
            } else {
                System.out.println("Invalid choice");       
            }
          
        // General Errors 
        } catch (UnknownHostException e) {
            System.err.println("Error unable to determine IP address in client class: " + e.getMessage());  
        } catch (IOException e) {
            System.err.println("Error IO in client class: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Error a null pointer exception in client class: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Error client class occurred: " + e.getMessage());
        }
    }         
}
