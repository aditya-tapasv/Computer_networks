import java.io.*;
import java.net.*;

public class Socketserveradi {
    public static void main (String [] args){
  
        try (ServerSocket serverSocket = new ServerSocket(5000)) {
            Socket adi = serverSocket.accept(); // this accept method will wait until  the requests from the clients arrive to the port
            DataInputStream suman = new DataInputStream(adi.getInputStream());
            String s = suman.readUTF();
            System.out.println(s);
        }
    
    catch (Exception e){
        System.out.println(e);
    }
  }
}