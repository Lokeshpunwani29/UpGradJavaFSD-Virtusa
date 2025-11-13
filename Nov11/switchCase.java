package com.java.dailyPractice.Nov10;

import java.util.Scanner;

public class switchCase {

    //Without using Yield
    public String menu2(int o)
    {
        switch (o){
            case 1 : return  "Monday";
            case 2 : return  "Tuesday";
            case 3 : return  "Wednesday";
            case 4 : return  "Thursday";
            case 5 : return  "Friday";
            case 6 : return  "Saturday";
            case 7 : return  "Sunday";
            default: return "invalid choice";
        }
    }

    // Using Yield
    public String menu(int o)
    {
        String result = switch (o){
            case 1 : yield  "Monday";
            case 2 : yield  "Tuesday";
            case 3 : yield  "Wednesday";
            case 4 : yield  "Thursday";
            case 5 : yield  "Friday";
            case 6 : yield  "Saturday";
            case 7 : yield  "Sunday";
            default: yield "invalid choice";
        };
        return result;
    }

    //Using Lamda Most Appropriate
    public String menu3(int o)
    {
        String result = switch (o){
            case 1 ->  "Monday";
            case 2 ->  "Tuesday";
            case 3 ->  "Wednesday";
            case 4 ->  "Thursday";
            case 5 ->  "Friday";
            case 6 ->  "Saturday";
            case 7 ->  "Sunday";
            default -> "invalid choice";
        };
        return result;
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Choice of Day: ");
        int o = sc.nextInt();
        switchCase obj = new switchCase();
        System.out.println(obj.menu(o));
        System.out.println(obj.menu2(o));
        System.out.println(obj.menu3(o));
    }
}
