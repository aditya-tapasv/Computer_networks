import java.io.*;
import java.net.*;

public class SocketClient {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 5000)) 
        {
            System.out.println("Connected to the server");

            // Streams to send and receive data
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // Send a message to the server
            String messageToSend = "Hello, I am Abbaragithan!";
            output.writeUTF(messageToSend);
            System.out.println("Sent to server: " + messageToSend);

            // Read response from the server
            String responseMessage = input.readUTF();
            System.out.println("Received from server: " + responseMessage);

            // Close connections
            input.close();
            output.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
