import java.io.*;
import java.net.*;
import java.util.*;

public class hiiserver {
    private static final int PORT = 12345;
    private static Map<String, PrintWriter> clients = new HashMap<>();

    public static void main(String[] args) {
        System.out.println("Server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new ClientHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class ClientHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String clientName;

        public ClientHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                // Ask for the client's name
                clientName = in.readLine();
                clients.put(clientName, out);
                System.out.println(clientName + " connected.");
                broadcast(clientName + " has joined the chat."); // Notify all clients

                String message;
                while ((message = in.readLine()) != null) {
                    // Handle EXIT command
                    if (message.equals("EXIT")) {
                        System.out.println(clientName + " disconnected.");
                        break;
                    }

                    // Split the message into recipient and content
                    String[] parts = message.split(":", 2);
                    if (parts.length < 2) {
                        continue; // Skip invalid messages
                    }

                    String recipient = parts[0];
                    String content = parts[1];

                    // Broadcast or send privately
                    if (recipient.equals("ALL")) {
                        broadcast(clientName + ": " + content);
                    } else {
                        sendToClient(recipient, clientName + ": " + content);
                    }
                }
            } catch (IOException e) {
                System.out.println(clientName + " disconnected unexpectedly.");
            } finally {
                // Remove client and notify others
                clients.remove(clientName);
                broadcast(clientName + " has left the chat.");
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Broadcast a message to all clients
        private void broadcast(String message) {
            for (PrintWriter writer : clients.values()) {
                writer.println(message);
            }
        }

        // Send a message to a specific client
        private void sendToClient(String recipient, String message) {
            PrintWriter writer = clients.get(recipient);
            if (writer != null) {
                writer.println(message);
            } else {
                clients.get(clientName).println("Recipient " + recipient + " not found.");
            }
        }
    }
}