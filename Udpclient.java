import java.net.*;
import java.util.*;
import java.io.*;
public class Udpclient
{
    public static void main(String[] args)
    {
        try
        {
            DatagramSocket ds = new DatagramSocket();
            DatagramSocket ds2 = new DatagramSocket(8888);
            byte[] buffer=new byte[200];
            InetAddress ip = InetAddress.getByName("localhost");
            Scanner scan=new Scanner(System.in);
            String msg;
            while(true)
            {
                DatagramPacket dp2=new DatagramPacket(buffer,200);
                ds2.receive(dp2);
                String r=new String(dp2.getData(),0,dp2.getLength());
                if(r.equalsIgnoreCase("bye"))
                {
                    System.out.print("server:"+r);
                    break;
                }
                System.out.println("server:"+r);
                System.out.print("you:");
                msg= scan.nextLine();
                DatagramPacket dp=new DatagramPacket(msg.getBytes(),msg.length(),ip,9999);
                ds.send(dp);
                if(msg.equalsIgnoreCase("bye"))
                {
                    break;
                }
            }
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
}
