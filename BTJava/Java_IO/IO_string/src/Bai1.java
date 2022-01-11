import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Bai1 {
	public static String Nhap() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		return br.readLine();
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
	public static void CountWordRepeat(String text)
	{
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
	            System.out.println("Word : "+ words.get(i) + " repeate : " + count);
	            
		}
	}
	public static void main(String[] args) throws IOException {
		Bai1 input = new Bai1();
		System.out.print("Hay nhap chuoi ki tu :");
		String text = input.Nhap();
		System.out.println("Chuoi nghich dao la: " + input.Reverse(text));
		System.out.println("Chuoi hoa        la: " + text.toUpperCase());
		System.out.println("Chuoi thuong     la: " + text.toLowerCase());
		System.out.println("Chuoi hoa, thuong la: " + input.Transition(text));
		System.out.println("So tu trong chuoi la: " + input.CountWord(text));
		input.CountWordRepeat(text);
		
	}

}
