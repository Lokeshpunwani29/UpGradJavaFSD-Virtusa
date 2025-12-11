package com.java;

import java.util.Scanner;

public class Input {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    System.out.println("Enter Your Name    ");
    String name = sc.nextLine();
    System.out.print("Name is  " +name);
    sc.close();
  }
}
