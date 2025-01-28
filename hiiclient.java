import java.io.*;
import java.net.*;
import java.util.Scanner;

public class hiiclient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            // Ask for the client's name
            System.out.print("Enter your name: ");
            String clientName = scanner.nextLine();
            out.println(clientName); // Send the name to the server

            // Start a thread to listen for messages from the server
            new Thread(() -> {
                try {
                    String serverMessage;
                    while ((serverMessage = in.readLine()) != null) {
                        System.out.println(serverMessage);
                    }
                } catch (IOException e) {
                    System.out.println("Disconnected from server.");
                }
            }).start();

            // Main loop to send messages
            while (true) {
                System.out.print("Enter recipient (or 'ALL' for broadcast, or 'EXIT' to quit): ");
                String recipient = scanner.nextLine();

                // Exit condition
                if (recipient.equalsIgnoreCase("EXIT")) {
                    out.println("EXIT"); // Notify the server
                    System.out.println("Disconnecting from the server...");
                    break;
                }

                System.out.print("Enter message: ");
                String message = scanner.nextLine();
                out.println(recipient + ":" + message); // Send the message to the server
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}