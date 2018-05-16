package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Company;
import be.jstack.ticketing.exception.CompanyNotFoundException;
import be.jstack.ticketing.exception.ProjectNotFoundException;
import be.jstack.ticketing.service.CompanyService;
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
    public void addNewCompany(@RequestBody Company company) {
        companyService.addCompany(company);
    }

    @PostMapping("/{companyId}/projects/{projectId}")
    public void addProjectWithIdToCompanyWithId(@PathVariable String companyId, @PathVariable String projectId) {
        try {
            companyService.addProjectToBusiness(companyId, projectId);
        } catch (CompanyNotFoundException | ProjectNotFoundException e) {
            e.printStackTrace();
        }
    }
}