package com.example.repository;

import com.example.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// User created query methods using JPARepository interface
// Marks as repository bean
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

    // returns boolean if username exists is database username column
    boolean existsByUsername(String username);   
    
    // returns account object if username and password exist in their respective database columns
    Account findByUsernameAndPassword(String username, String password);
}
