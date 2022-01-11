import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;
import java.util.Scanner;

public class serverChat {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(7090);
		System.out.println("Server is started!!");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		Scanner input = new Scanner(System.in);
		while(true)
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData(), "UTF-8");
			System.out.println("Client: "+request);
			System.out.print("Server: ");
			String mesage = input.nextLine();
			sendData = mesage.getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serverSocket.send(sendPacket);
		}
	}

}
