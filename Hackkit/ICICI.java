import java.util.*;
class ICICI implements CreditCardAPP
{
    String name,mob,address,nominee,age,email;
    double grossSal,netSal,perYearIncome,EMI;
    public void getPersonalDetails()
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter name,mob,address,nominee,age and email of the customer");
        name=in.nextLine();
        mob=in.nextLine();
        address=in.nextLine();
        nominee=in.nextLine();
        age=in.nextLine();
        email=in.nextLine();
    }
    public void calculatePerYearIncome()
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter gross salary");
        grossSal=in.nextDouble();
        netSal=grossSal-(0.2*grossSal);
        perYearIncome=netSal*12;
    }
    public void printEligibility()
    {
        Scanner in=new Scanner(System.in);
        System.out.println("Enter EMI paid per month");
        EMI=in.nextDouble();
        if(EMI==0)
        System.out.println("Eligibility score is: 3");
        else if(EMI<(0.2*perYearIncome))
        System.out.println("Eligibility score is: 2");
        else if(EMI<(0.4*perYearIncome))
        System.out.println("Eligibility score is: 1");
        else if(EMI<(0.6*perYearIncome))
        System.out.println("Eligibility score is: 0");
    }
}
class testInterface
{
    public static void main(String[] args)
    {
        CreditCardAPP ic=new ICICI();
        CreditCardAPP hd=new HDFC();
        CreditCardAPP ax=new AXIS();
        ic.getPersonalDetails();
        ic.calculatePerYearIncome();
        ic.printEligibility();
    }
}