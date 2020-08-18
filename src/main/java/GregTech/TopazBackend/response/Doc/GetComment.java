package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Comment;
import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.User;
import GregTech.TopazBackend.tool.ToolClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class GetComment {

    private static final Logger logger = LoggerFactory.getLogger(GetComment.class);// TODO: 2020/8/16

    private final DocDao docDao;
    private final Users userDao;

    @Autowired
    public GetComment(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
    }

    @RequestMapping(value = "/GetComment",// TODO: 2020/8/16
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int did = Integer.parseInt((String)body.get("did"));
        List<Comment> comments=docDao.getCommentsByDid(did);
        List<Map<String ,Object>> maps=new ArrayList<>();
        for (Comment comment : comments) {
            maps.add(collectData(comment));
        }

        Map<String, Object> res = new HashMap<>();
        res.put("result",true);
        res.put("comment",maps);
        // TODO: 2020/8/17  似乎还要加字段
        logger.trace("res is {}", res);
        return res;
    }

    Map<String, Object> collectData(Comment comment) {
        Map<String, Object> map = new HashMap<>();
        User user = userDao.getById(comment.getUid());
        map.put("avatar", user.getAvatar());
        map.put("name", user.getName());
        map.put("content", comment.getContent());
        map.put("time", String.valueOf(ToolClass.stamp2time(new Timestamp(comment.getTime()))));
        return map;
    }
}

