package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.service.ProjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/projects")
@Api(value = "Project controller", description = "Retrieve info about saved projects, make new projects or adapt existing ones.")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all the current companies", response = Project.class, responseContainer = "List")
    public Stream<Project> getAllProjects() {
        return projectService.findAll().stream();
    }

    @GetMapping("/{projectId}")
    @ApiOperation(value = "View info about the project with given ID", response = Project.class)
    public Optional<Project> getProjectById(@PathVariable String projectId) {
        return projectService.findById(projectId);
    }

    @PostMapping
    @ApiOperation(value = "Submit a new project", response = Project.class)
    public Project addNewProject(@RequestBody Project project) {
        return projectService.add(project);
    }
}