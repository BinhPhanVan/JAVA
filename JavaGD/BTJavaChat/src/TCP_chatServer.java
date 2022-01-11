import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class TCP_chatServer {
	public static ArrayList<Socket> listClient;
	public static ServerSocket server;
	public static void main(String[] args) throws IOException {
		listClient= new ArrayList<Socket>();
		server= new ServerSocket(8888);
		System.out.println("SERVER is LISTENING . . . .");
		Messenger messgener = new Messenger();
		messgener.start();
		while(!server.isClosed()) {
			Socket sk = server.accept();
			System.out.println(sk+" connected to Server");
			listClient.add(sk);
			Thread client = new Thread(new ServerSystem(sk));
			client.start();
		}
	}

}
class ServerSystem implements Runnable{
	private Socket socket;
	public ServerSystem(Socket sk) {
		this.socket= sk;
	}
	@Override
	public void run() {
		while(!socket.isClosed()) {
				try {
					DataInputStream din = new DataInputStream(socket.getInputStream());
					String messenger = din.readUTF();
					String port = String.valueOf(socket.getPort());
					for (Socket client  : TCP_chatServer.listClient) {		
						if(client.isConnected()) {
							DataOutputStream dos = new DataOutputStream(client.getOutputStream());
							dos.writeUTF("\nClient "+ port +" :"+messenger);
							dos.flush();
						}
					}
				}
				catch(Exception ex) {
					TCP_chatServer.listClient.remove(socket);
				}
		}
	}
}
class Messenger extends Thread {

	public void run() {
		try {
			Scanner sc = new Scanner(System.in);
			while (true) {
				System.out.print("Server: ");
				String messenger = sc.nextLine();
				for (Socket client : TCP_chatServer.listClient) {
					DataOutputStream dos = new DataOutputStream(client.getOutputStream());	
					dos.writeUTF("\nServer: " + messenger);
					dos.flush();
					sc = sc.reset();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
