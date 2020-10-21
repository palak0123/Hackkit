
import java.util.Scanner;

import static java.lang.Math.pow;
import static java.lang.System.exit;
import static java.lang.System.in;
class Binary2Decimal {
    public static void main(String args[]) {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter a number in binary or press -1 tp quit");
        for (; ; ) {
            int binary = input.nextInt();
            if(binary == -1) {
                exit(1);
            }
            else{
                double count = 0;
                double ans = 0 ;
                while(binary != 0){
                    double rem = binary%10 ;
                    if(rem == 1){
                        ans = ans + pow(2 , count);
                    }

                    count= count+1;
                    binary = binary/10;
                }
                System.out.println(ans);
            }
        }
    }
}
