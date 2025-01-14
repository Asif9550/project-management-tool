package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Chat;
import com.asif.projectmanagementsystem1.model.Message;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.repository.MessageRepository;
import com.asif.projectmanagementsystem1.repository.UserRepository;
import com.asif.projectmanagementsystem1.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService{
    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProjectService projectService;
    @Override
    public Message sendMessage(Long senderId, Long projectId, String content) throws Exception {
        User sender=userRepository.findById(senderId).orElseThrow(()->new Exception("User not found"));
        Chat chat=projectService.getProjectById(projectId).getChat();
        Message message=new Message();
        message.setSender(sender);
        message.setChat(chat);
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        Message savedMessage=messageRepository.save(message);
        chat.getMessages().add(savedMessage);
        return savedMessage;
    }

    @Override
    public List<Message> getMessagesByProjectId(Long projectId) throws Exception {
        Chat chat=projectService.getChatByProjectId(projectId);
        List<Message> findByChatIdOrderByCreatedAtAsc=messageRepository.findByChatIdOrderByCreatedAtAsc(chat.getId());
        return findByChatIdOrderByCreatedAtAsc;
    }
}
