import java.io.*;
import java.net.*;

public class comclient {

    public static void main(String[] args) throws Exception{
        String host = "localhost";
        int port = 8080;

        Socket socket = new Socket(host, port);
        BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        PrintWriter output = new PrintWriter(socket.getOutputStream(), true);
        BufferedReader consoleInput = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Connected to the server at " + host + ":" + port);
        System.out.println("Enter command to execute on the server (type 'exit' to quit):");

        String command;
        while (!(command = consoleInput.readLine()).equalsIgnoreCase("exit")) {
            output.println(command);
            System.out.println("Server response:");

            String responseLine;
            while ((responseLine = input.readLine()) != null) {
                System.out.println(responseLine);
                if (!input.ready()) break;
            }
        }

    }
}
