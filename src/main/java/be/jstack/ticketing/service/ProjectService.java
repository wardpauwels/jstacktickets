package be.jstack.ticketing.service;

import be.jstack.ticketing.data.ProjectRepository;
import be.jstack.ticketing.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAllProjects() {
        return projectRepository.findAll();
    }

    public void addProject(Project project) {
        projectRepository.save(project);
    }

    public Optional<Project> findProjectById(String projectId) {
        return projectRepository.findById(projectId);
    }
}