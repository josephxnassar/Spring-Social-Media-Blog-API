package com.example.controller;

import com.example.entity.*;
import com.example.service.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

import com.example.exception.UsernameAlreadyExistsException;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

 @RestController
public class SocialMediaController {
    private final AccountService accountService;
    private final MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        try {
            Account newAccount = this.accountService.addAccount(account);
            if (newAccount != null) {
                return ResponseEntity.status(HttpStatus.OK).body(newAccount);
            }
            
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        catch (UsernameAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account newAccount = this.accountService.loginAccount(account);
        if (newAccount != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newAccount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = this.messageService.addMessage(message);
        if (newMessage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = this.messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable int messageId) {
        Message message = this.messageService.getMessage(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
        int rows = this.messageService.deleteMessage(messageId);
        return rows == 1 ? ResponseEntity.status(HttpStatus.OK).body(rows) : ResponseEntity.status(HttpStatus.OK).build();
    }

    // apparently spring doesn't know how to extract just a String field from JSON so need to use Map
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@RequestBody Map<String, String> responseBody, @PathVariable int messageId) {
        String message_text = responseBody.get("messageText");
        int rows = this.messageService.modifyMessage(message_text, messageId);
        return rows == 1 ? ResponseEntity.status(HttpStatus.OK).body(rows) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllUserMessages(@PathVariable int accountId) {
        List<Message> messages = this.messageService.getMessages(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }


}
