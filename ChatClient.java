import java.io.*;
import java.net.*;
import java.util.Scanner;
public class ChatClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) {
            System.out.println("Connected to the chatbot server");
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in);
            String sendMessage, receivedMessage;
            System.out.println("You (Client): Are you online?");
            output.writeUTF("Are you online?");
            output.flush();
            receivedMessage = input.readUTF();
            System.out.println("Server: " + receivedMessage);
            while (true) {
                System.out.print("You (Client): ");
                sendMessage = scanner.nextLine();
                output.writeUTF(sendMessage);
                output.flush();
                if (sendMessage.equalsIgnoreCase("bye")) {
                    System.out.println("You disconnected from the server");
                    break;
                }

                
                receivedMessage = input.readUTF();
                System.out.println("Server: " + receivedMessage);

                
                if (receivedMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Server disconnected");
                    break;
                }
            }

            // Close resources
            scanner.close();
            input.close();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}