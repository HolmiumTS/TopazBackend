package GregTech.TopazBackend.response.Team;

import GregTech.TopazBackend.dao.MessageDao;
import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Message;
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
public class SetAdmin {
    private static final Logger log = LoggerFactory.getLogger(SetAdmin.class);
    private final MessageDao messageDao;
    private final Teams teamDao;

    @Autowired
    public SetAdmin(Teams teamDao,MessageDao messageDao) {
        this.messageDao=messageDao;
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/SetAdmin",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        int id = Integer.parseInt((String) body.get("id"));
        boolean r = teamDao.setAdmin(tid, id, true);
        messageDao.generateNewMsg(-1,id,"您已成为团队："+teamDao.getTeamByTid(tid).getName()+" 的管理员");
        if (!r) {
            log.warn("Set admin failed, tid is {}, id is {}.", tid, id);
        }
        res.put("result", r);
        return res;
    }
}
