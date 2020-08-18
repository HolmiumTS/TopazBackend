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

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetTeamFile {

    private static final Logger logger = LoggerFactory.getLogger(GetTeamFile.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public GetTeamFile(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
    }

    @RequestMapping(value = "/GetTeamFile",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        int teamId = Integer.parseInt((String) body.get("teamId"));
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> docMap = new ArrayList<>();
        try {
            List<Doc> docs = docDao.getTeamFileByTid(teamId);
            if (docs.isEmpty()) {
                logger.warn("this is an empty team");
            }
            for (Doc doc : docs) {
                if (!doc.isDel()) {
                    docMap.add(colletData(doc, id));
                }
            }
            logger.warn("teamIs {} \n teamFiles are{}", teamId, docMap);
            res.put("result", true);
            res.put("files", docMap);
            return res;
        } catch (Exception e) {
            res.put("result", true);
            return res;
        }
    }

    private Map<String, Object> colletData(Doc doc, int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(doc.getDid()));
        map.put("name", doc.getName());
        map.put("owner", String.valueOf(doc.getOwner()));
        map.put("team", String.valueOf(doc.getTeam()));
        map.put("isDel", String.valueOf(doc.isDel()));
        map.put("collected", docDao.isCollected(id, doc.getDid()) ? "已收藏" : "未收藏");
        map.put("view", doc.isView() ? String.valueOf(1) : String.valueOf(0));
        map.put("edit", String.valueOf(doc.getEdit()));
        return map;
    }
}

