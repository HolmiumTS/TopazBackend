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
public class GetFile {

    private static final Logger logger = LoggerFactory.getLogger(GetFile.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public GetFile(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
    }

    @RequestMapping(value = "/GetFile",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Doc doc = docDao.getDocByDid(Integer.parseInt((String) body.get("id")));
        Map<String ,Object>res =new HashMap<>();
        res.put("result",doc!=null);
        res.put("owner",String.valueOf(doc.getOwner()));
        res.put("createTime",doc.getStrCreate());
        res.put("updateTime",doc.getStrUpdate());
        res.put("content",doc.getContent());
        res.put("count",String.valueOf(doc.getCount()/2));
        res.put("name",doc.getName());
        res.put("tid",doc.getTeam());
        // TODO: 2020/8/17  似乎还要加字段
        return res;
    }
}

