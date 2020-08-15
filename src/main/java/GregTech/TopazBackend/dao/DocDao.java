package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.User;
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

import java.util.ArrayList;
import java.util.Date;

import java.sql.*;
import java.util.List;

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
            doc.setEdit(rs.getInt("edit"));
            doc.setCreate(rs.getTimestamp("create").getTime());
            doc.setUpdate(rs.getTimestamp("update").getTime());
            doc.setCount(rs.getInt("count"));
            doc.setContent(rs.getString("content"));
            doc.setDel(rs.getBoolean("isdel"));
            return doc;
        }
    }

    /**
     * @// TODO: 2020/8/15
     * @param  id id of User
     * @return all not deleted docs
     * return null if User dont have a doc
     */
    public List<Doc> getDocsByOwner(int id) {
        String sql="select * from  doc d where d.owner=? and  d.isdel=0 ";
        return jdbc.query(sql,new DocMapper(),id);
    }

    /**
     * @// TODO: 2020/8/15
     * @param id id of user
     * @return deletedDocList
     */

    public List<Doc> getDeletedDocsByOwner(int id ) {
        String sql="select * from  doc d where d.owner=? and  d.isdel=1 ";
        return jdbc.query(sql,new DocMapper(),id);
    }

    /**
     * @// TODO: 2020/8/15
     * @param id
     * @return list
     * */
    public List<Doc> getRecentFileByOwner(int id, Users users){
        int[] recentList=users.getById(id).getLatestDoc();
        List<Doc>docs=new ArrayList<>();
        for (int i : recentList) {
                docs.add(this.getDocByDid(i));
        }
        return docs;
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
            log.warn("did:" + did + "is not a valid did");
            return null;
        }
    }


    /**
     * @param doc new doc, id is set to -1
     * @return id of the doc, -1 if failed
     */
    public int addDoc(Doc doc) {
        String sql = "insert into doc(name, owner, team, view, edit, `create`, `update`, count, content, isdel) values(?,?,?,?,?,?,?,?,?,?)";
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int i = jdbc.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"did"});
                    ps.setString(1, doc.getName());
                    ps.setInt(2, doc.getOwner());
                    ps.setInt(3, doc.getTeam());
                    ps.setBoolean(4, doc.isView());
                    ps.setInt(5, doc.getEdit());
                    ps.setTimestamp(6, new Timestamp(new Date().getTime()));
                    ps.setTimestamp(7, new Timestamp(new Date().getTime()));
                    ps.setInt(8, doc.getCount());
                    ps.setString(9, doc.getContent());
                    ps.setBoolean(10, doc.isDel());
                    return ps;
                }
            }, keyHolder);
            return keyHolder.getKey().intValue();
        } catch (Exception e) {
            log.error("error happened in add Doc");
            return -1;
        }


    }

    /**
     * @param doc new doc
     * @return false if failed
     */
    public boolean updateDoc(Doc doc) {
        try {
            String sql = "update  doc d set d.name=?,d.owner=?,d.team=?,d.view=?,d.edit=?,d.`update`=?,d.count=?,d.content=?,d.isdel=? ";

            int i = jdbc.update(sql, doc.getName(), doc.getOwner(), doc.getTeam(), doc.isView(), doc.getEdit(),
                    new Timestamp(new java.util.Date().getTime()), doc.getCount() + 1, doc.getContent(), doc.isDel());
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("Exception happened in updateDoc");
            return false;
        }
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
        try {
            String sql = "delete  from doc d where  d.did=?";
            jdbc.update(sql, did);
            return true;
        } catch (Exception e) {
            log.warn("err happened in delDoc");
            return false;
        }

    }
}
