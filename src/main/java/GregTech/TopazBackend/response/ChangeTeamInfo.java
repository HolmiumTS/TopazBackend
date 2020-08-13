package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ChangeTeamInfo {
    private static final Logger log = LoggerFactory.getLogger(CancelAdmin.class);

    private final Teams teamDao;

    @Autowired
    public ChangeTeamInfo(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/ChangeTeamInfo",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        Team team = teamDao.getTeamByTid(tid);
        if (team == null) {
            log.warn("Change failed: No such team");
            res.put("result", false);
        } else {
            team.setInfo((String) body.get("teamInfo"));
            team.setName((String) body.get("teamName"));
            boolean r = teamDao.updateTeam(team);
            if (r) {
                log.trace("Change successfully.");
                res.put("result", true);
            } else {
                log.warn("Change failed: Unknown error.");
                res.put("result", false);
            }
        }
        return res;
    }
}
