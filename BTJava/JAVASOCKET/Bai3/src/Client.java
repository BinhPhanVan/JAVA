import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client {
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost",8008);
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		Scanner input = new Scanner(System.in);
		while(true)
		{
			System.out.print("Client: ");
			String mesage = input.nextLine();
			dos.writeUTF(mesage);
			dos.flush();
			String reply = dis.readUTF();
			System.out.println(reply);
			input = input.reset();
		}

	}
}
