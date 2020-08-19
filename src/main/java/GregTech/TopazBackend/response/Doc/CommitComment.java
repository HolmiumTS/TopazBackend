package GregTech.TopazBackend.response.Doc;

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
public class CommitComment {

    private static final Logger logger = LoggerFactory.getLogger(CommitComment.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;
    private final MessageDao messageDao;

    @Autowired
    public CommitComment(DocDao docDao, Users users,MessageDao messageDao) {
        this.docDao = docDao;
        this.userDao = users;
        this.messageDao=messageDao;
    }

    @RequestMapping(value = "/CommitComment",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        int did = Integer.parseInt((String) body.get("did"));
        String content = (String) body.get("content");
        boolean result = docDao.commitComment(id, did, content);
        Map<String, Object> res = new HashMap<>();
        res.put("result", result);
        messageDao.generateNewMsg(id, docDao.getDocByDid(did).getOwner(),
                "文档:"+ docDao.getDocByDid(did).getName()+"有新的评论");
        logger.trace("res is {}", res);
        return res;
    }
}

