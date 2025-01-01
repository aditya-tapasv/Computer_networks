import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ChatbotServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Chatbot server is listening on port 5000...");
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);

            String receivedMessage;
            String sendMessage;

            // Step 1: Handle the initial "Are you online?" question
            receivedMessage = input.readUTF();
            System.out.println("Client says: " + receivedMessage);
           
            if (receivedMessage.equalsIgnoreCase("Are you online?")) {
                // Respond with "online" status
                output.writeUTF("Yes, I am online!");
                output.flush();
            }

            // Step 2: Continue the chat back and forth
            while (true) {
                // Server reads client's message
                receivedMessage = input.readUTF();
                System.out.println("Client says: " + receivedMessage);

                // Break the loop if client says 'bye'
                if (receivedMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client disconnected");
                    break;
                }

                // Server sends its response or question
                System.out.print("You (Server): ");
                sendMessage = scanner.nextLine();
                output.writeUTF(sendMessage);
                output.flush();

                // If server says 'bye', end the chat
                if (sendMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Chat ended by server.");
                    break;
                }
            }

            // Close resources
            scanner.close();
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}