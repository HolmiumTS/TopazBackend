package GregTech.TopazBackend.response.Message;


import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.MessageDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.Message;
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
public class GetUserMessage {

    private static final Logger logger = LoggerFactory.getLogger(GetUserMessage.class);// TODO: 2020/8/16
    private final MessageDao messageDao;
    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public GetUserMessage(DocDao docDao, Users users, MessageDao messageDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.messageDao = messageDao;
    }

    @RequestMapping(value = "/GetUserMessage",//
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        Map<String, Object> res = new HashMap<>();
        List<Message> messages = messageDao.getSortedMsg(id);
        List<Map<String, Object>> msgs = new ArrayList<>();
        for (Message message : messages) {
            msgs.add(collectData(message));
        }
        res.put("messages", msgs);
        return res;
    }

    private Map<String, Object> collectData(Message message) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(message.getMid()));
        map.put("content", message.getContent());
        map.put("time", message.getStrTime());
        map.put("status",message.getStatus());
        return map;
    }

}

