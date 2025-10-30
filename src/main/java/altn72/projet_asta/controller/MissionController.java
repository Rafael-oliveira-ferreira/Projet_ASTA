package altn72.projet_asta.controller;

import altn72.projet_asta.model.Mission;
import altn72.projet_asta.model.dto.MissionDto;
import altn72.projet_asta.services.MissionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MissionController {
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/mission/{idMission}")
    public Mission getMissionById(@PathVariable Integer idMission) {
        return missionService.getMissionById(idMission);
    }

    @PostMapping("/createMission/")
    public void createMission(@RequestBody Mission newMission) {
        missionService.addMission(newMission);
    }

    @PutMapping("/updateMission/{idMission}")
    public void updateMission(@PathVariable Integer idMission, @RequestBody Mission mission) {
        missionService.updateMission(idMission, mission);
    }

    @GetMapping("/missionByApprenticeId/{idApprentice}")
    public List<MissionDto> getMissionsByMentorId(@PathVariable("idApprentice") Integer idApprentice) {
        return missionService.findByApprenticeId(idApprentice).stream()
                .map(m -> new MissionDto(
                        m.getId(), m.getApprentice() != null ? m.getApprentice().getId() : null,
                        m.getKeywords(), m.getTargetJob(), m.getComments()
                ))
                .toList();
    }
}
