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
public class Login {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    private final Users userDao;

    @Autowired
    public Login(Users userDao) {
        this.userDao = userDao;
    }

    @RequestMapping(value = "/Login",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        String uu = (String) body.get("user");
        String password = (String) body.get("password");
        //从 body map 取出 来键所对应的值
        Map<String, Object> res = new HashMap<>();
        User user = null;
        if (uu.matches("[0-9]{1,9}")) {
            int id = Integer.parseInt(uu);
            user = userDao.getById(id);//从数据库取出 user 实例
            if (user != null && password.equals(user.getPassword())) {
                res.putAll(collectData(user));//将user 放入 map ，并将此 map 放入 res
                res.put("result", true);
                log.trace("Login by id successfully. Id is {}", id);
                return res;
            }
        }
        if (uu.matches(".*@.*\\..*")) {
            user = userDao.getByEmail(uu);
            if (user != null && password.equals(user.getPassword())) {
                res.putAll(collectData(user));
                res.put("result", true);
                log.trace("Login by email successfully. Email is {}", uu);
                return res;
            }
        }
        if (uu.matches("[0-9]{8,11}")) {
            user = userDao.getByTel(uu);
            if (user != null && password.equals(user.getPassword())) {
                res.putAll(collectData(user));
                res.put("result", true);
                log.trace("Login by tel successfully. Tel is {}", uu);
                return res;
            }
        }
        res.put("result", false);
        log.trace("Login failed. Body is {}", body);
        return res;
    }

    private Map<String, Object> collectData(User user) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(user.getId()));
        map.put("username", user.getName());
        map.put("avatar", user.getAvatar());
        return map;
    }
}
