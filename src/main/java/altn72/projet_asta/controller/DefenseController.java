package altn72.projet_asta.controller;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.services.DefenseService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DefenseController {
    private final DefenseService defenseService;

    public DefenseController(DefenseService defenseService) {
        this.defenseService = defenseService;
    }

    @GetMapping("/defense/{idDefense}")
    public Defense getDefenseById(@PathVariable Integer idDefense) {
        return defenseService.getDefenseById(idDefense);
    }
}
