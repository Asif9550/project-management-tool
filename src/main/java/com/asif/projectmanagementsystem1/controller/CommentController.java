package com.asif.projectmanagementsystem1.controller;

import com.asif.projectmanagementsystem1.model.Comment;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.request.CreateCommentRequest;
import com.asif.projectmanagementsystem1.response.MessageResponse;
import com.asif.projectmanagementsystem1.service.CommentService;
import com.asif.projectmanagementsystem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @PostMapping("/createComment")
    public ResponseEntity<Comment> createComment(
            @RequestBody CreateCommentRequest req,
            @RequestHeader("Authorization")String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Comment createdComment=commentService.createComment(req.getIssueId(),user.getId(), req.getContent());
        return ResponseEntity.ok(createdComment);
    }
    @DeleteMapping("/{commentId}")
    public ResponseEntity<MessageResponse> deleteComment(@PathVariable Long commentId,
                                                         @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        commentService.deleteComment(commentId,user.getId());
        return ResponseEntity.ok(new MessageResponse("Comment deleted successfully"));
    }
    @GetMapping("/{issueId}")
    public ResponseEntity<List<Comment>> getCommentsByIssueId(@PathVariable Long issueId) throws Exception {
        List<Comment> comments=commentService.findCommentByIssueId(issueId);
        return new ResponseEntity<>(comments, HttpStatus.OK);
    }
}
