package com.restapp.accountmanager.service;

import com.restapp.accountmanager.model.Account;

public interface AccountService {
    void saveAccount(Account account);
    void updateAccount(Account account);
    void deleteAccountById(int accountId);
    Account findById(int accountId);
    Iterable<Account> findAllAccount();
    boolean isAccountExist(Account account);
    void deleteAllAccounts();
}
