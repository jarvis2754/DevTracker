package com.devtracker.DevTracker.services;

import com.devtracker.DevTracker.dto.issue.IssueDTO;
import com.devtracker.DevTracker.dto.issue.IssueUpdateDTO;
import com.devtracker.DevTracker.mapper.IssueMapper;
import com.devtracker.DevTracker.model.Issue;
import com.devtracker.DevTracker.model.Project;
import com.devtracker.DevTracker.model.User;
import com.devtracker.DevTracker.repository.IssueRepository;
import com.devtracker.DevTracker.repository.ProjectRepository;
import com.devtracker.DevTracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    private IssueRepository issueRepo;

    @Autowired
    public void setIssueRepo(IssueRepository issueRepo) {
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

    private IssueMapper mapper;

    @Autowired
    public void setIssueMapper(IssueMapper mapper) {
        this.mapper = mapper;
    }
//add
    public void addIssue(IssueUpdateDTO issueData) {
        Issue issue = new Issue();


        if (issueData.getReporterId() != null) {
            User reporter = userRepo.findById(issueData.getReporterId()).orElse(null);
            issue.setReporter(reporter);
        } else {
            issue.setReporter(null);
        }


        if (issueData.getAssignerId() != null) {
            User assigner = userRepo.findById(issueData.getAssignerId()).orElse(null);
            issue.setAssigner(assigner);
        } else {
            issue.setAssigner(null);
        }


        if (issueData.getProjectId() != null) {
            Project project = projectRepo.findById(issueData.getProjectId()).orElse(null);
            issue.setProject(project);
        } else {
            issue.setProject(null);
        }


        issue.setIssueTitle(issueData.getIssueTitle());
        issue.setIssueDescription(issueData.getIssueDescription());
        issue.setIssueType(issueData.getIssueType());
        issue.setStatus(issueData.getStatus());
        issue.setPriority(issueData.getPriority());

        issueRepo.save(issue);
    }


    public List<IssueDTO> getAllIssues() {
        return issueRepo.findAll().stream().map(mapper::toDto).toList();
    }


//    public List<IssueDTO> getAllIssuesByTitle(String keyword) {
//        return issueRepo.findByIssueTitleContainingIgnoreCase(keyword).stream().map(mapper::toDto).toList();
//    }


    public void updateIssueById(int id, IssueUpdateDTO update) {
        Issue issue = issueRepo.findById(id).orElseThrow(() -> new RuntimeException("Issue not found"));

        if (update.getIssueTitle() != null) issue.setIssueTitle(update.getIssueTitle());
        if (update.getIssueDescription() != null) issue.setIssueDescription(update.getIssueDescription());
        if (update.getIssueType() != null) issue.setIssueType(update.getIssueType());
        if (update.getStatus() != null) issue.setStatus(update.getStatus());
        if (update.getPriority() != null) issue.setPriority(update.getPriority());

        if (update.getReporterId() != null) {
            User reporter = userRepo.findById(update.getReporterId()).orElse(null);
            issue.setReporter(reporter);
        }

        if (update.getAssignerId() != null) {
            User assigner = userRepo.findById(update.getAssignerId()).orElse(null);
            issue.setAssigner(assigner);
        }

        if (update.getProjectId() != null) {
            Project project = projectRepo.findById(update.getProjectId()).orElse(null);
            issue.setProject(project);
        }

        issueRepo.save(issue);
    }


    public boolean deleteIssueById(int id) {
        if (issueRepo.existsById(id)) {
            issueRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
