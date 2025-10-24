package altn72.projet_asta.services;

import altn72.projet_asta.modele.Company;
import altn72.projet_asta.modele.CompanyRepository;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company getCompanyById(Integer id) {
        return companyRepository.findById(id).orElse(null);
    }
}
