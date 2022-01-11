import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;

public class chatServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(7090);
		System.out.print("Server is started");
		while(true)
		{
			Socket socket = server.accept();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String time = new Date(0).toString();
			dos.writeUTF("Server reponse:   "+time);
			socket.close();
		}
	}

}
