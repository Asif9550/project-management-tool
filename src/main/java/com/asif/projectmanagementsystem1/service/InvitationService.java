package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Invitation;

public interface InvitationService {
    public void sendInvitation(String email, Long projectId) throws Exception;
    public Invitation acceptInvitation(String token,Long userId) throws Exception;
    public String getTokenByUserMail(String email) throws Exception;
    void deleteToken(String token) throws Exception;
}
