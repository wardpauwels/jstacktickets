package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Company;
import be.jstack.ticketing.exception.*;
import be.jstack.ticketing.service.CompanyService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/companies")
@Api(value = "Company controller", description = "Retrieve info about saved companies, make new companies or adapt existing ones.")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all the current companies", response = Company.class, responseContainer = "List")
    public Stream<Company> getAllCompanies() {
        return companyService.findAll().stream();
    }

    @GetMapping("/{companyId}")
    @ApiOperation(value = "View info about the company with given ID", response = Company.class)
    public Optional<Company> getCompanyById(@PathVariable String companyId) {
        return companyService.findById(companyId);
    }

    @GetMapping("/name/{companyName}")
    @ApiOperation(value = "View info about the company with given name", response = Company.class)
    public Optional<Company> getCompanyByName(@PathVariable String companyName) {
        return companyService.findByName(companyName);
    }

    @PostMapping
    @ApiOperation(value = "Submit a new company", response = Company.class)
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.add(company);
    }

    @PatchMapping("/projects")
    @ApiOperation(value = "Add a project with given ID to a company with given ID", response = void.class)
    public void addProjectWithIdToCompanyWithId(@RequestBody String companyIdAndProjectId) {
        JSONObject jsonBody = new JSONObject(companyIdAndProjectId);
        try {
            companyService.addProjectToCompany(jsonBody.getString("companyId"), jsonBody.getString("projectId"));
        } catch (CompanyNotFoundException | ProjectNotFoundException | AlreadyContainsProjectException e) {
            e.printStackTrace();
        }
    }

    @PatchMapping("/users")
    @ApiOperation(value = "Add a user with give ID to a company with given ID", response = void.class)
    public void addUserWithIdToCompanyWithId(@RequestBody String companyIdAndUserId) {
        JSONObject jsonBody = new JSONObject(companyIdAndUserId);
        try {
            companyService.addUserToCompany(jsonBody.getString("companyId"), jsonBody.getString("userId"));
        } catch (CompanyNotFoundException | UserNotFoundException | AlreadyContainsUserException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{companyId}/projects/{projectId}")
    @ApiOperation(value = "Delete the project with given ID from the company with given ID", response = void.class)
    public void deleteProjectFromCompany(@PathVariable String companyId, @PathVariable String projectId) {
        try {
            companyService.deleteProjectFromCompany(companyId, projectId);
        } catch (ProjectNotFoundException | CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{companyId}/projects/{userId}")
    @ApiOperation(value = "Delete the user with given ID from the company with given ID", response = void.class)
    public void deleteUserFromCompany(@PathVariable String companyId, @PathVariable String userId) {
        try {
            companyService.deleteUserFromCompany(companyId, userId);
        } catch (UserNotFoundException | CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }
}