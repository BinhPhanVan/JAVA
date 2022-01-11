import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Date;

public class serverString {
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
	            tmp += "Word : "+ words.get(i) + " repeate : " + count +"\n";
	            
		}
		return tmp;
	}
	public static String capitalize(String string) {
//		   return Character.toUpperCase(line.charAt(0)) + line.substring(1);
		char[] chars = string.toLowerCase().toCharArray();
		  boolean found = false;
		  for (int i = 0; i < chars.length; i++) {
		    if (!found && Character.isLetter(chars[i])) {
		      chars[i] = Character.toUpperCase(chars[i]);
		      found = true;
		    } else if (Character.isWhitespace(chars[i]) || chars[i]=='.' || chars[i]=='\'') { // You can add other chars here
		      found = false;
		    }
		  }
		  return String.valueOf(chars);
		}
	public static int DemNguyenAm(String s) {
		int dem =0;
		for(int i=0;i<s.length();i++) {
			if(s.charAt(i)=='u' ||s.charAt(i)=='e'||s.charAt(i)=='o'||s.charAt(i)=='a'||s.charAt(i)=='i') {
				dem++;
			}
		}
		return dem;
	}
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		DatagramSocket serverSocket = new DatagramSocket(7090);
		System.out.println("Server is started!!");
		byte[] receiveData = new byte[1024];
		byte[] sendData = new byte[1024];
		while(true)
		{
			DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
			serverSocket.receive(receivePacket);
			InetAddress IPAddress = receivePacket.getAddress();
			int port = receivePacket.getPort();
			String request = new String(receivePacket.getData(), "UTF-8");
			String mesage = "";
			System.out.println("Client  : " + request);
			mesage += "Chuoi nghich dao la: " + Reverse(request.toString()).trim();
			mesage +="\nChuoi hoa        la: " + request.toUpperCase().trim();
			mesage +="\nChuoi thuong     la: " + request.toLowerCase().trim();
			mesage +="\nIn hoa ki tu dau tien la: " + capitalize(request.toString()).trim();
			mesage +="\nChuoi hoa, thuong la: " + Transition(request).trim();
			mesage +="\nSo tu trong chuoi la: " +  String.valueOf(CountWord(request)).trim();
		
			mesage += "\nDem nguyen am: " + String.valueOf(DemNguyenAm(request)).trim();
			
			sendData = mesage.getBytes();
			
			DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port );
			serverSocket.send(sendPacket);
		}
	}
}
