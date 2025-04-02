package com.example.service;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final AccountRepository accountRepository;
    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(AccountRepository accountRepository, MessageRepository messageRepository) {
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    public Message addMessage(Message message) {
        if (this.accountRepository.existsById(message.getPostedBy()) && !message.getMessageText().isBlank() && message.getMessageText().length() < 256) {
            return this.messageRepository.save(message);
        }
        return null;
    }

    public List<Message> getAllMessages() {
        return this.messageRepository.findAll();
    }

    public Message getMessage(int message_id) {
        return this.messageRepository.findById(message_id).orElse(null);
    }

    public int deleteMessage(int message_id) {
        if (messageRepository.existsById(message_id)) {
            messageRepository.deleteById(message_id);
            return 1;
        }
        return 0;
    }

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

    public List<Message> getMessages(int user_id) {
        return this.messageRepository.findByPostedBy(user_id);
    }
}
