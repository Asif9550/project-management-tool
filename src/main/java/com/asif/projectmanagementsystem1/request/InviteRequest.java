package com.asif.projectmanagementsystem1.request;

import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


public class InviteRequest {
    private Long projectId;
    private String email;

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public InviteRequest(Long projectId, String email) {
        this.projectId = projectId;
        this.email = email;
    }

    public InviteRequest() {
    }
}
