import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCP_serverString {
	public static String Transition(String text)
	{
		String tmp = "";
		for(int i=0;i<text.length();i++)
		{
			if(text.charAt(i)>='A' && text.charAt(i)<='Z')
			{
				tmp += (char)((int) text.charAt(i)+32); 
			}
			else
			{
				if(text.charAt(i)>='a' && text.charAt(i)<='z')
					tmp += (char)((int) text.charAt(i)-32); 
				else
				{
					tmp += text.charAt(i); 
				}
			}
		}
		return tmp;
	}
	public static String Reverse(String text)
	{
		String tmp="";
		for(int i=text.length()-1;i>=0;i--)
		{
			tmp+= text.charAt(i);
		}
		return tmp;
	}
	public static int CountWord(String text)
	{
		if(text.length()==0)
			return 0;
		return (text.split("\\s+")).length;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8008);
		System.out.println("Server TCP is started");
		while(true)
		{
			Socket socket = server.accept();
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			String mesage = "";
			String request = dis.readUTF();
//			System.out.println("Client  : " + request);
			mesage +="\nDao chuoi         la:"+ Reverse(request);
			mesage +="\nChuoi hoa         la: " + request.toUpperCase();
			mesage +="\nChuoi thuong      la: " + request.toLowerCase();
			mesage +="\nChuoi hoa, thuong la: " + Transition(request).toString();
			mesage +="\nSo tu trong chuoi la: " + String.valueOf(CountWord(request));
			dos.writeUTF("Server TCP: "+mesage);
		}
	}

}
