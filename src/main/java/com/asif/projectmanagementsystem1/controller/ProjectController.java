package com.asif.projectmanagementsystem1.controller;

import com.asif.projectmanagementsystem1.model.*;
import com.asif.projectmanagementsystem1.request.InviteRequest;
import com.asif.projectmanagementsystem1.response.MessageResponse;
import com.asif.projectmanagementsystem1.service.InvitationService;
import com.asif.projectmanagementsystem1.service.ProjectService;
import com.asif.projectmanagementsystem1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private UserService userService;;
    @Autowired
    private InvitationService invitationService;
    @GetMapping
    public ResponseEntity<List<Project>> getProjects(
            @RequestParam(required = false)String category,@RequestParam(required = false) String tag,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.getProjectByTeam(user,category,tag);
        return new ResponseEntity<>(projects, HttpStatus.OK);
    }
    @GetMapping("/{projectId}")
    public ResponseEntity<Project> getProjectById(
            @PathVariable Long projectId,@RequestHeader("Authorization") String jwt
    ) throws Exception {
            User user=userService.findUserProfileByJwt(jwt);
            Project project=projectService.getProjectById(projectId);
            return new ResponseEntity<>(project,HttpStatus.OK);
        }
    @PostMapping("/createProject")
    public ResponseEntity<Project> createProject(
            @RequestBody Project project,@RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project savedProject=projectService.createProject(project,user);
        return new ResponseEntity<>(savedProject,HttpStatus.CREATED);
    }
    @PatchMapping("/{projectId}")
    public ResponseEntity<Project> updateProject(
            @PathVariable Long projectId,@RequestBody Project updatedProject,
            @RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        Project project=projectService.updateProject(updatedProject,projectId);
        return new ResponseEntity<>(project,HttpStatus.OK);
    }
    @DeleteMapping("/{projectId}")
    public ResponseEntity<MessageResponse> deleteProject(
            @PathVariable Long projectId,@RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        projectService.deleteProject(projectId,user.getId());
        MessageResponse res=new MessageResponse("Project deleted successfully");
        return new ResponseEntity<>(res,HttpStatus.NO_CONTENT);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Project>> searchProject(
            @RequestParam(required = false) String keyword,@RequestHeader("Authorization") String jwt
    ) throws Exception {
        User user=userService.findUserProfileByJwt(jwt);
        List<Project> projects=projectService.searchProject(keyword,user);
        return new ResponseEntity<>(projects,HttpStatus.OK);
    }
    @GetMapping("/{projectId}/chat")
    public ResponseEntity<Chat> getChatByProjectId(
            @PathVariable long projectId,@RequestHeader("Authorization") String jwt
    )throws Exception{

        User user=userService.findUserProfileByJwt(jwt);
        Chat chat=projectService.getChatByProjectId(projectId);
        return new ResponseEntity<>(chat,HttpStatus.OK);
    }
    @PostMapping("/invite")
    public ResponseEntity<MessageResponse> inviteProject(
            @RequestHeader("Authorization") String jwt,@RequestBody InviteRequest inviteRequest,
            @RequestBody Project project
    )throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        Project createdProject=projectService.createProject(project,user);
         invitationService.sendInvitation(inviteRequest.getEmail(),inviteRequest.getProjectId());
         MessageResponse res=new MessageResponse("Invitation sent successfully");
        return new ResponseEntity<>(res,HttpStatus.OK);

    }
    @PostMapping("/accept_invitation")
    public ResponseEntity<Invitation> acceptInviteProject(
            @RequestHeader("Authorization") String jwt,@RequestParam String token,@RequestBody Project project) throws Exception{
        User user=userService.findUserProfileByJwt(jwt);
        Invitation invitation=invitationService.acceptInvitation(token,user.getId());
        projectService.addUserToProject(invitation.getProjectId(),user.getId());
        return new ResponseEntity<>(invitation,HttpStatus.ACCEPTED);
    }
}
