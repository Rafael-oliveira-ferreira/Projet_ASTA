package altn72.projet_asta.services;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.ApprenticeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import altn72.projet_asta.exception.*;

import java.util.List;

@Service
public class ApprenticeService {
    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findAll();
    }

    public Apprentice getApprenticeById(Integer id) {
        return apprenticeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Apprentice", id));
    }

    public void addApprentice(Apprentice apprentice) {
        if (apprentice.getEmail() != null && apprenticeRepository.existsByEmail(apprentice.getEmail())) {
            throw new DuplicateResourceException("Apprentice", "email", apprentice.getEmail());
        }
        apprenticeRepository.save(apprentice);
    }

    public void updateApprentice(Integer idApprentice, Apprentice apprentice) {
        Apprentice existingApprentice = apprenticeRepository.findById(idApprentice)
                .orElseThrow(() -> new ResourceNotFoundException("Apprentice", idApprentice));
        BeanUtils.copyProperties(apprentice, existingApprentice, "id");
        apprenticeRepository.save(existingApprentice);
    }

    public void deleteApprentice(Integer id) {
        Apprentice existingApprentice = apprenticeRepository.findById(id).orElseThrow();
        apprenticeRepository.delete(existingApprentice);
    }

    public List<Apprentice> findByMentorId(Integer mentorId) {
        return apprenticeRepository.findByApprenticeshipMentor_Id(mentorId);
    }

    public List<Apprentice> searchApprentices(Integer mentorId, String query) {
        return apprenticeRepository.searchApprentices(mentorId, query);
    }
}
