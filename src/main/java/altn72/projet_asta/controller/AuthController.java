package altn72.projet_asta.controller;

import altn72.projet_asta.modele.UserAccount;
import altn72.projet_asta.services.UserAccountService;
import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class AuthController {

    private final UserAccountService userAccountService;

    public AuthController(UserAccountService userAccountService) {
        this.userAccountService = userAccountService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "connexion";
    }

    @GetMapping("/home")
    public String home(Model model, Principal principal) {
        String username = principal.getName();
        UserAccount user = userAccountService.loadUserByUsername(username);
        model.addAttribute("user", user);
        return "home";
    }
}
