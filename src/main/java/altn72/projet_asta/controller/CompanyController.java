package altn72.projet_asta.controller;

import altn72.projet_asta.model.Company;
import altn72.projet_asta.services.CompanyService;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company/{idCompany}")
    public Company getCompanyById(@PathVariable Integer idCompany) {
        return companyService.getCompanyById(idCompany);
    }

    @PostMapping("/createCompany/")
    public void createCompany(@RequestBody Company newCompany) {
        companyService.addCompany(newCompany);
    }

    @PutMapping("/updateCompany/{idCompany}")
    public void updateCompany(@PathVariable Integer idCompany, @RequestBody Company company) {
        companyService.updateCompany(idCompany, company);
    }
}
