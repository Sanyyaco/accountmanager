package com.restapp.accountmanager.restcontroller;

import com.restapp.accountmanager.model.Account;
import com.restapp.accountmanager.service.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AccountRestController {
    @Autowired
    AccountServiceImpl accountService;

    //------------------- Retrieve accounts list --------------------------------------------------------

    @RequestMapping(value = "/accounts",method = GET)
    public List<Account> listAccounts() {
        List<Account> accounts = accountService.findAllAccount();
        return accounts;
    }

    //------------------- Retrieve Single Account --------------------------------------------------------
    @RequestMapping(value = "/accounts/{id}", method = GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Account> getAccount(@PathVariable("id") int id) {
        System.out.println("Fetching User with id " + id);
        Account account = accountService.findById(id);
        if (account == null) {
            System.out.println("Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Account>(account, HttpStatus.OK);
    }

    //------------------- Create an Account --------------------------------------------------------

    @RequestMapping(value = "/accounts", method = RequestMethod.POST)
    public void createAccount(
            @RequestBody Account account) {

        accountService.saveAccount(account);
    }

    //------------------- Update an Account --------------------------------------------------------

    @RequestMapping(value = "/accounts/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Account> updateAccount(@PathVariable("id") int id, @RequestBody Account account) {
        System.out.println("Updating Account " + id);

        Account currentAccount = accountService.findById(id);

        if (currentAccount==null) {
            System.out.println("Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        currentAccount.setValue(account.getValue());

        accountService.updateAccount(currentAccount);
        return new ResponseEntity<Account>(currentAccount, HttpStatus.OK);
    }

    //------------------- Delete an Account --------------------------------------------------------

    @RequestMapping(value = "/accounts/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAccount(@PathVariable("id") int id) {
        System.out.println("Fetching & Deleting Account with id " + id);

        Account account = accountService.findById(id);
        if (account == null) {
            System.out.println("Unable to delete. Account with id " + id + " not found");
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }

        accountService.deleteAccountById(id);
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }


    //------------------- Delete All Users --------------------------------------------------------

    @RequestMapping(value = "/accounts/deleteAll", method = RequestMethod.DELETE)
    public ResponseEntity<Account> deleteAllAccounts() {
        System.out.println("Deleting All Account");

        accountService.deleteAllAccounts();
        return new ResponseEntity<Account>(HttpStatus.NO_CONTENT);
    }


}
