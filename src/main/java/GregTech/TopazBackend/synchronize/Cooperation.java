package GregTech.TopazBackend.synchronize;

import GregTech.TopazBackend.dao.DocDao;
import GregTech.TopazBackend.dao.Users;
import GregTech.TopazBackend.metadata.Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("cooperation")
public class Cooperation {
    private final JdbcTemplate jdbc;
    private final DocDao docDao;
    private static final Logger log = LoggerFactory.getLogger(Cooperation.class);

    @Autowired
    public Cooperation(JdbcTemplate jdbc, DocDao docDao) {
        this.docDao = docDao;
        this.jdbc = jdbc;
    }


    public boolean getLock(int did) {
        synchronized (this) {
            Doc doc = docDao.getDocByDid(did);
            if (doc.isLocked()){
                return false;
            }else {
                doc.setLocked(true);
                docDao.updateDoc(doc);
                return  true;//if update fails ,the doc is not locked
            }
        }
    }

    public boolean returnLock(int did){
        synchronized (this){
            Doc doc =docDao.getDocByDid(did);
            if (doc.isLocked()){
                doc.setLocked(false);
                return docDao.updateDoc(doc);
            }else {
                //this lock does not exist so it need't to be return
                return false;
            }
        }
    }
}
