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
public class Register {
    private static final Logger log = LoggerFactory.getLogger(Register.class);

    @RequestMapping(value = "/Register",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        User user = new User();
        user.setEmail((String) body.get("email"));
        user.setName((String) body.get("username"));
        user.setTel((String) body.get("tel"));
        int id = Users.addUser(user);
        Map<String, Object> res = new HashMap<>();
        res.put("result", id == -1);
        res.put("id", id);
        if (id == -1) {
            log.trace("Register failed. Body is {}", body);
        } else {
            log.trace("Register successfully. Id is {}", id);
        }
        return res;
    }

}
