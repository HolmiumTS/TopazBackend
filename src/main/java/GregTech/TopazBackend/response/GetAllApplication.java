package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Applies;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Apply;
import GregTech.TopazBackend.metadata.ApplyStatus;
import GregTech.TopazBackend.metadata.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GetAllApplication {
    private static final Logger log = LoggerFactory.getLogger(GetAllApplication.class);

    private final Users userDao;
    private final Applies applyDao;

    @Autowired
    public GetAllApplication(Users userDao, Applies applyDao) {
        this.userDao = userDao;
        this.applyDao = applyDao;
    }

    @RequestMapping(value = "/GetAllApplication",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        res.put("result", true);
        List<Map<String, Object>> application = applyDao.getApplyByTid(tid, ApplyStatus.PENDING).stream()
                .map(Apply::getId)
                .map(userDao::getById)
                .map(this::collectData)
                .collect(Collectors.toList());
        log.trace("All application is {}", application);
        res.put("application", application);
        return res;
    }

    private Map<String, Object> collectData(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", user.getId());
        map.put("username", user.getName());
        map.put("avatar", user.getAvatar());
        return map;
    }
}
