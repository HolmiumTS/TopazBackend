package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Doc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository("docDao")
public class DocDao {
    private final JdbcTemplate jdbc;
    private static final Logger log = LoggerFactory.getLogger(DocDao.class);

    @Autowired
    public DocDao(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * @return return RowMapper<Doc>as the second argument of queryForObject and jdbc.query
     */
    private static class DocMapper implements RowMapper<Doc> {

        @Override
        public Doc mapRow(ResultSet rs, int rowNum) throws SQLException {
            Doc doc = new Doc();
            doc.setDid(rs.getInt("did"));
            doc.setName(rs.getString("name"));
            doc.setOwner(rs.getInt("owner"));
            doc.setTeam(rs.getInt("team"));
            doc.setView(rs.getBoolean("view"));
            doc.setEdit(rs.getBoolean("edit"));
            doc.setCreate(rs.getTimestamp("create").getTime());
            doc.setUpdate(rs.getTimestamp("update").getTime());
            doc.setCount(rs.getInt("count"));
            doc.setContent(rs.getString("content"));
            doc.setDel(rs.getBoolean("isdel"));
            return doc;
        }
    }


    /**
     * @param did doc id
     * @return null if no such doc
     */
    public Doc getDocByDid(int did) {
        String sql = "select  * from doc d where d.did=?";
        try {
            return jdbc.queryForObject(sql, new DocMapper(), did);
        } catch (EmptyResultDataAccessException e) {
            log.warn("did:"+did+"is not a valid did");
            return null;
        }
    }

    /**
     * @param doc new doc, id is set to -1
     * @return id of the doc, -1 if failed
     */
   /* public int addDoc(Doc doc) {
        String sql="";
        KeyHolder keyHolder=new GeneratedKeyHolder();
        int i =jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps =con.prepareStatement(sql, new String[]{"did"});
                ;

            };
        });
        return -1;
    }*/

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
