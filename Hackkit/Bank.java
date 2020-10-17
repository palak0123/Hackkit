import java.util.Scanner;
class Account 
{
	int ano;
	float bal;
	public float getBal() 
	{
		return bal;
	}
	public void setBal(float balance) 
	{
		bal = balance;
	}
}

class savings extends Account 
{
	savings(int accnum) 
	{
		ano = accnum;
		bal = 0;
		System.out.println("--Savings account created--");
		System.out.println("Acc. No.: " + ano);
		System.out.println("Balance: " + bal);
	}

	public static void compound(float rate, float time, float principle) 
	{
		float compoundInterest = (float) (principle * Math.pow((1 + rate / 100), time));
		System.out.println("The Compound Interest is : " + compoundInterest);
	}
}

class current extends Account
{
	float min;
	current(int accnum, float amt)
	{
		ano = accnum;
		bal = 0;
		min = amt;
		System.out.println("--Current account created--");
		System.out.println("Acc. No.: " + ano);
		System.out.println("Balance: " + bal + " (Please add balance)");
		System.out.println("Minimum Balance: " + min);
	}
	public void withdraw(float amt) 
	{
		if (bal < min)
			System.out.println("Below minimum balance!");
		else {
			bal = bal - amt;
			System.out.println("New Balance: " + this.getBal());
		}
	}
}

public class Bank 
{
	public static void main(String args[])
	{
		Scanner in = new Scanner(System.in);
		savings s1 = new savings(1);
		System.out.print("Enter Amt: ");
		float amt = in.nextFloat();
		s1.setBal(amt);
		System.out.println("Updated Balance: " + s1.getBal());
		System.out.println("\n" + "Compound Interest:");
		System.out.print("Enter principle: ");
		float principle = in.nextFloat();
		System.out.print("Enter rate: ");
		float rate = in.nextFloat();
		System.out.print("Enter time: ");
		float time = in.nextFloat();
		savings.compound(rate, time, principle);	
		System.out.println("\n" + "Current account creation: ");
		System.out.print("Enter minimum Balance: ");
		amt = in.nextFloat();
		current c1 = new current(2, amt);
		System.out.print("Enter a balance: ");
		float b = in.nextFloat();
		c1.setBal(b);
		System.out.println("New Balance: " + c1.getBal());
		System.out.print("Enter a withdraw Amt: ");
		amt = in.nextFloat();
		c1.withdraw(amt);
	}
}