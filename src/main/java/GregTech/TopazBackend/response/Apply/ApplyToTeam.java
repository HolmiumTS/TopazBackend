package GregTech.TopazBackend.response.Apply;

import GregTech.TopazBackend.dao.Applies;
import GregTech.TopazBackend.metadata.Apply;
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
public class ApplyToTeam {
    private static final Logger log = LoggerFactory.getLogger(ApplyToTeam.class);

    private final Applies applyDao;

    @Autowired
    public ApplyToTeam(Applies applyDao) {
        this.applyDao = applyDao;
    }

    @RequestMapping(value = "/ApplyToTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        Apply apply = new Apply();
        int id = Integer.parseInt((String) body.get("userId"));
        int tid = Integer.parseInt((String) body.get("teamId"));
        apply.setId(id);
        apply.setTid(tid);
        int aid = applyDao.addApply(apply);
        if (aid == -1) {
            log.warn("Apply failed: Unknown error.");
            res.put("result", false);
        } else {
            log.trace("Apply successfully, aid is {}", aid);
            res.put("result", true);
        }
        return res;
    }
}
