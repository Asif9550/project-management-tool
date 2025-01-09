package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Issue;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.request.IssueRequest;

import java.util.List;
import java.util.Optional;

public interface IssueService {
    Issue getIssueById(Long issueId) throws Exception;
    List<Issue> getIssuesByProjectId(Long projectId) throws Exception;
    Issue createIssue(IssueRequest issue, User user) throws Exception;
   // Optional<Issue> updateIssue(Long issueid,IssueRequest updatedIssue, Long userId) throws Exception;
    void deleteIssue(Long issueId, Long userId) throws Exception;
    Issue addUserToIssue(Long issueId, Long userId) throws Exception;
    Issue updateStatus(Long issueId, String status) throws Exception;
}
