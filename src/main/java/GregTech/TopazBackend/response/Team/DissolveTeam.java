package GregTech.TopazBackend.response.Team;

import GregTech.TopazBackend.dao.Teams;
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
public class DissolveTeam {
    private static final Logger log = LoggerFactory.getLogger(DissolveTeam.class);

    private final Teams teamDao;

    @Autowired
    public DissolveTeam(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/DissolveTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("id"));
        boolean r = teamDao.delTeam(tid);
        if (r) {
            log.trace("Delete successfully.");
        } else {
            log.warn("Delete failed: Unknown error.");
        }
        res.put("result", r);
        return res;
    }
}
