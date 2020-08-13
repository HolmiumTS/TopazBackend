package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
public class GetUserTeam {
    private static final Logger log = LoggerFactory.getLogger(GetUserTeam.class);

    private final Teams teamDao;

    @Autowired
    public GetUserTeam(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/GetUserTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        List<Team> teamsById = teamDao.getTeamsById(id);
        List<Map<String, Object>> teams = teamsById.stream().map(this::collectData).collect(Collectors.toList());
        log.trace("Result is {}", teams);
        Map<String, Object> res = new HashMap<>();
        res.put("teams", teams);
        return res;
    }

    private Map<String, Object> collectData(Team team) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", String.valueOf(team.getTid()));
        map.put("name", team.getName());
        return map;
    }
}

