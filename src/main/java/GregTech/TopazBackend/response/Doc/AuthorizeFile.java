package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class AuthorizeFile {

private static final Logger logger = LoggerFactory.getLogger(AuthorizeFile.class);// TODO: 2020/8/16

private final DocDao docDao;
private final Users userDao;
@Autowired
public AuthorizeFile(DocDao docDao, Users users) {
        this.docDao = docDao;
        this.userDao = users;
        }

@RequestMapping(value = "/AuthorizeFile",// TODO: 2020/8/16
        method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
public Map<String, Object> response(@RequestBody Map<String, Object> body) {
        return null;
        }
        }

