package GregTech.TopazBackend.response.Team;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Team;
import GregTech.TopazBackend.metadata.User;
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
public class GetTeamMember {
    private static final Logger log = LoggerFactory.getLogger(GetTeamMember.class);

    private final Teams teamDao;
    private final Users userDao;

    @Autowired
    public GetTeamMember(Users userDao, Teams teamDao) {
        this.userDao = userDao;
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/GetTeamMember",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int tid = Integer.parseInt((String) body.get("teamId"));
        Team team = teamDao.getTeamByTid(tid);
        if (team == null) {
            log.warn("Get failed: No such team.");
            res.put("result", false);
            return res;
        }
        List<Map<String,Object>> memberList= new ArrayList<>();
        memberList.add(addType(collectData(userDao.getById(team.getOwner())),"0"));
        //res.put("creatorId", String.valueOf(owner));
        List<User> normalUser=userDao.getNormalUserByTid(tid);
        for (User user : normalUser) {
            memberList.add(addType(collectData(user),"2"));
        }
        List<User> adminUser=userDao.getAdminUserByTid(tid);
        for (User user : adminUser) {
            if (user.getId()!=team.getOwner()) memberList.add(addType((collectData(user)),"1"));
        }
        log.warn("teamMemeber is {}",memberList);
        res.put("memberInfo",memberList);
        res.put("result", true);
        return res;
    }
    private Map<String,Object> collectData(User user){
        Map<String,Object> map=new HashMap<>();
        map.put("memberId",String.valueOf(user.getId()));
        map.put("memberUsername",user.getName());
        map.put("memberAvatar",user.getAvatar());
        return map;
    }
    private Map<String,Object> addType(Map<String,Object> preUser,String type){
        preUser.put("memberType",type);
        return preUser;
    }
}
