package altn72.projet_asta.controller;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.Visit;
import altn72.projet_asta.model.dto.ReportDto;
import altn72.projet_asta.model.dto.VisitDto;
import altn72.projet_asta.services.VisitService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class VisitController {
    private final VisitService visitService;

    public VisitController(VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping("/visit/{idVisit}")
    public Visit getVisitById(@PathVariable Integer idVisit) {
        return visitService.getVisitById(idVisit);
    }

    @PostMapping("/createVisit/")
    public ResponseEntity<Visit> createCompany(@RequestBody Visit newVisit) {
        Visit visit = visitService.addVisit(newVisit);
        return ResponseEntity.ok(visit);
    }

    @PutMapping("/updateVisit/{idVisit}")
    public void updateVisit(@PathVariable Integer idVisit, @RequestBody Visit visit) {
        visitService.updateVisit(idVisit, visit);
    }

    @GetMapping("/visitByApprenticeId/{idApprentice}")
    public List<VisitDto> getVisitsByMentorId(@PathVariable("idApprentice") Integer idApprentice) {
        return visitService.findByApprenticeId(idApprentice).stream()
                .map(v -> new VisitDto(
                        v.getId(), v.getApprentice() != null ? v.getApprentice().getId() : null,
                        v.getVisitDate(), v.getFormat(), v.getComments()
                ))
                .toList();
    }
}
