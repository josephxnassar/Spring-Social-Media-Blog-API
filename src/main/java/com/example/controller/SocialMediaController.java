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

// Contains Controller and ResponseBody annotations -> ResponseBody binds return value to the response body
 @RestController
public class SocialMediaController {
    // Instances to access service layer
    private final AccountService accountService;
    private final MessageService messageService;

    // Constructor marked to be controlled by spring
    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    // register
    // Deserializes the request body into an account object and tasks the service layer to add it to the database
    // Return (success): response entity containing JSON of account with status 200
    // Return (fail - duplicate username): response entity containing status 409
    // Return (fail - other): response entity containing status 400
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

    // login
    // Deserializes the request body into an account object and tasks the service layer to verify the account with the database
    // Return (success): response entity containing JSON of account with status 200
    // Return (fail): response entity containing status 401
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account newAccount = this.accountService.loginAccount(account);
        if (newAccount != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newAccount);
        }
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    // add message
    // Deserializes the request body into an message object and tasks the service layer to add it to the database
    // Return (success): response entity containing JSON of message with status 200
    // Return (fail): response entity containing status 400
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message newMessage = this.messageService.addMessage(message);
        if (newMessage != null) {
            return ResponseEntity.status(HttpStatus.OK).body(newMessage);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // get messages
    // Tasks the service layer to return all existing messages in the database
    // Return: response entity containing JSON of messages
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = this.messageService.getAllMessages();
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }

    // get message
    // Extracts the message_id from the URL path into an integer and tasks the service layer to find the message in the database
    // Return: response entity containing JSON of message with status 200
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessage(@PathVariable int messageId) {
        Message message = this.messageService.getMessage(messageId);
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    // delete message
    // Extracts the message_id from the URL path into an integer and tasks the service layer to delete the message from the database
    // Return (success): response entity containing JSON of rows deleted with status 200
    // Return (fail): response entity containing empty JSON with status 200
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int messageId) {
        int rows = this.messageService.deleteMessage(messageId);
        return rows == 1 ? ResponseEntity.status(HttpStatus.OK).body(rows) : ResponseEntity.status(HttpStatus.OK).build();
    }

    // update message
    // Deserializes the request body into a message object and extracts the message_id from the URL path into an integer. Then tasks the service layer to update the message in the database
    // Return (success): response entity containing JSON of rows updated with status 200
    // Return (fail): response entity containing empty JSON with status 200
    // Note: map is needed to extract string from JSON
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@RequestBody Map<String, String> responseBody, @PathVariable int messageId) {
        String message_text = responseBody.get("messageText");
        int rows = this.messageService.modifyMessage(message_text, messageId);
        return rows == 1 ? ResponseEntity.status(HttpStatus.OK).body(rows) : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    // get messages for user
    // Extracts the user_id from the URL path into an integer and tasks the service layer to get all existing messages for the user in the database
    // Return: response entity containing JSON of messages
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllUserMessages(@PathVariable int accountId) {
        List<Message> messages = this.messageService.getMessages(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(messages);
    }


}
