package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class GetTeamInfo {
    private static final Logger log = LoggerFactory.getLogger(GetTeamInfo.class);

    @RequestMapping(value = "/ChangeUserPassword",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int tid = Integer.parseInt((String) body.get("teamId"));
        log.trace("Team id is {}", tid);
        Map<String, Object> result = new HashMap<>();
        Team team = Teams.getTeamByTid(tid);
        if (team == null) {
            log.warn("No such team.");
            result.put("result", false);
        } else {
            result.putAll(collectData(team));
            result.put("result", true);
        }
        return result;
    }

    private Map<String, Object> collectData(Team team) {
        Map<String, Object> map = new HashMap<>();
        map.put("creatorId", String.valueOf(team.getOwner()));
        map.put("teamName", team.getName());
        map.put("teamInfo", team.getInfo());
        return map;
    }
}
