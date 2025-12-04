package com.java.bank.modal;

public class Account {
    private int AccountNumber;
    private String CustomerName;
    private int AccountBalance = 0;

    public int getAccountNumber() {
        return AccountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        AccountNumber = accountNumber;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String customerName) {
        CustomerName = customerName;
    }

    public int getAccountBalance() {
        return AccountBalance;
    }

    public void setAccountBalance(int accountBalance) {
        AccountBalance = accountBalance;
    }

    public Account(String customerName, int accountNumber) {
        this.CustomerName = customerName;
        this.AccountNumber = accountNumber;
    }

    @Override
    public String toString() {
        return "Account{" +
                "AccountNumber=" + AccountNumber +
                ", CustomerName='" + CustomerName + '\'' +
                ", AccountBalance=" + AccountBalance +
                '}';
    }
}
