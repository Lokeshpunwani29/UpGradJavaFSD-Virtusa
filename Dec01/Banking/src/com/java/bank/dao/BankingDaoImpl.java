package com.java.bank.dao;

import com.java.bank.modal.Account;

import java.util.ArrayList;
import java.util.List;

public class BankingDaoImpl implements BankingDao  {

    static List<Account> AccountList;

    static{
        AccountList = new ArrayList<Account>();
    }

    @Override
    public String AddAccountDao(Account account) {
        AccountList.add(account);
        return "Account Added Successfully";
    }
}
