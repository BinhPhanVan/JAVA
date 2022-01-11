import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class chatClient {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost",8888);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			String mesage = input.nextLine();
			dos.writeUTF("Client: "+mesage);
			dos.flush();
			String reply = dis.readUTF();
			System.out.println(reply);
			input = input.reset();
		}

	}
}
