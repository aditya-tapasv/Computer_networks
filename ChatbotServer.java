import java.io.*;
import java.lang.management.ThreadInfo;
import java.lang.Thread;
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
            // System.out.println("Enter the no. of Clients in theChatbox");
            // int no = scanner.nextInt();

            // System.out.println("Enter the no. of Clients in theChatbox");
            // int no = scanner.nextInt();
            String receivedMessage;
            String sendMessage;
            receivedMessage = input.readUTF();
            System.out.println("Client2 says: " + receivedMessage);


            // Thread th = new Thread();
            // for(int i=0;i<no;i++){
            //     System.out.println("The server is connected to client  "+ i);
            //     //th.start();
            // }
           

            if (receivedMessage.equalsIgnoreCase("Are you online?")) {
                output.writeUTF("Yes, I am online!");
                output.flush();
            }


            while (true) {
                receivedMessage = input.readUTF();
                System.out.println("Client1 says: " + receivedMessage);
                if (receivedMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Client1 disconnected");
                    break;
                }


                // Thread th = new Thread();
                // for(int i=0;i<no;i++){
                //     System.out.println("The server is connected to client  "+ i);
                // //    th.start();
                // }
                
                System.out.print("You (Client2): ");
                sendMessage = scanner.nextLine();
                output.writeUTF(sendMessage);
                output.flush();
                if (sendMessage.equalsIgnoreCase("bye")) {
                    System.out.println("Chat ended by Client1.");
                    break;
                }
            }



            // try (ServerSocket serverSocket = new ServerSocket(5000)) {
            //     System.out.println("Chatbot server is listening on port 5000...");
            //     Socket socket = serverSocket.accept();
            //     System.out.println("Client connected");
            //     DataInputStream input = new DataInputStream(socket.getInputStream());
            //     DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            //     Scanner scanner = new Scanner(System.in);
            //     // System.out.println("Enter the no. of Clients in theChatbox");
            //     // int no = scanner.nextInt();
            //     String receivedMessage;
            //     String sendMessage;
            //     receivedMessage = input.readUTF();
            //     System.out.println("Client2 says: " + receivedMessage);
               
    
            //     if (receivedMessage.equalsIgnoreCase("Are you online?")) {
            //         output.writeUTF("Yes, I am online!");
            //         output.flush();
            //     }

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