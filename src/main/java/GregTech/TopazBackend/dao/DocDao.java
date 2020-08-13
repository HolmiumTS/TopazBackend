package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Doc;
import org.springframework.stereotype.Repository;

@Repository("docDao")
public class DocDao {
    /**
     * @param did doc id
     * @return null if no such doc
     */
    public Doc getDocByDid(int did) {
        return null;
    }

    /**
     * @param doc new doc, id is set to -1
     * @return id of the doc, -1 if failed
     */
    public int addDoc(Doc doc) {
        return -1;
    }

    /**
     * @param doc new doc
     * @return false if failed
     */
    public boolean updateDoc(Doc doc) {
        return true;
    }

    /**
     * !!!!
     * Notice
     * Remove the doc from database
     * Completely delete the doc
     * if you want to set the status of the doc to 'Is deleted', please use updateDoc
     *
     * @param did doc id
     * @return false if
     * @see #updateDoc(Doc)
     */
    public boolean delDoc(int did) {
        return true;
    }
}
