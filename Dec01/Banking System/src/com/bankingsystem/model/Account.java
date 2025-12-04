package com.bankingsystem.model;

public class Account {

    private String customerName;
    private String accountNumber;
    private double accountBalance;

    public Account() {
    }

    public Account(String customerName, String accountNumber, double accountBalance) {
        this.customerName = customerName;
        this.accountNumber = accountNumber;
        this.accountBalance = accountBalance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getAccountNumber() {
        return accountNumber;
    }


    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }


    public synchronized void deposit(double amount) {
        this.accountBalance += amount;
    }


    public synchronized void withdraw(double amount) {
        this.accountBalance -= amount;
    }

    @Override
    public String toString() {
        return "Account {" +
                "customerName='" + customerName + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", accountBalance=" + accountBalance +
                '}';
    }
}
