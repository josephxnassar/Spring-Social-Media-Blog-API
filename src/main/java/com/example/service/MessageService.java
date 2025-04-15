package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

// Marks as service bean
@Service
public class MessageService {
    // Instances to access repository layer
    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;

    // Constructor marked to be controlled by spring
    @Autowired
    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    // Return the result of Message's repository layer built-in operation for save() for given message object (no messageId)
    public Message addMessage(Message message) {
        if (this.accountRepository.existsById(message.getPostedBy()) && !message.getMessageText().isBlank() && message.getMessageText().length() < 256) {
            return this.messageRepository.save(message);
        }
        return null;
    }

    // Return the result of Message's repository layer built-in operation for findAll()
    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    // Return the result of Message's repository layer build-in operation for findById() for given message_id
    public Message getMessage(int message_id) {
        return this.messageRepository.findById(message_id).orElse(null);
    }

    // Return number of rows deleted by Message's repository layer built-in operation for deleteById for given message_id
    public int deleteMessage(int message_id) {
        if (messageRepository.existsById(message_id)) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;
    }

    // Returns number of rows updated by Message's repository layer built-in operation for save for given message_text and message_id
    public int modifyMessage(String message_text, int message_id) {
        if (!message_text.isBlank() && message_text.length() < 256) {
            Message message = this.getMessage(message_id);

            if (message != null) {
                message.setMessageText(message_text);
                this.messageRepository.save(message);
                return 1;
            }
        }

        return 0;
    }

    // Returns the result of Message's repository layer user created query method findByPostedBy
    public List<Message> getMessages(int user_id) {
        return this.messageRepository.findByPostedBy(user_id);
    }
}
