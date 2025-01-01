import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Locale;
import java.util.Scanner;

public class serversocket
{
   public static void main (String args[])
   {
       try
       {
           ServerSocket serversocket = new ServerSocket(5555);
           Socket socket = serversocket.accept();
           DataInputStream din= new DataInputStream(socket.getInputStream());
           DataOutputStream dout= new DataOutputStream(socket.getOutputStream());
           String r,s;
           Scanner scan = new Scanner(System.in);
           while(true)
           {
               r=din.readUTF();
               System.out.println("socket: "+r);
               if(r.equalsIgnoreCase("bye"))
               {
                   dout.writeUTF("bye");
                   break;
               }
               s=scan.nextLine();
               dout.writeUTF(s);
           }

       }
       catch (Exception e)
       {
           System.out.println("error");
       }

   }

}
