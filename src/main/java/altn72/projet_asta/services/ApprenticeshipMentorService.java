package altn72.projet_asta.services;

import altn72.projet_asta.exception.ResourceNotFoundException;
import altn72.projet_asta.model.ApprenticeshipMentor;
import altn72.projet_asta.model.ApprenticeshipMentorRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class ApprenticeshipMentorService {
    private final ApprenticeshipMentorRepository apprenticeshipMentorRepository;

    public ApprenticeshipMentorService(ApprenticeshipMentorRepository apprenticeshipMentorRepository) {
        this.apprenticeshipMentorRepository = apprenticeshipMentorRepository;
    }

    public ApprenticeshipMentor getApprenticeshipMentorById(Integer id) {
        return apprenticeshipMentorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApprenticeshipMentor", id));
    }

    public ApprenticeshipMentor getApprenticeshipMentorByUserId(Integer id) {
        return apprenticeshipMentorRepository.findByIdAccountId(id)
                .orElseThrow(() -> new ResourceNotFoundException("ApprenticeshipMentor", id));
    }

    public void updateApprenticeshipMentor(Integer idApprenticeshipMentor, ApprenticeshipMentor apprenticeshipMentor) {
        ApprenticeshipMentor existingMentor = apprenticeshipMentorRepository.findById(idApprenticeshipMentor)
                .orElseThrow(() -> new ResourceNotFoundException("ApprenticeshipMentor", idApprenticeshipMentor));
        BeanUtils.copyProperties(apprenticeshipMentor, existingMentor, "id");
        apprenticeshipMentorRepository.save(existingMentor);
    }
}
