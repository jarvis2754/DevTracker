package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.config.JwtUtil;
import com.devtracker.DevTracker.dto.task.TaskDTO;
import com.devtracker.DevTracker.dto.task.TaskUpdateDTO;
import com.devtracker.DevTracker.mapper.TaskMapper;
import com.devtracker.DevTracker.model.Task;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.TaskRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private TaskRepository issueRepo;

    @Autowired
    public void setIssueRepo(TaskRepository issueRepo) {
        this.issueRepo = issueRepo;
    }

    private UserRepository userRepo;

    @Autowired
    public void setUserRepo(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private ProjectRepository projectRepo;

    @Autowired
    public void setProjectRepo(ProjectRepository projectRepo) {
        this.projectRepo = projectRepo;
    }

    private TaskMapper mapper;

    @Autowired
    public void setIssueMapper(TaskMapper mapper) {
        this.mapper = mapper;
    }

    private JwtUtil jwtUtil;
    @Autowired
    public void setJwtUtil(JwtUtil jwtUtil){
        this.jwtUtil = jwtUtil;
    }

    public User getUserFromToken(String token){
        String email = jwtUtil.extractUsername(token);
        return userRepo.findByEmail(email).orElseThrow(()->new UsernameNotFoundException("User with this email not found"));
    }

    public void addIssue(String token, TaskUpdateDTO issueData) {
        Task task = new Task();


        if (issueData.getAssignerId() != null) {
            User assigner = userRepo.findByUuId(issueData.getAssignerId()).orElse(null);
            task.setAssigner(assigner);
        } else {
            task.setAssigner(null);
        }


        if (issueData.getProjectId() != null) {
            Project project = projectRepo.findById(issueData.getProjectId()).orElse(null);
            task.setProject(project);
        } else {
            task.setProject(null);
        }

        task.setTitle(issueData.getTitle());
        task.setDescription(issueData.getDescription());
        task.setType(issueData.getType());
        task.setStatus(issueData.getStatus());
        task.setPriority(issueData.getPriority());
        task.setReporter(getUserFromToken(token));

        issueRepo.save(task);
    }


    public List<TaskDTO> getAllIssues() {
        return issueRepo.findAll().stream().map(mapper::toDto).toList();
    }


//    public List<TaskDTO> getAllIssuesByTitle(String keyword) {
//        return issueRepo.findByIssueTitleContainingIgnoreCase(keyword).stream().map(mapper::toDto).toList();
//    }
    public List<TaskDTO> getProjectIssues(int id) {
        Project project = projectRepo.findById(id).orElseThrow(()->new RuntimeException("Project not fount"));
        return issueRepo.findByProject(project).stream().map(mapper::toDto).toList();
    }

    public TaskDTO getTask(int id){
        return mapper.toDto(issueRepo.findById(id).orElseThrow(()->new RuntimeException("no task found")));
    }


    public void updateIssueById(int id, TaskUpdateDTO update) {
        Task task = issueRepo.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

        if (update.getTitle() != null) task.setTitle(update.getTitle());
        if (update.getDescription() != null) task.setDescription(update.getDescription());
        if (update.getType() != null) task.setType(update.getType());
        if (update.getStatus() != null) task.setStatus(update.getStatus());
        if (update.getPriority() != null) task.setPriority(update.getPriority());

        if (update.getAssignerId() != null) {
            User assigner = userRepo.findByUuId(update.getAssignerId()).orElse(null);
            task.setAssigner(assigner);
        }

        if (update.getProjectId() != null) {
            Project project = projectRepo.findById(update.getProjectId()).orElse(null);
            task.setProject(project);
        }
        issueRepo.save(task);
    }

    public boolean deleteIssueById(int id) {
        if (issueRepo.existsById(id)) {
            issueRepo.deleteById(id);
            return true;
        }
        return false;
    }


}
