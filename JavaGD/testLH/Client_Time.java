import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client_Time {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket clientSocket = new DatagramSocket(); //gan cong
		InetAddress IPAddress = InetAddress.getByName("localhost");
		byte[] sendData= new byte[100];
		byte[] receiveData= new byte[100];
		sendData = "getDate".getBytes();
		//tao datagram co noi dung yeu cau loai du lieu de gui cho server
		DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAddress,9876);
		clientSocket.send(sendPacket);
		//tao datagram rong de nhan du lieu
		DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
		clientSocket.receive(receivePacket);
		//nhan du lieu tu packet nhan duoc
		String str= new String(receivePacket.getData());
		System.out.println(str);
		clientSocket.close();
	}

}
