package altn72.projet_asta.controller;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.dto.ApprenticeDto;
import altn72.projet_asta.services.ApprenticeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
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

    @GetMapping("/apprenticesByMentorId/{idMentor}")
    public List<ApprenticeDto> getApprenticesByMentorId(@PathVariable("idMentor") Integer idMentor) {
        return apprenticeService.findByMentorId(idMentor).stream()
                .map(a -> new ApprenticeDto(
                        a.getId(), a.getProgram(), a.getAcademicYear(),
                        a.getMajor(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getPhone(),
                        a.getCompany() != null ? a.getCompany().getId() : null,
                        a.getApprenticeshipMentor() != null ? a.getApprenticeshipMentor().getId() : null
                ))
                .toList();
    }

}
