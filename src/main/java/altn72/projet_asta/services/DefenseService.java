package altn72.projet_asta.services;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.DefenseRepository;
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

    public void addDefense(Defense defense) {
        defenseRepository.save(defense);
    }

    public void updateDefense(Defense defense) {
        defenseRepository.save(defense);
    }

    public void deleteDefense(Integer id) {
        defenseRepository.deleteById(id);
    }
}
