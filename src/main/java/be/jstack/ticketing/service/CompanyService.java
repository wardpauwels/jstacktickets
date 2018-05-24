package be.jstack.ticketing.service;

import be.jstack.ticketing.data.CompanyRepository;
import be.jstack.ticketing.entity.Company;
import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService implements be.jstack.ticketing.service.Service<Company> {
    private final CompanyRepository companyRepository;
    private final ProjectService projectService;
    private final UserService userService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, ProjectService projectService, UserService userService) {
        this.companyRepository = companyRepository;
        this.projectService = projectService;
        this.userService = userService;
    }

    @Override
    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    @Override
    public Optional<Company> findById(String id) {
        return companyRepository.findById(id);
    }

    public Optional<Company> findByName(String companyName) {
        return companyRepository.findByName(companyName);
    }


    @Override
    public Company add(Company company) {
        return companyRepository.save(company);
    }

    public void addProjectToCompany(String companyId, String projectId) throws CompanyNotFoundException, ProjectNotFoundException, AlreadyContainsProjectException {
        Optional<Company> optionalCompany = findById(companyId);
        Optional<Project> optionalProject = projectService.findById(projectId);
        if (optionalCompany.isPresent()) {
            if (optionalProject.isPresent()) {
                optionalCompany.get().addProject(optionalProject.get());
                companyRepository.save(optionalCompany.get());
            } else throw new ProjectNotFoundException(projectId);
        } else throw new CompanyNotFoundException(companyId);
    }

    public void deleteProjectFromCompany(String companyId, String projectId) throws ProjectNotFoundException, CompanyNotFoundException {
        Optional<Company> optionalCompany = findById(companyId);
        Optional<Project> optionalProject = projectService.findById(projectId);
        if (optionalCompany.isPresent()) {
            if (optionalProject.isPresent()) {
                optionalCompany.get().deleteProject(optionalProject.get());
                companyRepository.save(optionalCompany.get());
            } else throw new ProjectNotFoundException(projectId);
        } else throw new CompanyNotFoundException(companyId);
    }

    public void addUserToCompany(String companyId, String userId) throws CompanyNotFoundException, UserNotFoundException, AlreadyContainsUserException {
        Optional<Company> optionalCompany = findById(companyId);
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalCompany.isPresent()) {
            if (optionalUser.isPresent()) {
                optionalCompany.get().addUser(optionalUser.get());
                companyRepository.save(optionalCompany.get());
            } else throw new UserNotFoundException(userId);
        } else throw new CompanyNotFoundException(companyId);
    }

    public void deleteUserFromCompany(String companyId, String userId) throws CompanyNotFoundException, UserNotFoundException {
        Optional<Company> optionalCompany = findById(companyId);
        Optional<User> optionalUser = userService.findById(userId);
        if (optionalCompany.isPresent()) {
            if (optionalUser.isPresent()) {
                optionalCompany.get().deleteUser(optionalUser.get());
                companyRepository.save(optionalCompany.get());
            } else throw new UserNotFoundException(userId);
        } else throw new CompanyNotFoundException(companyId);
    }
}