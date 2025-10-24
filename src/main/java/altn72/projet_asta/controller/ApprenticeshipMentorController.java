package altn72.projet_asta.controller;

import altn72.projet_asta.modele.ApprenticeshipMentor;
import altn72.projet_asta.services.ApprenticeshipMentorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@Controller
public class ApprenticeshipMentorController {
    private final ApprenticeshipMentorService apprenticeshipMentorService;

    public ApprenticeshipMentorController(ApprenticeshipMentorService apprenticeshipMentorService) {
        this.apprenticeshipMentorService = apprenticeshipMentorService;
    }

    @GetMapping("/mentor/{idMentor}")
    public ApprenticeshipMentor getMentorById(@PathVariable Integer idMentor) {
        return apprenticeshipMentorService.getApprenticeById(idMentor);
    }

    @PutMapping("/updateMentor/{idMentor}")
    public void updateMentor(@PathVariable Integer idMentor, ApprenticeshipMentor mentor) {
        apprenticeshipMentorService.updateApprenticeshipMentor(idMentor, mentor);
    }
}
