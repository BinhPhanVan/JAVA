import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Bai2 {
	public String Nhap() throws IOException
	{
		InputStreamReader lv = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(lv);
		return br.readLine();
	}
	public int SumOfNumber(int number)
	{
		int s=0;
		while(number!=0)
		{
			s+=number%10;
			number/=10;
		}
		return s;
	}
	public void CheckFibo(int number)
	{
		int a=1;
		int b=1, tmp;
		while(number>b)
		{
			tmp =b;
			b=a+b;
			a= tmp;
		}
		if(b==number || number == a)
			System.out.println("Number is FiboNumber");
		else
			System.out.println("Number isn't FiboNumber");
	}
	public int InverseNumber(int number)
	{
		int s=0;
		while(number!=0)
		{
			s+= number%10;
			number/=10;
			if(number!=0)
			{
				s*=10;
			}
		}
		return s;
	}
	public void CheckSymmetry(int number)
	{
		if(number == InverseNumber(number))
			System.out.println("Number is SymmetryNumber");
		else
			System.out.println("Number isn't SymmetryNumber");
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		// TODO Auto-generated method stub
		Bai2 input = new Bai2();
		System.out.print("Number is : ");
		int number =  Integer.parseInt(input.Nhap());
		System.out.println("Sum of number is : "+ input.SumOfNumber(number));
		System.out.println("Inverse of number is : "+ input.InverseNumber(number));
		input.CheckFibo(number);
		input.CheckSymmetry(number);
	}

}
