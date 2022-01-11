import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class chatServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8888);
		System.out.println("Server is started");
		Socket socket = server.accept();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner input = new Scanner(System.in);
		while(true)
		{
			String request = dis.readUTF();
			System.out.println(request);
			System.out.print("Server: ");
			String mesage = input.nextLine();
			dos.writeUTF("Server: "+mesage);
			dos.flush();
			input = input.reset();
		}

	}

}
