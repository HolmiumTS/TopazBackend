package GregTech.TopazBackend.response;

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
public class GetUserInfo {
    private static final Logger log = LoggerFactory.getLogger(GetUserInfo.class);

    private final Users userDao;

    @Autowired
    public GetUserInfo(Users userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/GetUserInfo",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        log.trace("Id is {}", id);
        User user = userDao.getById(id);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            log.warn("Get failed: No such user.");
            result.put("result", false);
        } else {
            result.putAll(collectData(user));
            log.trace("Get successfully.");
            result.put("result", true);
        }
        return result;
    }

    private Map<String, Object> collectData(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("username", user.getName());
        map.put("tel", user.getTel());
        map.put("email", user.getEmail());
        map.put("avatar", user.getAvatar());
        return map;
    }
}
