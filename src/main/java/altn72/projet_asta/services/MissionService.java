package altn72.projet_asta.services;

import altn72.projet_asta.model.Defense;
import altn72.projet_asta.model.Mission;
import altn72.projet_asta.model.MissionRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionService {
    private final MissionRepository missionRepository;

    public  MissionService(MissionRepository missionRepository) {
        this.missionRepository = missionRepository;
    }

    public Mission getMissionById(Integer id) {
        return missionRepository.findById(id).orElse(null);
    }

    public Mission addMission(Mission mission) {
        return missionRepository.save(mission);
    }

    public void updateMission(Integer idMission, Mission mission) {
        Mission existingMission = missionRepository.findById(idMission).orElseThrow();
        if (existingMission != null) {
            BeanUtils.copyProperties(mission, existingMission, "id");
            missionRepository.save(existingMission);
        }
    }

    public void deleteMission(Integer id) {
        missionRepository.deleteById(id);
    }

    public List<Mission> findByApprenticeId(Integer apprenticeId) {
        return missionRepository.findByApprentice_Id(apprenticeId);
    }
}
