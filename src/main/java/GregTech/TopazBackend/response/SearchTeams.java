package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SearchTeams {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    private final Teams teamDao;

    @Autowired
    public SearchTeams(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/SearchTeams",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        String key = (String) body.get("keyword");
        List<Team> teamList = new ArrayList<>();
        Set<Integer> abortSet = new HashSet<>();
        if (key.matches("[0-9]{1,9}")) {
            int tid = Integer.parseInt(key);
        }
        return null;
    }
}
