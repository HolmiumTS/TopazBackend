package GregTech.TopazBackend.response;

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
public class KickOffUser {
    private static final Logger log = LoggerFactory.getLogger(KickOffUser.class);

    private final Teams teamDao;

    @Autowired
    public KickOffUser(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/KickOff",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response1(@RequestBody Map<String, Object> body) {
        return kick(body);
    }

    @RequestMapping(value = "/QuitTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response2(@RequestBody Map<String, Object> body) {
        return kick(body);
    }

    private Map<String, Object> kick(Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        int id = Integer.parseInt((String) body.get("id"));
        boolean r = teamDao.removeUser(tid, id);
        if (!r) {
            log.warn("Kick off failed, tid is {}, id is {}.", tid, id);
        }
        res.put("result", r);
        return res;
    }
}
