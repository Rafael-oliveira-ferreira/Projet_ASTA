package altn72.projet_asta.services;

import altn72.projet_asta.exception.DuplicateResourceException;
import altn72.projet_asta.exception.ResourceNotFoundException;
import altn72.projet_asta.model.Visit;
import altn72.projet_asta.model.VisitRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VisitService {
    private final VisitRepository visitRepository;

    public VisitService(VisitRepository visitRepository) {
        this.visitRepository = visitRepository;
    }

    public Visit getVisitById(Integer id) {
        return visitRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Visit", id));
    }

    public void addVisit(Visit visit) {
        if (visit.getVisitDate() != null && visitRepository.existsByVisitDate(visit.getVisitDate())) {
            throw new DuplicateResourceException("Company", "company name", visit.getVisitDate());
        }
        visitRepository.save(visit);
    }

    public void updateVisit(Integer idVisit, Visit visit) {
        Visit existingVisit = visitRepository.findById(idVisit)
                .orElseThrow(() -> new ResourceNotFoundException("Visit", idVisit));
        if (existingVisit != null) {
            BeanUtils.copyProperties(visit, existingVisit, "id");
            visitRepository.save(existingVisit);
        }
    }

    public void deleteVisit(Integer id) {
        visitRepository.deleteById(id);
    }

    public List<Visit> findByApprenticeId(Integer apprenticeId) {
        return visitRepository.findByApprentice_Id(apprenticeId);
    }
}
