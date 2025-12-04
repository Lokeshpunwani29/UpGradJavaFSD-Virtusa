package com.bankingsystem.dao;

import com.bankingsystem.model.Account;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class AccountDaoImpl implements AccountDao {

    private final Map<String, Account> accountsMap = new ConcurrentHashMap<>();

    @Override
    public void createAccount(Account account) {
        accountsMap.put(account.getAccountNumber(), account);
    }

    @Override
    public Account getAccountByNumber(String accountNumber) {
        return accountsMap.get(accountNumber);
    }

    @Override
    public List<Account> getAllAccounts() {
        return new ArrayList<>(accountsMap.values());
    }

    @Override
    public void updateAccount(Account account) {
        // For in-memory map, this is basically same as put
        accountsMap.put(account.getAccountNumber(), account);
    }

    @Override
    public void deleteAccount(String accountNumber) {
        accountsMap.remove(accountNumber);
    }

    public List<Account> findAccountsByCustomerName(String name) {
        return accountsMap.values().stream()
                .filter(acc -> acc.getCustomerName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    public boolean existsByAccountNumber(String accountNumber) {
        return accountsMap.containsKey(accountNumber);
    }
}
