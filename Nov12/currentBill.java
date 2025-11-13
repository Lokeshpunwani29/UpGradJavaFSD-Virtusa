import java.util.Scanner;

public class currentBill {
    public double billCalc(int unit) {
        double billAmount = 0;
        double rate;

        if (unit <= 90) {
            rate = 1;
            billAmount = unit * rate;
        }

        if (unit > 90) {
            rate = 1;
            billAmount = 90 * rate;
            if (unit - 150 >= 0) {
                rate = 1.5;
                billAmount = billAmount + (60 * rate);
            } else {
                rate = 1.5;
                billAmount = billAmount + (unit - 90) * rate;
            }
        }
        if (unit > 150) {
            rate = 2;
            if (unit - 200 >= 0) {
                billAmount = billAmount + (50 * rate);
            } else {
                billAmount = billAmount + (unit - 150) * rate;
            }
            if (unit > 200) {
                rate = 2.5;
                if (unit - 200 >= 0) {
                    billAmount = billAmount + (40 * rate);
                } else {
                    billAmount = billAmount + (unit - 200) * rate;
                }
            }
            return billAmount;
        }
        
        public static void main (String args[])
        {
            Scanner sc = new Scanner(System.in);
            System.out.print("Enter the unit-> ");
            int unit = sc.nextInt();
            currentBill obj = new currentBill();
            System.out.println("Total Bill for " + unit + " unit is " + obj.billCalc(unit));
        }
}