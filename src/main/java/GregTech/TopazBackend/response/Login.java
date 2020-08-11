package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class Login {
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
                return res;
            }
        }
        if (uu.matches(".*@.*\\..*")) {
            user = Users.getByEmail(uu);
            if (user != null) {
                collectData(res, user);
                res.put("result", true);
                return res;
            }
        }
        if (uu.matches("[0-9]{8,11}")) {
            user = Users.getByTel(uu);
            if (user != null) {
                collectData(res, user);
                res.put("result", true);
                return res;
            }
        }
        res.put("result", false);
        return res;
    }

    private void collectData(Map<String, Object> res, User user) {
        res.put("id", String.valueOf(user.getId()));
        res.put("username", user.getName());
        res.put("avatar", user.getAvatar());
    }
}
