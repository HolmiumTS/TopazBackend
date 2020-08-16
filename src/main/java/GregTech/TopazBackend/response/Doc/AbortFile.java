package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.synchronize.Cooperation;
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
public class AbortFile {

    private static final Logger logger = LoggerFactory.getLogger(AbortFile.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;
    private final Cooperation cooperation;

    @Autowired
    public AbortFile(DocDao docDao, Users users, Cooperation cooperation) {
        this.docDao = docDao;
        this.userDao = users;
        this.cooperation = cooperation;
    }

    @RequestMapping(value = "/AbortFile",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int id = Integer.parseInt((String) body.get("id"));
        int did = Integer.parseInt((String) body.get("did"));
        Doc doc = docDao.getDocByDid(did);
        if (doc == null) {
            logger.warn("target is empty");
            res.put("result", true);
            return res;
        }
        if (doc.isLocked()) {
            cooperation.returnLock(doc.getDid());
        }
        res.put("result", true);
        return res;
    }
}

