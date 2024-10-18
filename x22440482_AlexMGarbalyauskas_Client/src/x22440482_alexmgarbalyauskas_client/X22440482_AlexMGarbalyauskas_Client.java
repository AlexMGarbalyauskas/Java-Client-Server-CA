/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package x22440482_alexmgarbalyauskas_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Alex
 */
public class X22440482_AlexMGarbalyauskas_Client {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //TODO code application logic here
        
        try {
            
            //Establish server connection and port 1234
            Socket s = new Socket(InetAddress.getLocalHost(), 1234); 
            
            //Input and Output streams
            PrintWriter output = new PrintWriter(s.getOutputStream(), true);
            BufferedReader input = new BufferedReader(new InputStreamReader(s.getInputStream()));
            Scanner scanner = new Scanner(System.in);
           
            //Choosing chat or calendar 
            System.out.println(input.readLine()); 
            String choice = scanner.nextLine();
            //Activates your choice
            output.println(choice);
            
            //Chat selection 
            if(choice.equalsIgnoreCase("chat")){
                //Asking for the clients name 
                System.out.println(input.readLine());
                String username = scanner.nextLine();
                output.println(username); 
                
                String message;
                while(true){
                    System.out.println("Enter chat message or 'stop': ");
                    message = scanner.nextLine(); 
                    
                    if(message.equalsIgnoreCase("stop")){
                        break;
                    }
                    
                    output.println(message);
                    //String response = input.readLine();
                    System.out.println("Server: " +  input.readLine());
                }     
            }
            
            //Calendar selection
            else if(choice.equalsIgnoreCase("calendar")){
                //calendarHandler(input, output, scanner); 
                System.out.println("Coming soon");
                
            } 
            //If user has a wrong input selection 
            else {
                System.out.println("Invalid choice"); 
                
            }   
        }
        
        catch(IOException e){
            System.err.println("Error: " + e.getMessage());
            
        }            
    }  
}
