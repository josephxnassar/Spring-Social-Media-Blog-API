package com.example.service;

import com.example.entity.Account;
import com.example.exception.UsernameAlreadyExistsException;
import com.example.repository.AccountRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) {
        if (this.accountRepository.existsByUsername(account.getUsername())) {
            throw new UsernameAlreadyExistsException("Username already exists.");
        }
        if (!account.getUsername().isBlank() && account.getPassword().length() > 3) {
            return this.accountRepository.save(account);
        }
        return null;
    }

    public Account loginAccount(Account account) {
        return this.accountRepository.findByUsernameAndPassword(account.getUsername(), account.getPassword());
    }
}
