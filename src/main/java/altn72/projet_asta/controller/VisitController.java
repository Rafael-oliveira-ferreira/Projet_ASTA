package altn72.projet_asta.controller;

import altn72.projet_asta.model.Visit;
import altn72.projet_asta.services.VisitService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visit/{idVisit}")
    public Visit getVisitById(@PathVariable Integer idVisit) {
        return visitService.getVisitById(idVisit);
    }
}
