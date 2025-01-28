import java.io.*;
import java.net.*;
import java.util.*;

public class noticeboradser {
    private static final int PORT = 12346;
    private static List<String> messages = new ArrayList<>();

    public static void main(String[] args) {
        System.out.println("Notice Board Server started on port " + PORT);
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            while (true) {
                new NoticeBoardHandler(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class NoticeBoardHandler extends Thread {
        private Socket socket;
        private PrintWriter out;
        private BufferedReader in;

        public NoticeBoardHandler(Socket socket) {
            this.socket = socket;
        }

        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);

                String request;
                while ((request = in.readLine()) != null) {
                    if (request.equals("VIEW")) {
                        out.println(String.join("\n", messages));
                    } else {
                        messages.add(request);
                        out.println("Message posted.");
                    }
                }
            } catch (IOException e) {
                System.out.println("Client disconnected.");
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}