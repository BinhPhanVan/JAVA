import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class chatClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		// TODO Auto-generated method stub
		Socket socket = new Socket("localhost", 7090);
		DataInputStream din = new DataInputStream(socket.getInputStream());
		String time = din.readUTF();
		System.out.print(time);
		socket.close();
	}

}
