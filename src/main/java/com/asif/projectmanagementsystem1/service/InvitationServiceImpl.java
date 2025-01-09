package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Invitation;
import com.asif.projectmanagementsystem1.repository.InvitationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class InvitationServiceImpl implements InvitationService{
    @Autowired
    private final EmailService emailService;
    @Autowired
    private InvitationRepository invitationRepository;

    public InvitationServiceImpl(EmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public void sendInvitation(String email, Long projectId) throws Exception {
        String invitationToken= UUID.randomUUID().toString();
        Invitation invitation=new Invitation();
        invitation.setEmail(email);
        invitation.setProjectId(projectId);
        invitation.setToken(invitationToken);
        String invitationLink="http://localhost:5173/invitation/"+invitationToken;
        emailService.sendEmailWithToken(email,invitationLink);
    }

    @Override
    public Invitation acceptInvitation(String token,Long userId) throws Exception {
        Invitation invitation=invitationRepository.findByToken(token);
        if(invitation==null){
            throw new Exception("Invitation not found");
        }
        return null;
    }

    @Override
    public String getTokenByUserMail(String email) throws Exception {
        Invitation invitation=invitationRepository.findByEmail(email);

        return invitation.getToken();
    }

    @Override
    public void deleteToken(String token) throws Exception {
        Invitation invitation=invitationRepository.findByToken(token);
        invitationRepository.delete(invitation);
    }
}
