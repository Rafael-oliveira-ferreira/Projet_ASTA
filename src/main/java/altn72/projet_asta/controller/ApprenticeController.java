package altn72.projet_asta.controller;

import altn72.projet_asta.modele.Apprentice;
import altn72.projet_asta.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class ApprenticeController {
    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("/apprentice/{idApprentice}")
    public Apprentice getApprenticeById(@PathVariable Integer idApprentice) {
        return apprenticeService.getApprenticeById(idApprentice);
    }
}
