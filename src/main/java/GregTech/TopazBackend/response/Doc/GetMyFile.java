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
public class GetMyFile {
    private static final Logger log = LoggerFactory.getLogger(GetMyFile.class);
    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public GetMyFile(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
    }

    @RequestMapping(value = "/GetMyFile",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        Map<String, Object> res = new HashMap<>();
        List<Map<String, Object>> files = new ArrayList<>();
        List<Doc> docs = docDao.getDocsByOwner(id);
        //存入 files list
        for (Doc doc : docs) {
            files.add(colletData(doc, id));
        }
        log.warn("files are {}", files);
        res.put("files", files);
        return res;
    }

    private Map<String, Object> colletData(Doc doc, int id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(doc.getDid()));
        map.put("name", doc.getName());
        map.put("team", String.valueOf(doc.getTeam()));
        map.put("collected", docDao.isCollected(id, doc.getDid()) ? "已收藏" : "未收藏");
        map.put("view", doc.isView() ?String.valueOf(1) :String.valueOf(0) );
        map.put("edit", String.valueOf(doc.getEdit()) );
        return map;
    }

}
