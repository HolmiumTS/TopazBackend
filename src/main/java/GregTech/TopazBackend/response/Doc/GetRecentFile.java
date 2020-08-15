package GregTech.TopazBackend.response.Doc;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
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
public class GetRecentFile {
    private static final Logger logger=LoggerFactory.getLogger(GetRecentFile.class);
    private final DocDao docDao;
    private final Users userDao;

    @Autowired public GetRecentFile(DocDao docDao, Users userDao){
        this.docDao=docDao;
        this.userDao=userDao;
    }
    @RequestMapping(value ="/GetRecentFile"
                        ,method = RequestMethod.POST,produces = "application/json;charset=UTF-8")
    public Map<String, Object> response(@RequestBody Map<String, Object> body){
        List<Map<String,Object>> docs=new ArrayList<>();
        Map<String ,Object> res=new HashMap<>();
        int[] dids =  userDao.getById(Integer.parseInt( (String)body.get("id"))).getLatestDoc();
        int id=Integer.parseInt((String)body.get("id"));
        if (dids.length==0){
            logger.warn("{} has no recent file",id);
            return null;
        }
        for (int did : dids) {
            docs.add(colletData(docDao.getDocByDid(did),id));
        }
        res.put("files",docs);
        logger.warn("res is {}",res);
        return res;
    }

    // TODO: 2020/8/15
    private Map<String ,Object> colletData (Doc doc,int id){
        boolean inside =false;
        Map<String ,Object>map=new HashMap<>();
        map.put("id",String.valueOf(doc.getDid()));
        map.put("name",doc.getName());
        map.put("username",userDao.getById(doc.getOwner()).getName());
        map.put("team",String.valueOf(doc.getTeam()));
        map.put("time",String.valueOf(doc.getUpdate()));
        for (int s : userDao.getById(id).getCollectedDoc()) {
            if (s==doc.getDid()){
              inside=true;
            }
        }
        map.put("collected",inside?"已收藏":"未收藏");
        map.put("view",doc.isView()?1:0);
        map.put("edit",doc.getEdit());
        return map;
    }

}
