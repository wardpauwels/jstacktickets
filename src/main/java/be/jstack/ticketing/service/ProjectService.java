package be.jstack.ticketing.service;

import be.jstack.ticketing.data.ProjectRepository;
import be.jstack.ticketing.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProjectService implements be.jstack.ticketing.service.Service<Project> {
    private final ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(String projectId) {
        return projectRepository.findById(projectId);
    }

    @Override
    public Project add(Project project) {
        return projectRepository.save(project);
    }
}