package com.asif.projectmanagementsystem1.controller;

import com.asif.projectmanagementsystem1.model.Chat;
import com.asif.projectmanagementsystem1.model.Message;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.request.CreateMessageRequest;
import com.asif.projectmanagementsystem1.service.MessageService;
import com.asif.projectmanagementsystem1.service.ProjectService;
import com.asif.projectmanagementsystem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    @Autowired
    private MessageService messageService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProjectService projectService;
    @PostMapping("/send")
    public ResponseEntity<Message> sendMessage(@RequestBody CreateMessageRequest request) throws Exception{
        User user=userService.findUserById(request.getSenderId());
        Chat chats=projectService.getProjectById(request.getProjectId()).getChat();
        if(chats==null){
            throw new Exception("Chat not found");
        }
        Message sentMessage=messageService.sendMessage(request.getSenderId(),
                request.getProjectId(),request.getContent());
        return ResponseEntity.ok(sentMessage);
    }
    @GetMapping("/chat/{projectId}")
    public ResponseEntity<List<Message>> getMessagesByChatId(@PathVariable Long projectId)throws Exception{
        List<Message> messages=messageService.getMessagesByProjectId(projectId);
        return ResponseEntity.ok(messages);
    }
}
