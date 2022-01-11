import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Date;

public class serverDate {

	public static void main(String[] args) throws IOException {
		DatagramSocket serverSocket = new DatagramSocket(7090);
		System.out.println("Server is started!!");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true)
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData());
			System.out.println(request);
			if(request.trim().equals("getDate"))
			{
				sendData = new Date().toString().getBytes();
			}
			else sendData = "Server not know what you want?".getBytes();
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serverSocket.send(sendPacket);
		}
		

	}

}
