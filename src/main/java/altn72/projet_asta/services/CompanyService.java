package altn72.projet_asta.services;

import altn72.projet_asta.exception.DuplicateResourceException;
import altn72.projet_asta.exception.ResourceNotFoundException;
import altn72.projet_asta.model.Company;
import altn72.projet_asta.model.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Company", id));
    }

    public void addCompany(Company company) {
        if (company.getCompanyName() != null && companyRepository.existsByCompanyName(company.getCompanyName())) {
            throw new DuplicateResourceException("Company", "company name", company.getCompanyName());
        }
        companyRepository.save(company);
    }

    public void updateCompany(Integer idCompany, Company company) {
        Company existingCompany = companyRepository.findById(idCompany)
                .orElseThrow(() -> new ResourceNotFoundException("Company", idCompany));
        if (existingCompany != null) {
            BeanUtils.copyProperties(company, existingCompany, "id");
            companyRepository.save(existingCompany);
        }
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
