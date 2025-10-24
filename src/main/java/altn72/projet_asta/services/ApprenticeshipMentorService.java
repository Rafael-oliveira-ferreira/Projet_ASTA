package altn72.projet_asta.services;

import altn72.projet_asta.modele.ApprenticeshipMentor;
import altn72.projet_asta.modele.ApprenticeshipMentorRepository;
import org.springframework.stereotype.Service;

@Service
public class ApprenticeshipMentorService {
    private final ApprenticeshipMentorRepository apprenticeshipMentorRepository;

    public ApprenticeshipMentorService(ApprenticeshipMentorRepository apprenticeshipMentorRepository) {
        this.apprenticeshipMentorRepository = apprenticeshipMentorRepository;
    }

    public ApprenticeshipMentor getApprenticeById(Integer id) {
        return apprenticeshipMentorRepository.findById(id).orElse(null);
    }
}
