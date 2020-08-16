package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.relational.core.sql.In;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetAuth {

    private static final Logger logger = LoggerFactory.getLogger(GetAuth.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;
    private final Teams teamDao;

    @Autowired
    public GetAuth(DocDao docDao, Users users, Teams teamDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/GetAuth",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int did = Integer.parseInt((String) body.get("did"));
        int id = Integer.parseInt((String) body.get("id"));
        Doc doc = docDao.getDocByDid(did);
        int dteam = doc.getTeam();
        if (doc == null) {
            res.put("result", false);
            return res;
        } else {
            res.put("result", true);
        }
        if (doc.isDel()) {
            res.put("result", true);
            res.put("admin", false);
            res.put("edit", false);
            res.put("view", false);
            res.put("lock", false);
            return res;
        }
        res.put("result", true);
        // this user is the owner of this doc
        if (doc.getOwner() == id) {
            res.put("admin", true);
            res.put("edit", true);
            res.put("view", true);
            res.put("lock", doc.isLocked());
            return res;
        }
        //this doc is team's and user is not admin and
        if (teamDao.isAdmin(id, doc.getTeam())) {
            res.put("admin", true);
            res.put("edit", doc.getEdit() > 0);
            res.put("view", true);
            res.put("edit", doc.getEdit() > 0);
            res.put("lock", doc.isLocked());
            return res;
        }

        if (teamDao.isUser(id, doc.getTeam())) {
            res.put("admin", false);
            res.put("edit", doc.getEdit() > 0);
            res.put("view", true);
            res.put("lock", doc.isLocked());
            return res;
        }
        // user that not in this team or not a user
        res.put("admin", false);
        res.put("edit", doc.getEdit() > 1);
        res.put("view",doc.isView());
        res.put("lock",doc.isLocked());
        return res;
    }
}

