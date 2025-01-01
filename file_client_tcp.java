import java.io.*;
import java.net.*;
public class file_client_tcp
{
    public static void main (String [] args)
    {
        try
        {
            Socket s = new Socket("localhost", 8888);
            byte[] b = new byte[5000];
            InputStream is = s.getInputStream();
            FileOutputStream fo = new FileOutputStream("D:\\tcp_file\\sample.txt");
            is.read(b, 0, b.length);
            fo.write(b, 0, b.length);
        }
        catch(Exception e)
        {
            System.out.println("error");
        }
    }
}
