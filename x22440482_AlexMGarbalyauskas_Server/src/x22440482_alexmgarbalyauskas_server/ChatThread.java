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

public class ChatThread implements Runnable {
    final Socket clientSocket;

    public ChatThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        BufferedReader input = null;
        PrintWriter output = null;

        try {
            input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            output = new PrintWriter(clientSocket.getOutputStream(), true);

            // Asking for the client's name
            output.println("Enter your name: ");
            String username = input.readLine();

            // Check if username is valid
            if (username == null || username.trim().isEmpty()) {
                output.println("Invalid username. Connection will be closed.");
                return;
            }

            System.out.println(username + " has joined the chat.");

            String message;
            while ((message = input.readLine()) != null) {
                if (message.equalsIgnoreCase("exit")) {
                    System.out.println(username + " has left the chat.");
                    break;
                }

                System.out.println(username + " says: " + message);
                output.println("You (" + username + ") said: " + message);
            }
        } catch (IOException e) {
            System.err.println("I/O error: " + e.getMessage());
        } finally {
             // Close resources
            try {
                if (output != null) {
                    output.close();
                }
                if (input != null) {
                    input.close();
                }
                if (clientSocket != null && !clientSocket.isClosed()) {
                    clientSocket.close();
                }
            } catch (IOException e) {
                System.err.println("Error closing resources: " + e.getMessage());
            }

        }
    }
}