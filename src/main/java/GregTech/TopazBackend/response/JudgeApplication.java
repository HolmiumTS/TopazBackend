package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Applies;
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

    @Autowired
    public JudgeApplication(Applies applyDao) {
        this.applyDao = applyDao;
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
                //todo add to the team
            } else {
                apply.setStatus(ApplyStatus.REFUSED);
            }
            applyDao.updateApply(apply);
            log.trace("Judge successfully.");
            res.put("result", true);
        }
        return res;
    }
}
