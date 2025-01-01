import java.net.Socket;
import java.net.ServerSocket;
import java.io.*;
import java.util.Scanner;

public class socket
{
    public static void main (String args[])
    {
        try
        {
            Socket socket = new Socket("localhost",5555);
            DataInputStream din= new DataInputStream(socket.getInputStream());
            DataOutputStream dout= new DataOutputStream(socket.getOutputStream());
            String r,s;
            Scanner scan = new Scanner(System.in);
            while(true)
            {
                s=scan.nextLine();
                if(s.equalsIgnoreCase("bye"))
                {
                    dout.writeUTF(s);
                    r= din.readUTF();
                    System.out.println("server :"+r);
                    break;
                }
                dout.writeUTF(s);
                r= din.readUTF();
                System.out.println("server :"+r);

            }

        }
        catch (Exception e)
        {
            System.out.println("error");
        }

    }

}
