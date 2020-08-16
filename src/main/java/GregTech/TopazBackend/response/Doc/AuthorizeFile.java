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

import java.util.HashMap;
import java.util.Map;

@RestController
public class AuthorizeFile {

private static final Logger logger = LoggerFactory.getLogger(AuthorizeFile.class);// TODO: 2020/8/16

private final DocDao docDao;
private final Users userDao;
@Autowired
public AuthorizeFile(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
        }

@RequestMapping(value = "/AuthorizeFile",// TODO: 2020/8/16
        method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int view =Integer.parseInt((String)body.get("view"));
        int edit =Integer.parseInt((String)body.get("edit"));
        Doc doc = docDao.getDocByDid(Integer.parseInt((String) body.get("id")));
        Map<String ,Object>res =new HashMap<>();
        if (doc==null){
                res.put("result",false);
                return res;
        }
        doc.setView(view==0?false:true);
        doc.setEdit(edit);
        res.put("result",true);
        //todo
        return res;
        }

}

