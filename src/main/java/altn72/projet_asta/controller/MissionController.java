package altn72.projet_asta.controller;

import altn72.projet_asta.model.Mission;
import altn72.projet_asta.services.MissionService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MissionController {
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @GetMapping("/mission/{idMission}")
    public Mission getMissionById(@PathVariable Integer idMission) {
        return missionService.getMissionById(idMission);
    }
}
