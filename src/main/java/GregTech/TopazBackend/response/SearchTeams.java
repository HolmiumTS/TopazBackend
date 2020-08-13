package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Applies;
import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Apply;
import GregTech.TopazBackend.metadata.ApplyStatus;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;

@RestController
public class SearchTeams {
    private static final Logger log = LoggerFactory.getLogger(Login.class);

    private final Teams teamDao;
    private final Applies applyDao;

    @Autowired
    public SearchTeams(Teams teamDao, Applies applyDao) {
        this.teamDao = teamDao;
        this.applyDao = applyDao;
    }

    @RequestMapping(value = "/SearchTeams",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        String key = (String) body.get("keyword");
        List<Team> teamList = new ArrayList<>();
        Set<Integer> abortSet = new HashSet<>();
        if (key.matches("[0-9]{1,9}")) {
            int tid = Integer.parseInt(key);
            Team t = teamDao.getTeamByTid(tid);
            if (t != null) {
                teamList.add(t);
            }
        }
        teamList.addAll(teamDao.getTeamByName(key));
        abortSet.addAll(teamDao.getTeamsById(id).stream()
                .map(Team::getTid)
                .collect(Collectors.toSet())
        );
        abortSet.addAll(applyDao.getApplyById(id, ApplyStatus.PENDING).stream()
                .map(Apply::getTid)
                .collect(Collectors.toSet())
        );
        List<Map<String, Object>> res = teamList.stream()
                .dropWhile(team -> abortSet.contains(team.getTid()))
                .map(this::collectData)
                .collect(Collectors.toList());
        log.trace("Team list is {}", res);
        return res;
    }

    private Map<String, Object> collectData(Team team) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(team.getTid()));
        map.put("name", team.getName());
        map.put("info", team.getInfo());
        return map;
    }
}
