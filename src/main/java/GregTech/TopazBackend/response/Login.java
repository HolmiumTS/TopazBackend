package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    @RequestMapping(value = "/Login",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        String uu = (String) body.get("user");
        Map<String, Object> res = new HashMap<>();
        User user = null;
        if (uu.matches("[0-9]{1,9}")) {
            int id = Integer.parseInt(uu);
            user = Users.getById(id);
            if (user != null) {
                collectData(res, user);
                res.put("result", true);
                log.trace("Login by id successfully. Id is {}", id);
                return res;
            }
        }
        if (uu.matches(".*@.*\\..*")) {
            user = Users.getByEmail(uu);
            if (user != null) {
                collectData(res, user);
                res.put("result", true);
                log.trace("Login by email successfully. Email is {}", uu);
                return res;
            }
        }
        if (uu.matches("[0-9]{8,11}")) {
            user = Users.getByTel(uu);
            if (user != null) {
                collectData(res, user);
                res.put("result", true);
                log.trace("Login by tel successfully. Tel is {}", uu);
                return res;
            }
        }
        res.put("result", false);
        log.trace("Login failed. Body is {}", body);
        return res;
    }

    private void collectData(Map<String, Object> res, User user) {
        res.put("id", String.valueOf(user.getId()));
        res.put("username", user.getName());
        res.put("avatar", user.getAvatar());
    }
}
