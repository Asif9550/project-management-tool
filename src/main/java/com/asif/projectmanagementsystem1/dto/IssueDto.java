package com.asif.projectmanagementsystem1.dto;

import com.asif.projectmanagementsystem1.model.Project;
import com.asif.projectmanagementsystem1.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
public class IssueDto {
    private Long id;
    private String title;
    private String description;
    private String status;
    private Long projectId;
    private String priority;
    private String dueDate;
    private List<String> tags=new ArrayList<>();
    private Project project;
    private User assignee;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public IssueDto(Long id, String title, String description, Long projectId, String status, String priority, String dueDate, List<String> tags, Project project, User assignee) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.projectId = projectId;
        this.status = status;
        this.priority = priority;
        this.dueDate = dueDate;
        this.tags = tags;
        this.project = project;
        this.assignee = assignee;
    }

    public IssueDto() {
    }
}
