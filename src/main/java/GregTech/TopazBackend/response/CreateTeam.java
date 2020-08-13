package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Applies;
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
public class CreateTeam {
    private static final Logger log = LoggerFactory.getLogger(ApplyToTeam.class);

    private final Teams teamDao;

    @Autowired
    public CreateTeam(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/CreateTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int owner = Integer.parseInt((String) body.get("id"));
        Team team = new Team();
        team.setOwner(owner);
        team.setName((String) body.get("teamName"));
        team.setInfo((String) body.get("teamInfo"));
        int tid = teamDao.addTeam(team);
        if (tid == -1) {
            log.warn("Create failed.");
            res.put("result", false);
        } else {
            log.trace("Create successfully, tid is {}", tid);
            res.put("result", true);
        }
        return res;
    }
}
