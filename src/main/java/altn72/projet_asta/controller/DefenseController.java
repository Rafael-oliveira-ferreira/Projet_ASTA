package altn72.projet_asta.controller;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.dto.ApprenticeDto;
import altn72.projet_asta.model.dto.DefenseDto;
import altn72.projet_asta.services.DefenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DefenseController {
    private final DefenseService defenseService;

    public DefenseController(DefenseService defenseService) {
        this.defenseService = defenseService;
    }

    @GetMapping("/defense/{idDefense}")
    public Defense getDefenseById(@PathVariable Integer idDefense) {
        return defenseService.getDefenseById(idDefense);
    }

    @PostMapping("/createDefense/")
    public ResponseEntity<Defense> createCompany(@RequestBody Defense newDefense) {
        Defense defense = defenseService.addDefense(newDefense);
        return ResponseEntity.ok(defense);
    }

    @PutMapping("/updateDefense/{idDefense}")
    public void updateDefense(@PathVariable Integer idDefense, @RequestBody Defense defense) {
        defenseService.updateDefense(idDefense, defense);
    }

    @GetMapping("/defenseByApprenticeId/{idApprentice}")
    public List<DefenseDto> getDefensesByMentorId(@PathVariable("idApprentice") Integer idApprentice) {
        return defenseService.findByApprenticeId(idApprentice).stream()
                .map(d -> new DefenseDto(
                        d.getId(), d.getApprentice() != null ? d.getApprentice().getId() : null,
                        d.getDefenseDate(), d.getGrade(), d.getComments()
                ))
                .toList();
    }
}
