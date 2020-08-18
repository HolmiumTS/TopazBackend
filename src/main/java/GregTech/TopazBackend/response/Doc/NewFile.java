package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.metadata.Doc;
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
public class NewFile {
    private static final Logger logger = LoggerFactory.getLogger(NewFile.class);

    private final DocDao docDao;

    @Autowired
    public NewFile(DocDao docDao) {
        this.docDao = docDao;
    }

    @RequestMapping(value = "/NewFile",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Doc doc = new Doc();
        doc.setOwner(Integer.parseInt((String) body.get("userId")));
        doc.setTeam(Integer.parseInt((String) body.get("teamId")));
        doc.setName((String) body.get("name"));
        int tpid = Integer.parseInt((String) body.get("templateId"));
        doc.setContent(tpid == -1 ? "" : docDao.getDocByDid(tpid).getContent());
        logger.warn("doc is {}", doc);
        int id = docDao.addDoc(doc);
        Map<String, Object> res = new HashMap<>();
        res.put("result", id != -1);
        return res;
    }

}
