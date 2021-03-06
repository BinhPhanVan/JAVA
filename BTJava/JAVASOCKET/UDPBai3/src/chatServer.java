import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Scanner;

public class chatServer {

	public static void main(String[] args) throws IOException {
		DatagramSocket serversocket = new DatagramSocket(7090);
		System.out.println("Server is started!");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true)
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serversocket.receive(receivePacket);
			String str = new String(receivePacket.getData(), "UTF-8");
			System.out.println("Client: " + str);
			
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			
			Scanner sr = new Scanner(System.in);
			System.out.print("Server: ");
			String message = sr.nextLine();
			sendData = message.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serversocket.send(sendPacket);
		}

	}

}
