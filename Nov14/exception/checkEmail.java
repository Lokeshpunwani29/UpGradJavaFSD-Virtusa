package com.java.dailyPractice.exception;

import java.util.Scanner;

public class checkEmail {
    
    public void check(String email) throws invalidEmail, invalidUserName
    {
        email = email.trim();
        if(email.equals("") || email.contains(" ")){
            throw new invalidEmail("Email is not valid may contain space or empty.");
        }
        else if (email.indexOf("@")== -1 || !email.contains(".com")) {
            throw new invalidEmail("It is not a valid Email.");
        } else if (email.substring(0,email.indexOf("@")).length() < 8 || email.substring(0,email.indexOf("@")).length() > 22) {
            throw new invalidUserName("UserName is not valid.");
        }
        System.out.println("Email is Valid:)");
    }
    
    public static void main(String[] args) {
        String email;
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Email: ");
        email = sc.nextLine();
        checkEmail obj = new checkEmail();
        try {
            obj.check(email);
        } catch (invalidEmail | invalidUserName e) {
            System.err.println(e.getMessage());
        }
    }
}
