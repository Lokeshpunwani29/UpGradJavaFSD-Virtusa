package com.leave.main;

import com.leave.bal.LeaveBal;
import com.leave.exception.LeaveException;
import com.leave.model.LeaveDetails;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class LeaveMain {
    private static final LeaveBal bal = new LeaveBal();
    private static final Scanner sc = new Scanner(System.in);
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("Welcome to Employee Leave Management System!");
            System.out.println("1. Add Leave");
            System.out.println("2. Show Leave");
            System.out.println("3. Search Leave");
            System.out.println("4. Update Leave");
            System.out.println("5. Delete Leave");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");
            try {
                choice = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Try again.");
                continue;
            }

            try {
                switch (choice) {
                    case 1 -> addLeave();
                    case 2 -> showLeave();
                    case 3 -> searchLeave();
                    case 4 -> updateLeave();
                    case 5 -> deleteLeave();
                    case 6 -> {
                        System.out.println("Exiting...");
                        return;
                    }
                    default -> System.out.println("Invalid choice");
                }
            } catch (LeaveException | ParseException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (true);
    }

    private static void addLeave() throws ParseException, LeaveException {
        System.out.print("Enter leaveId: ");
        int leaveId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter empId: ");
        int empId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter leaveStartDate (yyyy-MM-dd): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Enter leaveEndDate (yyyy-MM-dd): ");
        Date end = sdf.parse(sc.nextLine());
        System.out.print("Enter leaveReason: ");
        String reason = sc.nextLine();

        LeaveDetails leave = new LeaveDetails(leaveId, empId, start, end, reason);
        String res = bal.addLeave(leave);
        System.out.println(res);
    }

    private static void showLeave() {
        List<LeaveDetails> list = bal.showLeave();
        if (list.isEmpty()) System.out.println("No leaves found.");
        else list.forEach(System.out::println);
    }

    private static void searchLeave() {
        System.out.print("Enter leaveId: ");
        int id = Integer.parseInt(sc.nextLine());
        LeaveDetails l = bal.searchLeave(id);
        System.out.println(l == null ? "Not found" : l);
    }

    private static void updateLeave() throws ParseException, LeaveException {
        System.out.print("Enter leaveId to update: ");
        int leaveId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter empId: ");
        int empId = Integer.parseInt(sc.nextLine());
        System.out.print("Enter leaveStartDate (yyyy-MM-dd): ");
        Date start = sdf.parse(sc.nextLine());
        System.out.print("Enter leaveEndDate (yyyy-MM-dd): ");
        Date end = sdf.parse(sc.nextLine());
        System.out.print("Enter leaveReason: ");
        String reason = sc.nextLine();

        LeaveDetails leave = new LeaveDetails(leaveId, empId, start, end, reason);
        String res = bal.updateLeave(leave);
        System.out.println(res);
    }

    private static void deleteLeave() {
        System.out.print("Enter leaveId to delete: ");
        int id = Integer.parseInt(sc.nextLine());
        String res = bal.deleteLeave(id);
        System.out.println(res);
    }
}
