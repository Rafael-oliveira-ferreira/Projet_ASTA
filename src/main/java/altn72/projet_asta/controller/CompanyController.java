package altn72.projet_asta.controller;

import altn72.projet_asta.modele.Company;
import altn72.projet_asta.services.CompanyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/{idCompany}")
    public Company getCompanyById(@PathVariable Integer idCompany) {
        return companyService.getCompanyById(idCompany);
    }
}
