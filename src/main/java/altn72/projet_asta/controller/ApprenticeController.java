package altn72.projet_asta.controller;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.dto.ApprenticeDto;
import altn72.projet_asta.services.ApprenticeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
public class ApprenticeController {
    private final ApprenticeService apprenticeService;

    public ApprenticeController(ApprenticeService apprenticeService) {
        this.apprenticeService = apprenticeService;
    }

    @GetMapping("/apprentice/{idApprentice}")
    public String getApprenticeById(Model model , @PathVariable Integer idApprentice) {
        Apprentice apprentice = apprenticeService.getApprenticeById(idApprentice);
        model.addAttribute("apprentice", apprentice);
        return "apprenticeDetails";
    }

    @PutMapping("/updateApprentice/{idApprentice}/")
    @ResponseBody
    public void updateApprentice(@PathVariable Integer idApprentice, @RequestBody Apprentice apprenticeDetails) {
        apprenticeService.updateApprentice(idApprentice, apprenticeDetails);
    }

    @DeleteMapping("/deleteApprentice/{idApprentice}/")
    @ResponseBody
    public void deleteApprentice(@PathVariable Integer idApprentice) {
        apprenticeService.deleteApprentice(idApprentice);
    }

    @PostMapping("/createApprentice/")
    @ResponseBody
    public ResponseEntity<Apprentice> createApprentice(@RequestBody Apprentice newApprentice) {
        Apprentice apprenticeDto = apprenticeService.addApprentice(newApprentice);
        return new ResponseEntity<>(apprenticeDto, HttpStatus.CREATED);
    }

    @GetMapping("/apprenticesByMentorId/{idMentor}")
    @ResponseBody
    public List<ApprenticeDto> getApprenticesByMentorId(@PathVariable("idMentor") Integer idMentor) {
        // Convert Apprentice entities to ApprenticeDto but remove apprentice with Program "Gradué"
        return apprenticeService.findByMentorId(idMentor).stream()
                .filter(a -> !"Gradué".equals(a.getProgram()))
                .map(a -> new ApprenticeDto(
                        a.getId(), a.getProgram(), a.getAcademicYear(),
                        a.getMajor(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getPhone(),
                        a.getCompany() != null ? a.getCompany().getId() : null,
                        a.getApprenticeshipMentor() != null ? a.getApprenticeshipMentor().getId() : null
                ))
                .toList();
    }

    @GetMapping("/recherche/{idMentor}")
    @ResponseBody
    public List<ApprenticeDto> searchApprentices(@PathVariable Integer idMentor, @RequestParam String query) {
        return apprenticeService.searchApprentices(idMentor, query).stream()
                .filter(a -> !"Gradué".equals(a.getProgram()))
                .map(a -> new ApprenticeDto(
                        a.getId(), a.getProgram(), a.getAcademicYear(),
                        a.getMajor(), a.getFirstName(), a.getLastName(), a.getEmail(), a.getPhone(),
                        a.getCompany() != null ? a.getCompany().getId() : null,
                        a.getApprenticeshipMentor() != null ? a.getApprenticeshipMentor().getId() : null
                ))
                .toList();
    }

}
