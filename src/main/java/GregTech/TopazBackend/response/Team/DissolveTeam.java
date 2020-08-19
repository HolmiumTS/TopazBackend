package GregTech.TopazBackend.response.Team;

import GregTech.TopazBackend.dao.MessageDao;
import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.dao.Users;
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
public class DissolveTeam {
    private static final Logger log = LoggerFactory.getLogger(DissolveTeam.class);

    private final Teams teamDao;
    private final Users userdao;
    private final MessageDao messageDao;

    @Autowired
    public DissolveTeam(Teams teamDao, Users users, MessageDao messageDao) {
        this.messageDao = messageDao;
        this.userdao = users;
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
            for (User user :userdao.getAdminUserByTid(tid) ) {
                messageDao.generateNewMsg(-1,user.getId(),"您所管理的团队"+teamDao.getTeamByTid(tid).getName()+"已被创建者解散");
            }
            for (User user:userdao.getNormalUserByTid(tid)){
                messageDao.generateNewMsg(-1,user.getId(),"您加入的团队"+teamDao.getTeamByTid(tid).getName()+"已被创建者解散");
            }
        } else {
            log.warn("Delete failed: Unknown error.");
        }
        res.put("result", r);
        return res;
    }
}
