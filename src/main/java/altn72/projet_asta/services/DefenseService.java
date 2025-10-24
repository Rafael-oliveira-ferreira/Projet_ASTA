package altn72.projet_asta.services;

import altn72.projet_asta.modele.Defense;
import altn72.projet_asta.modele.DefenseRepository;
import org.springframework.stereotype.Service;

@Service
public class DefenseService {
    private final DefenseRepository defenseRepository;

    public DefenseService(DefenseRepository defenseRepository) {
        this.defenseRepository = defenseRepository;
    }

    public Defense getDefenseById(Integer id) {
        return defenseRepository.findById(id).orElse(null);
    }
}
