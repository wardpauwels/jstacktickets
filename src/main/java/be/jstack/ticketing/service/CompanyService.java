package be.jstack.ticketing.service;

import be.jstack.ticketing.data.CompanyRepository;
import be.jstack.ticketing.entity.Company;
import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.exception.CompanyNotFoundException;
import be.jstack.ticketing.exception.ProjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final ProjectService projectService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ProjectService projectService) {
        this.companyRepository = companyRepository;
        this.projectService = projectService;
    }

    public void addCompany(Company company) {
        this.companyRepository.save(company);
    }

    public Optional<Company> findCompanyById(String id) {
        return companyRepository.findById(id);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public void addProjectToBusiness(String companyId, String projectId) throws CompanyNotFoundException, ProjectNotFoundException {
        Optional<Company> optionalCompany = findCompanyById(companyId);
        Optional<Project> optionalProject = projectService.findProjectById(projectId);
        if (optionalCompany.isPresent()) {
            if (optionalProject.isPresent()) {
                optionalCompany.get().addProject(optionalProject.get());
                companyRepository.save(optionalCompany.get());
            } else throw new ProjectNotFoundException(projectId);
        } else throw new CompanyNotFoundException(companyId);
    }
}