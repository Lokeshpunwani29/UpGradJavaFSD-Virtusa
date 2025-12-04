package com.bankingsystem.dao;

import com.bankingsystem.model.Account;

import java.util.List;

public interface AccountDao {

    void createAccount(Account account);

    Account getAccountByNumber(String accountNumber);

    List<Account> getAllAccounts();

    void updateAccount(Account account);

    void deleteAccount(String accountNumber);
}
