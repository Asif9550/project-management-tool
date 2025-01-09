package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Chat;
import org.springframework.stereotype.Service;


public interface ChatService {
    Chat createChat(Chat chat) throws Exception;
}
