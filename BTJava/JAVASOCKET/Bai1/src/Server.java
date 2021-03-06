import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Date;
import java.time.LocalDateTime;

public class Server {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(7090);
		System.out.print("Server is started");
		while(true)
		{
			Socket socket = server.accept();
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			int hour = LocalDateTime.now().getHour();
			int minute = LocalDateTime.now().getMinute();
			int second = LocalDateTime.now().getSecond();
//			String time = new DateTime().toString();
			dos.writeUTF("Server reponse:   "+ hour +" : "+ minute +" : "+ second);
//			Thread.sleep(1000);
//			socket.close();
		}	
	}

}
