import java.io.*;
import java.net.*;

public class file_server_udp
{
    public static void main (String [] args)
    {
        try
        {
            DatagramSocket ds = new DatagramSocket(8888);
            byte[] b = new byte[5000];
            DatagramPacket pac = new DatagramPacket(b,0,b.length);
            ds.receive(pac);
            FileOutputStream fo = new FileOutputStream("D:\\tcp_file\\sample.txt");
            fo.write(b,0,b.length);
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
}

