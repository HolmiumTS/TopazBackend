package GregTech.TopazBackend.response.Message;


import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.MessageDao;
import GregTech.TopazBackend.dao.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.HashMap;
import java.util.Map;

public class ChangeMessageStatus {
    private static final Logger logger = LoggerFactory.getLogger(ChangeMessageStatus.class);// TODO: 2020/8/16
    private final MessageDao messageDao;
    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public ChangeMessageStatus(DocDao docDao, Users users, MessageDao messageDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.messageDao = messageDao;
    }

    @RequestMapping(value = "/ChangeMessageStatus",//
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        String  [] ids = (String[])body.get("id");
        for (String id : ids) {
            messageDao.markAsRead(Integer.parseInt(id));
        }
        Map<String, Object> res = new HashMap<>();
        res.put("result","true");
        return res;
    }

}
