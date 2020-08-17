package GregTech.TopazBackend.response.Template;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.TemplateDao;
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
public class DeleteTemplate {

    private static final Logger logger = LoggerFactory.getLogger(DeleteTemplate.class);// TODO: 2020/8/16
    private final TemplateDao templateDao;
    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public DeleteTemplate(DocDao docDao, Users users, TemplateDao templateDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.templateDao = templateDao;
    }

    @RequestMapping(value = "/DeleteTemplate",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int uid = Integer.parseInt((String) body.get("userId"));
        int temid = Integer.parseInt((String) body.get("templateId"));
        boolean result = templateDao.deleteTemplate(uid, temid);
        res.put("result", String.valueOf(result));
        return res;
    }
}

