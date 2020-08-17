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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class EditDoc{

    private static final Logger logger = LoggerFactory.getLogger(EditDoc.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;
        private final Cooperation cooperation;
    @Autowired
    public EditDoc(DocDao docDao, Users users,Cooperation cooperation) {
        this.docDao = docDao;
        this.userDao = users;
        this.cooperation=cooperation;
    }

    @RequestMapping(value = "/EditFile",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int id = Integer.parseInt((String) body.get("id"));
        int did = Integer.parseInt((String) body.get("did"));
        boolean result = cooperation.getLock(did);
        res.put("result", result);
        return res;
    }
}

