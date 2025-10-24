package altn72.projet_asta.controller;

import altn72.projet_asta.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApprenticeController {
    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("/apprentices")
    public String listApprentices() {
        return apprenticeService.getAllApprentices()
    }
}
