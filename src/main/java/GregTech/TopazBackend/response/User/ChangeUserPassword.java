package GregTech.TopazBackend.response.User;

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
public class ChangeUserPassword {
    private static final Logger log = LoggerFactory.getLogger(ChangeUserPassword.class);


    private final Users userDao;

    @Autowired
    public ChangeUserPassword(Users userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/ChangeUserPassword",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        log.trace("Id is {}", id);
        User user = userDao.getById(id);
        Map<String, Object> result = new HashMap<>();
        if (user == null) {
            log.warn("Change failed: No such user.");
            result.put("result", false);
        } else if (!user.getPassword().equals(body.get("oldPassword"))) {
            log.trace("Change failed: Wrong old password.");
            result.put("result", false);
        } else {
            user.setPassword((String) body.get("newPassword"));
            boolean r = userDao.updateUser(user);
            if (!r) {
                log.warn("Change failed: Unknown error.");
                result.put("result", false);
            } else {
                log.trace("Change successfully.");
                result.put("result", true);
            }
        }
        return result;
    }
}
