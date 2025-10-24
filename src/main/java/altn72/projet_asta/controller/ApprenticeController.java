package altn72.projet_asta.controller;

import altn72.projet_asta.modele.Apprentice;
import altn72.projet_asta.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @PutMapping("/updateApprentice/{idApprentice}/")
    public void updateApprentice(@PathVariable Integer idApprentice, @RequestBody Apprentice apprenticeDetails) {
        apprenticeService.updateApprentice(idApprentice, apprenticeDetails);
    }

    @DeleteMapping("/deleteApprentice/{idApprentice}/")
    public void deleteApprentice(@PathVariable Integer idApprentice) {
        apprenticeService.deleteApprentice(idApprentice);
    }

    @PostMapping("/createApprentice/")
    public void createApprentice(@RequestBody Apprentice newApprentice) {
        apprenticeService.addApprentice(newApprentice);
    }
}
