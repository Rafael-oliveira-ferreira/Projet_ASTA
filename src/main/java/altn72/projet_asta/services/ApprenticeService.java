package altn72.projet_asta.services;

import altn72.projet_asta.model.Apprentice;
import altn72.projet_asta.model.ApprenticeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

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
        return apprenticeRepository.findById(id).orElse(null);
    }

    public void addApprentice(Apprentice apprentice) {
        apprenticeRepository.save(apprentice);
    }

    public void updateApprentice(Integer idApprentice, Apprentice apprentice) {
        Apprentice existingApprentice = apprenticeRepository.findById(idApprentice).orElseThrow();
        if (existingApprentice != null) {
            BeanUtils.copyProperties(apprentice, existingApprentice, "id");
            apprenticeRepository.save(existingApprentice);
        }
    }

    public void deleteApprentice(Integer id) {
        apprenticeRepository.deleteById(id);
    }

    public List<Apprentice> findByMentorId(Integer mentorId) {
        return apprenticeRepository.findByApprenticeshipMentor_Id(mentorId);
    }
}
