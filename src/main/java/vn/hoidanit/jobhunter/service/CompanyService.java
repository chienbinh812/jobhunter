package vn.hoidanit.jobhunter.service;

import org.springframework.stereotype.Service;
import vn.hoidanit.jobhunter.domain.Company;
import vn.hoidanit.jobhunter.repository.CompanyRepository;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company companyName) {
        return this.companyRepository.save(companyName);
    }

    public List<Company> getAllCompanies() {
        return this.companyRepository.findAll();
    }

    public Company getCompanyById(long id) {
        Optional<Company> company = companyRepository.findById(id);
        if (company.isPresent()) {
            return company.get();
        }
        return null;
    }

    public Company handleUpdateCompany(Company requestCompany) {
        Optional<Company> optionalCompany = this.companyRepository.findById(requestCompany.getId());
        if (optionalCompany.isPresent()) {
            Company currentCompany = optionalCompany.get();
            currentCompany.setName(requestCompany.getName());
            currentCompany.setDescription(requestCompany.getDescription());
            currentCompany.setAddress(requestCompany.getAddress());
            currentCompany.setLogo(requestCompany.getLogo());
            return this.companyRepository.save(currentCompany);
        }
        return null;
    }

    public void handleDeleteCompany(long id) {
        this.companyRepository.deleteById(id);
    }

}
