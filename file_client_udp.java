import java.io.*;
import java.net.*;
public class file_client_udp
{
    public static void main (String [] args)
    {
        try
        {
            DatagramSocket ds = new DatagramSocket();
            byte[] b = new byte[5000];
            FileInputStream fi = new FileInputStream("D:\\mmmm.txt");
            fi.read(b,0,b.length);
            InetAddress ip =InetAddress.getByName("localhost");
            DatagramPacket pac=new DatagramPacket(b,b.length,ip,8888);
            ds.send(pac);
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }
}
