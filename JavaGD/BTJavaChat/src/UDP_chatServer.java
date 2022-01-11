import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UDP_chatServer {
	private int port;
	private InetAddress clientIP;
	private int clientPort;
	public static Map<DatagramPacket, Integer> ListSK;

	public UDP_chatServer(int port) {
		this.port = port;
	}
	public static void main(String[] args) throws Exception {
		ListSK = new HashMap<DatagramPacket, Integer>();
		UDP_chatServer server = new UDP_chatServer(7799);
		server.execute();
	}
	private void execute() throws Exception {
		DatagramSocket server = new DatagramSocket(port);
		WriteServer write = new WriteServer(server);
		write.start();
		System.out.println("Server is Listening....");
		while (true) {
			String sms = receiveData(server);
			for (DatagramPacket item : ListSK.keySet()) {
				sendData(sms, server, item.getAddress(), item.getPort());
			}
			System.out.println(sms);
		}
	}

	private String receiveData(DatagramSocket server) throws Exception {
		byte[] buffer = new byte[1024];
		DatagramPacket recieve_Packet = new DatagramPacket(buffer, buffer.length);
		server.receive(recieve_Packet);
		addClient(recieve_Packet);
		return new String(recieve_Packet.getData()).trim();
	}

	private void addClient(DatagramPacket packet) {
		for (DatagramPacket item : ListSK.keySet()) {
			if (item.getAddress().equals(packet.getAddress()) && item.getPort() == packet.getPort()) {
				ListSK.replace(item, 0);
				return;
			}
		}
		ListSK.put(packet, 0);
	}

	private void sendData(String value, DatagramSocket server, InetAddress clientIP, int clientPort)
			throws IOException {
		byte[] buffer = new byte[1024];
		buffer = value.getBytes();
		DatagramPacket send_Packet = new DatagramPacket(buffer, buffer.length, clientIP, clientPort);
		server.send(send_Packet);
	}

}

class WriteServer extends Thread {
	private DatagramSocket server;

	public WriteServer(DatagramSocket server) {
		this.server = server;
	}

	@Override
	public void run() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			String sms = sc.nextLine();
			try {
				for (DatagramPacket packet : UDP_chatServer.ListSK.keySet()) {
					sendData("\nServer: " + sms, server, packet.getAddress(), packet.getPort());
				}
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private void sendData(String value, DatagramSocket server, InetAddress clientIP, int clientPort)
			throws IOException {
		byte[] buffer = new byte[1024];
		buffer = value.getBytes();
		DatagramPacket send_Packet = new DatagramPacket(buffer, buffer.length, clientIP, clientPort);
		server.send(send_Packet);
	}
}
