package GregTech.TopazBackend.response;

import GregTech.TopazBackend.dao.Applies;
import GregTech.TopazBackend.dao.Teams;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.dsig.spec.HMACParameterSpec;
import java.util.HashMap;
import java.util.Map;

@RestController
public class CreateTeam {
    private static final Logger log = LoggerFactory.getLogger(ApplyToTeam.class);

    private final Teams teamDao

    @Autowired
    public CreateTeam(Teams teamDao) {
        this.teamDao = teamDao;
    }

    @RequestMapping(value = "/ApplyToTeam",
            method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        Map<String, Object> res = new HashMap<>();
        int owner = Integer.parseInt((String) body.get("id"));
        return res;
    }
}
