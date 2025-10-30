package altn72.projet_asta.controller;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.dto.ApprenticeDto;
import altn72.projet_asta.services.ApprenticeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public void createApprentice(@RequestBody Apprentice newApprentice) {
        apprenticeService.addApprentice(newApprentice);
    }

    @GetMapping("/apprenticesByMentorId/{idMentor}")
    @ResponseBody
    public List<ApprenticeDto> getApprenticesByMentorId(@PathVariable("idMentor") Integer idMentor) {
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
