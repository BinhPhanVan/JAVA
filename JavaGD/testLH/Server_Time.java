import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;


public class Server_Time {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Gan cong 9876 cho chuong trinh
		DatagramSocket serverSocket = new DatagramSocket(9876);
		//Tao cac mang byte de chua du lieu gui va nhan
		System.out.println("Server is started");
		byte[] receiveData = new byte[100];
		byte[] sendData = new byte[100];
		while(true) {
			//Tao goi rong de nhan du lieu tu client
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			//Nhan dia chi tu client
			serverSocket.receive(receivePacket);
			//Lay dia chi IP cua may client
			InetAddress IPAdress = receivePacket.getAddress();
			//Lay port cua chuong trinh client
			int port = receivePacket.getPort();
			//Lay ngay gio de gui nguoc lai client
			String request = new String(receivePacket.getData());
			System.out.println(request);
			if(request.trim().equals("getDate")) 
				sendData = new Date().toString().getBytes();
			else sendData ="Server not know what you want?".getBytes();
			System.out.println(new Date().toString());
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length,IPAdress,port);
			//Gui du lieu lai cho client
			serverSocket.send(sendPacket);
		}
	}
}
