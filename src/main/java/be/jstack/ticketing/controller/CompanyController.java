package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Company;
import be.jstack.ticketing.exception.*;
import be.jstack.ticketing.service.CompanyService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    private final CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public Stream<Company> getAllCompanies() {
        return companyService.getAllCompanies().stream();
    }

    @GetMapping("/{companyId}")
    public Optional<Company> getCompanyById(@PathVariable String companyId) {
        return companyService.findCompanyById(companyId);
    }

    @GetMapping("/name/{companyName}")
    public Optional<Company> getCompanyByName(@PathVariable String companyName) {
        return companyService.findCompanyById(companyName);
    }

    @PostMapping
    public Company addNewCompany(@RequestBody Company company) {
        return companyService.addCompany(company);
    }

    @PatchMapping("/projects")
    public void addProjectWithIdToCompanyWithId(@RequestBody String bodyString) {
        JSONObject jsonBody = new JSONObject(bodyString);
        try {
            companyService.addProjectToCompany(jsonBody.getString("companyId"), jsonBody.getString("projectId"));
        } catch (CompanyNotFoundException | ProjectNotFoundException | AlreadyContainsProjectException e) {
            e.printStackTrace();
        }
    }

    @PatchMapping("/users")
    public void addUserWithIdToCompanyWithId(@RequestBody String bodyString) {
        JSONObject jsonBody = new JSONObject(bodyString);
        try {
            companyService.addUserToCompany(jsonBody.getString("companyId"), jsonBody.getString("userId"));
        } catch (CompanyNotFoundException | UserNotFoundException | AlreadyContainsUserException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{companyId}/projects/{projectId}")
    public void deleteProjectFromCompany(@PathVariable String companyId, @PathVariable String projectId) {
        try {
            companyService.deleteProjectFromCompany(companyId, projectId);
        } catch (ProjectNotFoundException | CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/{companyId}/projects/{userId}")
    public void deleteUserFromCompany(@PathVariable String companyId, @PathVariable String userId) {
        try {
            companyService.deleteUserFromCompany(companyId, userId);
        } catch (UserNotFoundException | CompanyNotFoundException e) {
            e.printStackTrace();
        }
    }
}