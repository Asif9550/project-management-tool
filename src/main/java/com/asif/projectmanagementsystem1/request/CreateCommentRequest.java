package com.asif.projectmanagementsystem1.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CreateCommentRequest {
    private Long issueId;
    private String content;

    public Long getIssueId() {
        return issueId;
    }

    public String getContent() {
        return content;
    }
}
