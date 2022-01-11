import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public class test1 {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(7091);
		System.out.println("Server UDP is started!!");
		
		while(true)
		{
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData(), "UTF-8");
			String mesage = "Server UDP: " + String.valueOf(port).trim();
			
			sendData = mesage.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serverSocket.send(sendPacket);
		}
	}
}

