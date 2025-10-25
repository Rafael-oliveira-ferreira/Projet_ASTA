package altn72.projet_asta.controller;

import altn72.projet_asta.modele.ApprenticeshipMentor;
import altn72.projet_asta.modele.UserAccount;
import altn72.projet_asta.services.ApprenticeshipMentorService;
import altn72.projet_asta.services.UserAccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    private final UserAccountService userAccountService;
    private final ApprenticeshipMentorService apprenticeshipMentorService;

    public AuthController(UserAccountService userAccountService, ApprenticeshipMentorService apprenticeshipMentorService) {
        this.userAccountService = userAccountService;
        this.apprenticeshipMentorService = apprenticeshipMentorService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "connexion";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        if (principal == null) {
            // pas authentifié -> rediriger vers login
            return "redirect:/login";
        }

        String username = principal.getName();
        UserAccount userAccount;
        try {
            userAccount = userAccountService.loadUserByUsername(username);
        } catch (Exception e) {
            // si l'utilisateur n'est pas trouvé, rediriger vers connexion
            return "redirect:/login";
        }

        model.addAttribute("userAccount", userAccount);

        ApprenticeshipMentor mentor = apprenticeshipMentorService.getApprenticeshipMentorByUserId(userAccount.getId());
        if (mentor != null) {
            model.addAttribute("apprenticementor", mentor);
        }

        return "home";
    }
}
