package com.asif.projectmanagementsystem1.repository;

import com.asif.projectmanagementsystem1.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IssueRepository extends JpaRepository<Issue,Long> {
    List<Issue> findByProjectId(Long projectID);
}
