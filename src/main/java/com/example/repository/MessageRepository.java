package com.example.repository;

import com.example.entity.Message;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

// User created query methods using JPARepository interface
// Marks as repository bean
@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    
    // returns list of messages by a given number for postedBy column
    List<Message> findByPostedBy(int postedBy);
}
