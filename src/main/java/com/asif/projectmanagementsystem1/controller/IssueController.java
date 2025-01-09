package com.asif.projectmanagementsystem1.controller;

import com.asif.projectmanagementsystem1.dto.IssueDto;
import com.asif.projectmanagementsystem1.model.Issue;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.request.IssueRequest;
import com.asif.projectmanagementsystem1.response.AuthResponse;
import com.asif.projectmanagementsystem1.response.MessageResponse;
import com.asif.projectmanagementsystem1.service.IssueService;
import com.asif.projectmanagementsystem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/issues")
public class IssueController {
    @Autowired
    private IssueService issueService;
    @Autowired
    private UserService userService;
    @GetMapping("/{issueId}")
    public ResponseEntity<Issue> getIssueById(@PathVariable Long issueId) throws Exception {
        return ResponseEntity.ok(issueService.getIssueById(issueId));
    }
    @GetMapping("/project/{projectId}")
    public ResponseEntity<List<Issue>> getIssueByProjectId(@PathVariable Long projectId) throws Exception {
        return ResponseEntity.ok(issueService.getIssuesByProjectId(projectId));
    }
    @PostMapping("/createIssue")
    public ResponseEntity<IssueDto> createIssue(@RequestBody IssueRequest issueRequest,
                                                @RequestHeader("Authorization") String token) throws Exception {
        User tokenUser=userService.findUserProfileByJwt(token);
        User user=userService.findUserById(tokenUser.getId());
            Issue issue=issueService.createIssue(issueRequest,user);
            IssueDto issueDto=new IssueDto();
            issueDto.setId(issue.getId());
            issueDto.setTitle(issue.getTitle());
            issueDto.setProject(issue.getProject());
            issueDto.setTags(issue.getTags());
            issueDto.setAssignee(issue.getAssignee());
            issueDto.setDescription(issue.getDescription());
            issueDto.setPriority(issue.getPriority());
            issueDto.setDueDate(String.valueOf(issue.getDueDate()));
            issueDto.setStatus(issue.getStatus());
            issueDto.setProjectId((issue.getProjectID()));
            return ResponseEntity.ok(issueDto);
    }
    @DeleteMapping("/{issueId}")
    public ResponseEntity<MessageResponse> deleteIssue(@PathVariable Long issueId,
                                                       @RequestHeader("Authorization") String token) throws Exception {
        User user=userService.findUserProfileByJwt(token);
        issueService.deleteIssue(issueId,user.getId());
        return ResponseEntity.ok(new MessageResponse("Issue deleted successfully"));
    }
    @PutMapping("/{issueId}/assignee/{userId}")
    public ResponseEntity<Issue> addUserToIssue(@PathVariable Long issueId,@PathVariable Long userId) throws Exception {
        Issue issue=issueService.addUserToIssue(issueId,userId);
        return ResponseEntity.ok(issue);
    }
    @PutMapping("/{issueId}/status/{status}")
    public ResponseEntity<Issue> updateIssueStatus(@PathVariable Long issueId,@PathVariable String status) throws Exception {
        Issue issue=issueService.updateStatus(issueId,status);
        return ResponseEntity.ok(issue);
    }
}
