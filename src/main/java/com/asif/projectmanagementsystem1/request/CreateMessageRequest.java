package com.asif.projectmanagementsystem1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class CreateMessageRequest {
    private Long senderId;
    private String content;
    private Long projectId;

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public CreateMessageRequest(Long senderId, String content, Long projectId) {
        this.senderId = senderId;
        this.content = content;
        this.projectId = projectId;
    }

    public CreateMessageRequest() {
    }
}
