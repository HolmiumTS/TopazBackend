package GregTech.TopazBackend.response.Apply;

import GregTech.TopazBackend.dao.Applies;
import GregTech.TopazBackend.dao.MessageDao;
import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Apply;
import GregTech.TopazBackend.metadata.ApplyStatus;
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
public class JudgeApplication {
    private static final Logger log = LoggerFactory.getLogger(JudgeApplication.class);

    private final Applies applyDao;
    private final Teams teamDao;
    private final MessageDao messageDao;

    @Autowired
    public JudgeApplication(Applies applyDao, Teams teamDao,MessageDao messageDao) {
        this.applyDao = applyDao;
        this.teamDao = teamDao;
        this.messageDao=messageDao;
    }

    @RequestMapping(value = "/JudgeApplication",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        int id = Integer.parseInt((String) body.get("id"));
        boolean isAccept = Boolean.parseBoolean((String) body.get("isAccept"));
        Apply apply = applyDao.getApplyByTid(tid, ApplyStatus.PENDING).stream()
                .filter(app -> app.getId() == id)
                .findAny()
                .orElse(null);
        if (apply == null) {
            log.warn("Judge failed: No such apply.");
            res.put("result", false);
        } else {
            if (isAccept) {
                apply.setStatus(ApplyStatus.ACCEPTED);
                boolean r = teamDao.addMember(id, tid);
                if (r) {
                    messageDao.generateNewMsg(-1,id,"您加入团队："+teamDao.getTeamByTid(tid).getName()+"的申请已经被接受");
                    log.trace("Add to team successfully.");
                } else {
                    log.warn("Add failed: Member exists");
                    res.put("result", false);
                    return res;
                }
            } else {
                messageDao.generateNewMsg(-1,id,"抱歉，您加入团队："+teamDao.getTeamByTid(tid).getName()+"的申请已经被拒绝");
                apply.setStatus(ApplyStatus.REFUSED);
                log.trace("Refuse apply successfully.");
            }
            applyDao.updateApply(apply);
            log.trace("Judge successfully.");
            res.put("result", true);
        }
        return res;
    }
}
