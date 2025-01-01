import java.io.*;
import java.net.*;

public class SocketServer {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            System.out.println("Server is listening on port 5000...");
            
            // Wait for client connection
            Socket socket = serverSocket.accept();
            System.out.println("Client connected");
            
            // Streams to send and receive data
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());

            // Read message from client
            String receivedMessage = input.readUTF();
            System.out.println("Received from client: " + receivedMessage);

            // Send a response
            String responseMessage = "Hello, I am adi , your personel assistant!";
            output.writeUTF(responseMessage);
            System.out.println("Sent to client: " + responseMessage);

            // Close connections
            input.close();
            output.close();
            socket.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
