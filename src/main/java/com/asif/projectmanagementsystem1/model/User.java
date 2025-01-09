package com.asif.projectmanagementsystem1.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String fullName;
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @JsonIgnore
    @OneToMany(mappedBy = "assignee",cascade = CascadeType.ALL)
    private List<Issue> assinedIssues =new ArrayList<>();

    private int projectSize;



    public Long getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public List<Issue> getAssinedIssues() {
        return assinedIssues;
    }

    public int getProjectSize() {
        return projectSize;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAssinedIssues(List<Issue> assinedIssues) {
        this.assinedIssues = assinedIssues;
    }

    public void setProjectSize(int projectSize) {
        this.projectSize = projectSize;
    }

    public User(String fullName, Long id, String email, String password, List<Issue> assinedIssues, int projectSize) {
        this.fullName = fullName;
        this.id = id;
        this.email = email;
        this.password = password;
        this.assinedIssues = assinedIssues;
        this.projectSize = projectSize;
    }
    public User(){
    }


}
