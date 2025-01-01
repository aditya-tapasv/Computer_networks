import java.io.*;
import java.net.*;

public class comserver {

    public static void main(String[] args) throws Exception {
        ServerSocket serverSocket = new ServerSocket(8080) ;
        System.out.println("Server started, waiting for clients...");

        while (true)
        {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress());

            try (BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter output = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String command;
                while ((command = input.readLine()) != null) {
                    System.out.println("Executing command: " + command);
                    String result = executeCommand(command);
                    output.println(result);
                }
            } catch (IOException e) {
                System.out.println("Error communicating with client: " + e.getMessage());
            }
            serverSocket.close();
        }
    }

    private static String executeCommand(String command)
    {
        StringBuilder output = new StringBuilder();

        try
        {
            Process process = Runtime.getRuntime().exec(command);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
            BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));

            String line;
            while ((line = stdInput.readLine()) != null)
            {
                output.append(line).append("\\n");
            }
            while ((line = stdError.readLine()) != null)
            {
                output.append(line).append("\\n");
            }
        }
        catch (IOException e)
        {
            output.append("Error executing command: ").append(e.getMessage());
        }

        return output.toString();
    }

}