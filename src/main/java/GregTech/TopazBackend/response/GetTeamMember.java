package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Team;
import GregTech.TopazBackend.metadata.User;
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
public class GetTeamMember {
    private static final Logger log = LoggerFactory.getLogger(GetTeamMember.class);

    private final Teams teamDao;
    private final Users userDao;

    @Autowired
    public GetTeamMember(Users userDao, Teams teamDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/GetTeamMember",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        Team team = teamDao.getTeamByTid(tid);
        if (team == null) {
            log.warn("Get failed: No such team.");
            res.put("result", false);
            return res;
        }
        int owner = team.getOwner();
        res.put("creatorId", String.valueOf(owner));
        int[] adminId = userDao.getAdminUserByTid(tid).stream()
                .mapToInt(User::getId)
                .filter(i -> i != owner)
                .toArray();
        res.put("adminId", adminId);
        int[] memberId = userDao.getNormalUserByTid(tid).stream()
                .mapToInt(User::getId)
                .toArray();
        res.put("memberId", memberId);
        return res;
    }
}
