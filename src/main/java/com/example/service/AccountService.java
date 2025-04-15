package com.example.service;

import com.example.entity.Account;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// Marks as service bean
@Service
public class AccountService {

    // Instance to access account repository
    private final AccountRepository accountRepository;

    // Constructor marked to be controlled by spring
    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    // Return result of Account's repository layer built-in operation save() for given account object (no accountID) using custom query method existsByUsername()
    public Account addAccount(Account account) {
        if (this.accountRepository.existsByUsername(account.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        if (!account.getUsername().isBlank() && account.getPassword().length() > 3) {
            return this.accountRepository.save(account);
        }
        return null;
    }

    // Returns result of Account's repository layer user created query method findByUsernameAndPassword() given account object
    public Account loginAccount(Account account) {
        return this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
