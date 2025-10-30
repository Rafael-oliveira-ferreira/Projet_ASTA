package altn72.projet_asta.services;

import altn72.projet_asta.exception.DuplicateResourceException;
import altn72.projet_asta.exception.ResourceNotFoundException;
import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.DefenseRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DefenseService {
    private final DefenseRepository defenseRepository;

    public DefenseService(DefenseRepository defenseRepository) {
        this.defenseRepository = defenseRepository;
    }

    public Defense getDefenseById(Integer id) {
        return defenseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Defense", id));
    }

    public void addDefense(Defense defense) {
        if (defense.getDefenseDate() != null && defenseRepository.existsByDefenseDate(defense.getDefenseDate())) {
            throw new DuplicateResourceException("Company", "company name", defense.getDefenseDate());
        }
        defenseRepository.save(defense);
    }

    public void updateDefense(Integer idDefense, Defense defense) {
        Defense existingDefense = defenseRepository.findById(idDefense)
                .orElseThrow(() -> new ResourceNotFoundException("defense", idDefense));
        if (existingDefense != null) {
            BeanUtils.copyProperties(defense, existingDefense, "id");
            defenseRepository.save(existingDefense);
        }
    }

    public void deleteDefense(Integer id) {
        defenseRepository.deleteById(id);
    }

    public List<Defense> findByApprenticeId(Integer apprenticeId) {
        return defenseRepository.findByApprentice_Id(apprenticeId);
    }
}
