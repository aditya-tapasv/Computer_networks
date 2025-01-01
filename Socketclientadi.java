import java.io.*;
import java.net.*;

public class Socketclientadi {
    public static void main(String [] args){
        try{
            Socket adi = new Socket("localhost", 5000);
            DataOutputStream suman = new DataOutputStream(adi.getOutputStream());
            suman.writeUTF("Hello , Abbaragithan"); 
            adi.close();
        }
        catch (Exception e){
            System.out.println(e);
        }

    }
}