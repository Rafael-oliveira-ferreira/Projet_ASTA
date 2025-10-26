package altn72.projet_asta.services;

import altn72.projet_asta.model.Mission;
import altn72.projet_asta.model.MissionRepository;
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

    public void addMission(Mission mission) {
        missionRepository.save(mission);
    }

    public void updateMission(Mission mission) {
        missionRepository.save(mission);
    }

    public void deleteMission(Integer id) {
        missionRepository.deleteById(id);
    }
}
