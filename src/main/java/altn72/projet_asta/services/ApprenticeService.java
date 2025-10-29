package altn72.projet_asta.services;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.ApprenticeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
        return apprenticeRepository.findById(id).orElse(null);
    }

    public Apprentice addApprentice(Apprentice apprentice) {
        return apprenticeRepository.save(apprentice);
    }

    public void updateApprentice(Integer idApprentice, Apprentice apprentice) {
        Apprentice existingApprentice = apprenticeRepository.findById(idApprentice).orElseThrow();
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
