package com.asif.projectmanagementsystem1.service;

import com.asif.projectmanagementsystem1.model.Chat;
import com.asif.projectmanagementsystem1.model.Project;
import com.asif.projectmanagementsystem1.model.User;
import com.asif.projectmanagementsystem1.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImpl implements ProjectService{
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private ChatService chatService;
    @Override
    public Project createProject(Project project, User user) throws Exception {
        Project createProject=new Project();
        createProject.setOwner(user);
        createProject.setTags(project.getTags());
        createProject.setName(project.getName());
        createProject.setCategory(project.getCategory());
        createProject.setDescription(project.getDescription());
        createProject.getTeam().add(user);
        Project savedProject=projectRepository.save(createProject);
        Chat chat=new Chat();
        chat.setProject(savedProject);
        Chat projectChat=chatService.createChat(chat);
        savedProject.setChat(projectChat);
        return savedProject;
    }

    @Override
    public List<Project> getProjectByTeam(User user, String category, String tag) throws Exception {
        List<Project> projects=projectRepository.findByNameContainingAndTeamContains(category,user);
        if(category!=null){
            projects=projects.stream().filter(project->project.getCategory().equals(category)).toList();
        }
        if(tag!=null){
            projects=projects.stream().filter(project->project.getTags().contains(tag)).toList();
        }
        return projects;
    }

    @Override
    public Project getProjectById(Long projectId) throws Exception {
        Optional<Project> optionalProject=projectRepository.findById(projectId);
        if(optionalProject.isEmpty()){
            throw new Exception("Project not found");
        }
        return optionalProject.get();
    }

    @Override
    public void deleteProject(Long projectId, Long userId) throws Exception {
      getProjectById(projectId);
      projectRepository.deleteById(projectId);
    }

    @Override
    public Project updateProject(Project updatedProject, Long id) throws Exception {
        Project project=getProjectById(id);
        project.setName(updatedProject.getName());
        project.setDescription(updatedProject.getDescription());
        project.setTags(updatedProject.getTags());
        return projectRepository.save(project);
    }

    @Override
    public void addUserToProject(Long projectId, Long userId) throws Exception {
        Project project=getProjectById(projectId);
        User user=userService.findUserById(userId);
        if(!project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().add(user);
        }
        projectRepository.save(project);

    }

    @Override
    public void removeUserFromProject(Long projectId, Long userId) throws Exception {
        Project project=getProjectById(projectId);
        User user=userService.findUserById(userId);
        if(project.getTeam().contains(user)){
            project.getChat().getUsers().add(user);
            project.getTeam().remove(user);
        }
        projectRepository.save(project);
    }

    @Override
    public Chat getChatByProjectId(Long projectId) throws Exception {
        Project project=getProjectById(projectId);
        return project.getChat();
    }

    @Override
    public List<Project> searchProject(String keyword, User user) throws Exception {

        return projectRepository.findByNameContainingAndTeamContains(keyword,user);
    }
}
