package com.restapp.accountmanager.service;

import com.restapp.accountmanager.model.Account;
import com.restapp.accountmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service("accountService")
public class AccountServiceImpl implements AccountService {
    @Autowired
    UserRepository userRepository;

    @Override
    public void saveAccount(Account account) {
        userRepository.save(account);
    }

    @Override
    public void updateAccount(Account account) {
        Account currentAccount = userRepository.findById(account.getId()).get();
        if(currentAccount==null) {
            System.out.println("no update");
            return;
        }
        currentAccount.setValue(account.getValue());
        userRepository.save(currentAccount);
    }

    @Override
    public void deleteAccountById(int accountId) {
         userRepository.deleteById(accountId);
    }

    @Override
    public List<Account> findAllAccount() {
        List<Account> accounts = new ArrayList<>();
        userRepository.findAll().forEach(account -> accounts.add(account));
        return accounts;
    }

    @Override
    public Account findById(int accountId) {
        return userRepository.findById(accountId).get();
    }

    @Override
    public boolean isAccountExist(Account account) {
        return userRepository.findById(account.getId()) != null;
    }

    @Override
    public void deleteAllAccounts() {
        userRepository.deleteAll();
    }
}
