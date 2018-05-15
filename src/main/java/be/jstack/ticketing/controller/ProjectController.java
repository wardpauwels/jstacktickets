package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public Stream<Project> getAllProjects() {
        return projectService.findAllProjects().stream();
    }

    @GetMapping("/{projectId}")
    public Optional<Project> getProjectById(@PathVariable String projectId) {
        return projectService.findProjectById(projectId);
    }


    @PostMapping
    public void addNewProject(@RequestBody Project project) {
        projectService.addProject(project);
    }
}