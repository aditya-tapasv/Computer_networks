import java.io.*;
import java.net.*;
import java.util.Scanner;

public class noticeboardclient {
    private static final String SERVER_IP = "127.0.0.1";
    private static final int PORT = 12346;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_IP, PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {

            while (true) {
                System.out.println("1. Post Message\n2. View Notice Board");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                if (choice == 1) {
                    System.out.print("Enter message: ");
                    String message = scanner.nextLine();
                    out.println(message);
                    System.out.println(in.readLine());
                } else if (choice == 2) {
                    out.println("VIEW");
                    System.out.println(in.readLine());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}