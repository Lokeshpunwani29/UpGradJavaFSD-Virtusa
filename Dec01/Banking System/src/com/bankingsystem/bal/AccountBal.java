package com.bankingsystem.bal;

import com.bankingsystem.dao.AccountDaoImpl;
import com.bankingsystem.exceptions.*;
import com.bankingsystem.model.Account;

import java.util.List;
import java.util.Random;

public class AccountBal {

    private final AccountDaoImpl accountDao;
    private final Random random;

    public AccountBal(AccountDaoImpl accountDao) {
        this.accountDao = accountDao;
        this.random = new Random();
    }

    private String generateAccountNumber(String customerName) {
        String[] parts = customerName.trim().split("\\s+");
        StringBuilder initials = new StringBuilder();
        for (String p : parts) {
            initials.append(Character.toUpperCase(p.charAt(0)));
        }

        String accNo;
        do {
            int num = 1000 + random.nextInt(9000);
            accNo = initials + String.valueOf(num);
        } while (accountDao.existsByAccountNumber(accNo));

        return accNo;
    }

    public Account createAccount(String customerName) {
        try {
            if (customerName == null || customerName.trim().isEmpty()) {
                throw new InvalidNameException("Customer name cannot be empty.");
            }
            String accNo = generateAccountNumber(customerName);
            Account account = new Account(customerName.trim(), accNo, 0.0);
            accountDao.createAccount(account);
            System.out.println("Account created successfully. Account Number: " + accNo);
            return account;
        } catch (InvalidNameException e) {
            System.out.println("Error in account creation: " + e.getMessage());
            return null;
        }
    }

    public void deposit(String accountNumber, double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Deposit amount must be positive.");
            }
            Account account = accountDao.getAccountByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found: " + accountNumber);
            }

            account.deposit(amount);
            accountDao.updateAccount(account);

            System.out.println("Deposit successful. New balance: " + account.getAccountBalance());

        } catch (InvalidAmountException | AccountNotFoundException e) {
            System.out.println("Error in deposit: " + e.getMessage());
        }
    }

    public void withdraw(String accountNumber, double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Withdrawal amount must be positive.");
            }
            Account account = accountDao.getAccountByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found: " + accountNumber);
            }

            try {
                if (account.getAccountBalance() < amount) {
                    throw new InsufficientBalanceException("Insufficient balance for withdrawal.");
                }

                account.withdraw(amount);
                accountDao.updateAccount(account);

                System.out.println("Withdrawal successful. New balance: " + account.getAccountBalance());
            } catch (InsufficientBalanceException e) {
                System.out.println("Error in withdrawal: " + e.getMessage());
            } finally {
            }

        } catch (InvalidAmountException | AccountNotFoundException e) {
            System.out.println("Error in withdrawal: " + e.getMessage());
        }
    }

    public void transfer(String sourceAccNo, String destAccNo, double amount) {
        try {
            if (amount <= 0) {
                throw new InvalidAmountException("Transfer amount must be positive.");
            }

            Account source = accountDao.getAccountByNumber(sourceAccNo);
            Account dest = accountDao.getAccountByNumber(destAccNo);
            if (source == null) {
                throw new AccountNotFoundException("Source account not found: " + sourceAccNo);
            }
            if (dest == null) {
                throw new AccountNotFoundException("Destination account not found: " + destAccNo);
            }

            Account firstLock = sourceAccNo.compareTo(destAccNo) < 0 ? source : dest;
            Account secondLock = (firstLock == source) ? dest : source;

            synchronized (firstLock) {
                synchronized (secondLock) {
                    if (source.getAccountBalance() < amount) {
                        throw new InsufficientBalanceException("Insufficient balance for transfer.");
                    }

                    source.withdraw(amount);
                    dest.deposit(amount);

                    accountDao.updateAccount(source);
                    accountDao.updateAccount(dest);
                }
            }

            System.out.println("Transfer successful.");
            System.out.println("Source New Balance: " + source.getAccountBalance());
            System.out.println("Destination New Balance: " + dest.getAccountBalance());

        } catch (InvalidAmountException | AccountNotFoundException | InsufficientBalanceException e) {
            System.out.println("Error in transfer: " + e.getMessage());
        }
    }

    public void showBalance(String accountNumber) {
        try {
            Account account = accountDao.getAccountByNumber(accountNumber);
            if (account == null) {
                throw new AccountNotFoundException("Account not found: " + accountNumber);
            }
            System.out.println("Account Number: " + account.getAccountNumber());
            System.out.println("Customer Name: " + account.getCustomerName());
            System.out.println("Current Balance: " + account.getAccountBalance());
        } catch (AccountNotFoundException e) {
            System.out.println("Error in balance inquiry: " + e.getMessage());
        }
    }

    public void listAllAccounts() {
        List<Account> accounts = accountDao.getAllAccounts();
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            accounts.forEach(System.out::println);
        }
    }

    public void simulateConcurrentTransactions(String accountNumber) {
        Account account = accountDao.getAccountByNumber(accountNumber);
        if (account == null) {
            System.out.println("Cannot run concurrency demo. Account not found: " + accountNumber);
            return;
        }

        Runnable depositTask = () -> {
            for (int i = 0; i < 5; i++) {
                account.deposit(100);
            }
        };

        Runnable withdrawTask = () -> {
            for (int i = 0; i < 5; i++) {
                if (account.getAccountBalance() >= 50) {
                    account.withdraw(50);
                }
            }
        };

        Thread t1 = new Thread(depositTask, "Deposit-Thread");
        Thread t2 = new Thread(withdrawTask, "Withdraw-Thread");

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
            accountDao.updateAccount(account);
            System.out.println("Concurrent transactions finished. Final balance: " + account.getAccountBalance());
        } catch (InterruptedException e) {
            System.out.println("Concurrency demo interrupted: " + e.getMessage());
            Thread.currentThread().interrupt();
        }
    }
}