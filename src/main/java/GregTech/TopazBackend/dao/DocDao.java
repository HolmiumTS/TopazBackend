package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Deldoc;
import GregTech.TopazBackend.metadata.Doc;
import GregTech.TopazBackend.metadata.U_d;
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
    public static class u_dMapper implements RowMapper<U_d> {
        @Override
        public U_d mapRow(ResultSet rs, int rowNum) throws SQLException {
            U_d u_d = new U_d();
            u_d.setDid(rs.getInt("did"));
            u_d.setUid(rs.getInt("uid"));
            return u_d;
        }
    }

    public static class delMapper implements RowMapper<Deldoc> {

        @Override
        public Deldoc mapRow(ResultSet rs, int rowNum) throws SQLException {
            Deldoc deldoc = new Deldoc();
            deldoc.setDid(rs.getInt("deid"));
            deldoc.setTime(rs.getTimestamp("time").getTime());
            deldoc.setExecutor(rs.getInt("executor"));
            return deldoc;
        }
    }

    public boolean isCollected(int id, int did) {
        String sql = "select  * from  u_d where did=? and uid= ?";
        try {
            U_d u_d = jdbc.queryForObject(sql, new u_dMapper(), did, id);
            return true;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }
    }

    public boolean addCollect(int id, int did,int tid) {
        try {
            String sql = "insert into u_d(uid, did,dteam) values (?,?,?)";
            int i = jdbc.update(sql, id, did,tid);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean deleteCollect(int id, int did) {
        try {
            String sql = "delete from u_d where uid=? and did =? ";
            jdbc.update(sql, id, did);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

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
            log.trace("did is {}, c is {}, u is {}", doc.getDid(), doc.getCreate(), doc.getUpdate());
            return doc;
        }
    }

    /**
     * @param id id of User
     * @return all not deleted docs
     * return null if User dont have a doc
     * @// TODO: 2020/8/15
     */
    public List<Doc> getDocsByOwner(int id) {
        String sql = "select * from  doc d where d.owner=? and  d.isdel=0 ";
        return jdbc.query(sql, new DocMapper(), id);
    }

    public List<Doc> getTeamFileByTid(int tid) {
        String sql = "select * from doc where team=?";
        return jdbc.query(sql, new DocMapper(), tid);
    }

    public List<Doc> getCollectedDocsByID(int id) {
        String sql = "select * from doc d ,u_d ud where ud.uid=?";
        return jdbc.query(sql, new DocMapper(), id);
    }

    public List<Deldoc> getDeletedDocsByID(int id) {
        List<Doc> docs = new ArrayList<>();
        String sql = "select * from deldoc where executor=?";
        return jdbc.query(sql, new delMapper(), id);
    }


    /**
     * @param id
     * @return list
     * @// TODO: 2020/8/15
     */
    public List<Doc> getRecentFileByOwner(int id, Users users) {
        int[] recentList = users.getById(id).getLatestDoc();
        List<Doc> docs = new ArrayList<>();
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
                    ps.setBoolean(4, true);
                    ps.setInt(5, 0);
                    ps.setTimestamp(6, new Timestamp(new Date().getTime()));
                    ps.setTimestamp(7, new Timestamp(new Date().getTime()));
                    ps.setInt(8, 1);
                    ps.setString(9, doc.getContent());
                    ps.setBoolean(10, false);
                    return ps;
                }
            }, keyHolder);
            return keyHolder.getKey().intValue();
        } catch (Exception e) {
            log.error("error happened in add Doc");
            e.printStackTrace();
            return -1;
        }


    }


    public boolean isTmpDeletedBy(int id, int did) {
        String sql = "select *from deldoc where executor=? and did= ?";
        try {
            Deldoc deldoc = jdbc.queryForObject(sql, new delMapper(), id, did);
            if (deldoc != null) {
                return true;
            }
            return false;
        } catch (EmptyResultDataAccessException e) {
            return false;
        }

    }

    public boolean restoreDoc(int id, int did) {
        if (isTmpDeletedBy(id, did)) {
            String sql = "delete  from  deldoc where executor=? and  did=?";
            jdbc.update(sql, id, did);
            Doc doc = getDocByDid(did);
            doc.setDel(false);
            updateDoc(doc);
            return true;
        } else {
            return false;
        }
    }

    public boolean tmpDeleteDoc(int id, int did) {
        try {
            String sql2 = "insert  into deldoc(did, time, executor) values (?,?,?)";
            String sql = "update  doc d set d.isdel=1 ";
            int i = jdbc.update(sql);
            if (i > 0) {
                i = jdbc.update(sql2, did, new Timestamp(new Date().getTime()), id);
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("Exception happened in updateDoc");
            e.printStackTrace();
            return false;
        }
    }

    /**
     * @param doc new doc
     * @return false if failed
     */
    public boolean updateDoc(Doc doc) {
        try {
            String sql = "update  doc d set d.name=?,d.owner=?,d.team=?,d.view=?,d.edit=?,d.`update`=?,d.count=?,d.content=?,d.isdel=?,d.islocked=? where did =?";

            int i = jdbc.update(sql, doc.getName(), doc.getOwner(), doc.getTeam(), doc.isView(), doc.getEdit(),
                    new Timestamp(new java.util.Date().getTime()), doc.getCount() + 1, doc.getContent(), doc.isDel(), doc.isLocked(),doc.getDid());
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("Exception happened in updateDoc");
            e.printStackTrace();
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
            String sql1 = "delete from u_d where did=?";
            String sql2 = "delete from deldoc where  did =?";
            String sql = "delete  from doc d where  d.did=?";
            jdbc.update(sql1, did);
            jdbc.update(sql2, did);
            jdbc.update(sql, did);
            return true;
        } catch (Exception e) {
            log.warn("err happened in delDoc");
            return false;
        }

    }


}
