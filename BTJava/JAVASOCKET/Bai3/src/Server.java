import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class Server {
	public static String Reverse(String text)
	{
		String tmp="";
		for(int i=text.length()-1;i>=0;i--)
		{
			tmp+= text.charAt(i);
		}
		return tmp;
	}
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
	public static String CountWordRepeat(String text)
	{
		String tmp = "";
		ArrayList<String> words = new ArrayList<String>();
		String[] arr= text.split("\\s+");
		for(String s : arr){    
            words.add(s);    
        }
		int count;
		for(int i = 0; i < words.size(); i++){    
	            count = 1;       
	            for(int j = i+1; j < words.size(); j++){    
	                if(words.get(i).equals(words.get(j))){
	                	words.remove(words.get(j));
	                    count++;    
	                }     
	            }
	            tmp += "Word : "+ words.get(i) + " repeate : " + count+"\n";
	            
		}
		return tmp;
	}

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		ServerSocket server = new ServerSocket(8008);
		System.out.println("Server is started");
		Socket socket = server.accept();
		DataInputStream dis = new DataInputStream(socket.getInputStream());
		DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
		while(true)
		{
			String mesage = "";
			String request = dis.readUTF();
			System.out.println("Client  : " + request);
			mesage += "Chuoi nghich dao   la: " + Reverse(request.toString());
			mesage +="\nChuoi hoa         la: " + request.toUpperCase();
			mesage +="\nChuoi thuong      la: " + request.toLowerCase();
			mesage +="\nChuoi hoa, thuong la: " + Transition(request);
			mesage +="\nSo tu trong chuoi la: " + CountWord(request);
			mesage += "\n" + CountWordRepeat(request);
			dos.writeUTF("Server: \n"+mesage);
		}

	}

}
