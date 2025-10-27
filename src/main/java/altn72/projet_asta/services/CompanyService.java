package altn72.projet_asta.services;

import altn72.projet_asta.model.Apprentice;
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
        return companyRepository.findById(id).orElse(null);
    }

    public void addCompany(Company company) {
        companyRepository.save(company);
    }

    public void updateCompany(Integer idCompany, Company company) {
        Company existingCompany = companyRepository.findById(idCompany).orElseThrow();
        if (existingCompany != null) {
            BeanUtils.copyProperties(company, existingCompany, "id");
            companyRepository.save(existingCompany);
        }
    }

    public void deleteCompany(Integer id) {
        companyRepository.deleteById(id);
    }
}
