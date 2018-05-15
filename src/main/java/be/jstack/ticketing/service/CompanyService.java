package be.jstack.ticketing.service;

import be.jstack.ticketing.data.CompanyRepository;
import be.jstack.ticketing.entity.Company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
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

    public Optional<Company> findCompanyByName(String name) {
        return companyRepository.findByName(name);
    }
}
