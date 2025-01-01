import java.net.*;
import java.sql.SQLOutput;
import java.io.*;
import java.util.Scanner;

public class Udpserver
{
     public static void main(String[] args)
     {
         try
         {
             DatagramSocket ds = new DatagramSocket();
             DatagramSocket ds2 = new DatagramSocket(9999);
             byte[] buffer=new byte[200];
             InetAddress ip = InetAddress.getByName("localhost");
             Scanner scan=new Scanner(System.in);
             String msg;
             msg="hello client";
             DatagramPacket dp3=new DatagramPacket(msg.getBytes(),msg.length(),ip,8888);
             ds.send(dp3);
             while(true)
             {

                 DatagramPacket dp2=new DatagramPacket(buffer,200);
                 ds2.receive(dp2);
                 String r=new String(dp2.getData(),0,dp2.getLength());
                 if(r.equalsIgnoreCase("bye"))
                 {
                     System.out.println("client:"+r);
                     break;
                 }
                 System.out.println("client:"+r);
                 System.out.print("you:");
                 msg= scan.nextLine();
                 DatagramPacket dp=new DatagramPacket(msg.getBytes(),msg.length(),ip,8888);
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
