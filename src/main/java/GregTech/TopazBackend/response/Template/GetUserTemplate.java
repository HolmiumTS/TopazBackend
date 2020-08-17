package GregTech.TopazBackend.response.Template;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.TemplateDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.Template;
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
public class  GetUserTemplate{

    private static final Logger logger = LoggerFactory.getLogger(GetUserTemplate.class);// TODO: 2020/8/16

    private final TemplateDao templateDao;
    private final DocDao docDao;
    private final Users userDao;
    @Autowired
    public GetUserTemplate(DocDao docDao, Users users,TemplateDao templateDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.templateDao=templateDao;
    }
    @RequestMapping(value = "/GetUserTemplate",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String ,Object>res=new HashMap<>();
        List<Map<String ,Object>> template=new ArrayList<>();
        int uid =Integer.parseInt((String)body.get("id"));
        List<Template>templates =templateDao.getTemplates(uid);
        for (Template template1 : templates) {
            template.add(collectData(template1));
        }
        res.put("templates",template);

        return res;
    }
    private  Map<String,Object> collectData(Template template){
        Map<String ,Object>map =new HashMap<>();
        map.put("id",String.valueOf(template.getTemid()));
        map.put("name",template.getName());
        return map;
    }
}
