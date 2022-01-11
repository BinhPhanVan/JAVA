import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public class UDP_serverString {
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
	public static int CountWord(String text)
	{
		if(text.length()==0)
			return 0;
		return (text.split("\\s+")).length;
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
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(7091);
		System.out.println("Server UDP is started!!");
		
		while(true)
		{
			byte[] receiveData = new byte[1024];
			byte[] sendData = new byte[1024];
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData(), "UTF-8");
			String mesage = "Server UDP: ";
			mesage +="\nDao chuoi         la:"+ Reverse(request).trim();
			mesage +="\nChuoi hoa         la: " + request.toUpperCase().trim();
			mesage +="\nChuoi thuong      la: " + request.toLowerCase().trim();
			mesage +="\nChuoi hoa, thuong la: " + Transition(request).toString().trim();
			mesage +="\nSo tu trong chuoi la: " + String.valueOf(CountWord(request)).trim();
			
			sendData = mesage.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serverSocket.send(sendPacket);
		}
	}
}

