import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
public class client_server_udp {
    public static void main(String [] args){
        DatagramSocket ds =null;
        Scanner Scanner = new Scanner(System.in);
        try{
            ds = new DatagramSocket(5555);
            byte[]receive;
            byte[]send;
            System.out.println("Server is running and waiting for client");
            DatagramPacket.dp = new DatagramPacket(receive.length);
            ds = receive(dp);
            String rmsg = new String(dp.getData(),0,dp.getLength);
        }
    }
}
