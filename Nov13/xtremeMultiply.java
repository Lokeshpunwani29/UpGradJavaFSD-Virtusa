package com.java.dailyPractice.Nov13;
import java.util.*;

public class xtremeMultiply {

    public static String multiply(String num1, String num2) {

        if (num1.equals("0") || num2.equals("0")) return "0";

        int n1 = num1.length();
        int n2 = num2.length();

        int[] result = new int[n1 + n2];

        for (int i = n1 - 1; i >= 0; i--) {
            for (int j = n2 - 1; j >= 0; j--) {
                int digit1 = num1.charAt(i) - '0';
                int digit2 = num2.charAt(j) - '0';

                int product = digit1 * digit2;
                int posLow = i + j + 1;
                int posHigh = i + j;

                product += result[posLow];
                result[posLow] = product % 10;
                result[posHigh] += product / 10;
            }
        }
        
        StringBuilder sb = new StringBuilder();
        boolean leadingZero = true;

        for (int val : result) {
            if (val == 0 && leadingZero) continue;
            leadingZero = false;
            sb.append(val);
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter first 100-digit number:");
        String a = sc.nextLine();

        System.out.println("Enter second 100-digit number:");
        String b = sc.nextLine();

        String ans = multiply(a, b);

        System.out.println("\nProduct:");
        System.out.println(ans);
    }
}

