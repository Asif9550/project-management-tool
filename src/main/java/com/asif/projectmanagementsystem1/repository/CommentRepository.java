package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
    List<Comment> findCommentByIssueId(Long issueId);

    List<Comment> findByIssueId(Long issueId);
}
