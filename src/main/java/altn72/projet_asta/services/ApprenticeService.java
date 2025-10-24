package altn72.projet_asta.services;

import altn72.projet_asta.modele.Apprentice;
import altn72.projet_asta.modele.ApprenticeRepository;

import java.util.List;

public class ApprenticeService {
    private final ApprenticeRepository apprenticeRepository;

    public ApprenticeService(ApprenticeRepository apprenticeRepository) {
        this.apprenticeRepository = apprenticeRepository;
    }

    public List<Apprentice> getAllApprentices() {
        return apprenticeRepository.findAll();
    }

    public Apprentice getApprenticeById(int id) {
        return apprenticeRepository.findById(id).orElse(null);
    }
}
