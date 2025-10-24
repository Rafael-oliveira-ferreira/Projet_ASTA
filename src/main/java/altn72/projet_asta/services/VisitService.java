package altn72.projet_asta.services;

import altn72.projet_asta.modele.Visit;
import altn72.projet_asta.modele.VisitRepository;
import org.springframework.stereotype.Service;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit getVisitById(Integer id) {
        return visitRepository.findById(id).orElse(null);
    }
}
