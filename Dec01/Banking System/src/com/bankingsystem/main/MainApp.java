package com.bankingsystem.main;

import com.bankingsystem.bal.AccountBal;
import com.bankingsystem.dao.AccountDaoImpl;
import com.bankingsystem.exceptions.InvalidChoiceException;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MainApp {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        AccountDaoImpl accountDao = new AccountDaoImpl();
        AccountBal accountBal = new AccountBal(accountDao);

        boolean exit = false;

        while (!exit) {
            try {
                System.out.println("\n==== BANKING SYSTEM MENU ====");
                System.out.println("1. Create an account");
                System.out.println("2. Perform operations on existing account");
                System.out.println("3. List all accounts");
                System.out.println("4. Exit");
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        handleCreateAccount(accountBal);
                        break;
                    case 2:
                        handleAccountOperations(accountBal);
                        break;
                    case 3:
                        accountBal.listAllAccounts();
                        break;
                    case 4:
                        exit = true;
                        System.out.println("Exiting the program. Goodbye!");
                        break;
                    default:
                        throw new InvalidChoiceException("Invalid main menu choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid numeric choice.");
                scanner.nextLine();
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }

        scanner.close();
    }

    private static void handleCreateAccount(AccountBal accountBal) {
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        accountBal.createAccount(name);
    }

    private static void handleAccountOperations(AccountBal accountBal) {
        System.out.print("Enter account number to operate: ");
        String accountNumber = scanner.nextLine();

        boolean backToMain = false;

        while (!backToMain) {
            try {
                System.out.println("\n--- Account Operations Menu (" + accountNumber + ") ---");
                System.out.println("1. Deposit");
                System.out.println("2. Withdraw");
                System.out.println("3. Transfer");
                System.out.println("4. Show balance");
                System.out.println("5. Simulate concurrent transactions (multithreading demo)");
                System.out.println("6. Return to main menu");
                System.out.print("Enter your choice: ");

                int opChoice = scanner.nextInt();
                scanner.nextLine();

                switch (opChoice) {
                    case 1:
                        handleDeposit(accountBal, accountNumber);
                        break;
                    case 2:
                        handleWithdraw(accountBal, accountNumber);
                        break;
                    case 3:
                        handleTransfer(accountBal, accountNumber);
                        break;
                    case 4:
                        accountBal.showBalance(accountNumber);
                        break;
                    case 5:
                        accountBal.simulateConcurrentTransactions(accountNumber);
                        break;
                    case 6:
                        backToMain = true;
                        break;
                    default:
                        throw new InvalidChoiceException("Invalid account operations menu choice.");
                }

            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid numeric choice.");
                scanner.nextLine();
            } catch (InvalidChoiceException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void handleDeposit(AccountBal accountBal, String accountNumber) {
        try {
            System.out.print("Enter deposit amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            accountBal.deposit(accountNumber, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static void handleWithdraw(AccountBal accountBal, String accountNumber) {
        try {
            System.out.print("Enter withdrawal amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();
            accountBal.withdraw(accountNumber, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        }
    }

    private static void handleTransfer(AccountBal accountBal, String sourceAccountNumber) {
        try {
            System.out.print("Enter destination account number: ");
            String destAccount = scanner.nextLine();

            System.out.print("Enter transfer amount: ");
            double amount = scanner.nextDouble();
            scanner.nextLine();

            accountBal.transfer(sourceAccountNumber, destAccount, amount);
        } catch (InputMismatchException e) {
            System.out.println("Invalid amount. Please enter a valid number.");
            scanner.nextLine();
        }
    }
}
