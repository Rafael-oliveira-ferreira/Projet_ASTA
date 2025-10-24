package altn72.projet_asta.services;

import altn72.projet_asta.modele.Mission;
import altn72.projet_asta.modele.MissionRepository;
import org.springframework.stereotype.Service;

@Service
public class MissionService {
    private final MissionRepository missionRepository;

    public  MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission getMissionById(Integer id) {
        return missionRepository.findById(id).orElse(null);
    }
}
