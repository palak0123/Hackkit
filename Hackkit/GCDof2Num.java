package myproject;
import java.util.*;

public class GCDof2Num {
	
	public static void main(String[]args)
	{
		Scanner scan = new Scanner(System.in);
		int n, m;
		System.out.println("Enter 2 integers: ");
		n = scan.nextInt();
		m = scan.nextInt();
		
		System.out.println("GCD of 2 numbers: " + compute(n, m));
	}
	
	public static int compute(int a, int b) {
		if(b != 0)
			return compute(b, a%b);
		else 
			return a;
	}
}