import java.io.*;
import java.net.*;
public class file_server_tcp
{
    public static void main (String [] args)
    {
        try {
            ServerSocket ss = new ServerSocket(8888);
            Socket s = ss.accept();
            FileInputStream fi = new FileInputStream("D:\\mmmm.txt");
            byte[] b = new byte[5000];
            fi.read(b, 0, b.length);
            OutputStream os = s.getOutputStream();
            os.write(b,0,b.length);
        }
        catch (Exception e)
        {
            System.out.println("error");
        }
    }
}