import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Scanner;

public class clientString {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket clientSocket = new DatagramSocket();
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData = new byte[1024];
		byte[] receiveData = new byte[1024];
		
		Scanner input = new Scanner(System.in);
		System.out.print("Client: ");
		String mesage = input.nextLine();
		sendData = mesage.getBytes();
		
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, 7090);
		clientSocket.send(sendPacket);
		
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);

		String str = new String(receivePacket.getData());
		System.out.println(str);
		clientSocket.close();
	}

}
