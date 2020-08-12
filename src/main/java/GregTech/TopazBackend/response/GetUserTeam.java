package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Teams;
import GregTech.TopazBackend.metadata.Team;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    @RequestMapping(value = "/GetUserTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public List<Map<String, Object>> response(@RequestBody Map<String, Object> body) {
        int id = Integer.parseInt((String) body.get("id"));
        List<Team> teamsById = Teams.getTeamsById(id);
        List<Map<String, Object>> result = teamsById.stream().map(this::collectData).collect(Collectors.toList());
        log.trace("Result is {}", result);
        return result;
    }

    private Map<String, Object> collectData(Team team) {
        Map<String, Object> map = new HashMap<>();
        map.put("id", team.getTid());
        map.put("name", team.getName());
        return map;
    }

}

