package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetCollectedFIle {

    private static final Logger logger = LoggerFactory.getLogger(GetCollectedFIle.class);

    private final DocDao docDao;
    private final Users userDao;
    @Autowired
    public GetCollectedFIle(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
    }

    @RequestMapping(value = "/GetCollectedFile",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        logger.warn("start from here \n");

        int id = Integer.parseInt((String) body.get("id"));
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> files = new ArrayList<>();
        List<Doc> docs = docDao.getCollectedDocsByID(id);
        //存入 files list
        for (Doc doc : docs) {
            if (!doc.isDel()){
                files.add(colletData(doc, id));
            }
        }
        logger.warn("files are {}",files);
        res.put("files", files);
        return res;
    }
    private Map<String, Object> colletData(Doc doc, int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(doc.getDid()));
        map.put("name", doc.getName());
        map.put("username", userDao.getById(doc.getOwner()).getName());
        map.put("team", String.valueOf(doc.getTeam()));
        map.put("view", doc.isView() ?String.valueOf(1)  : String.valueOf(0));
        map.put("edit", String.valueOf(doc.getEdit()));
        map.put("owner",String.valueOf(doc.getOwner()));
        return map;
    }
}

