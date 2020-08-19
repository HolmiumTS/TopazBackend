package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Apply;
import GregTech.TopazBackend.metadata.ApplyStatus;
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

import java.io.StringReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository("applyDao")
public class Applies {
    private static final Logger log = LoggerFactory.getLogger(Applies.class);
    private final JdbcTemplate jdbc;
    @Autowired
    public Applies(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    private static class ApplyMapper implements RowMapper<Apply>{

        @Override
        public Apply mapRow(ResultSet rs, int rowNum) throws SQLException {
            Apply apply= new Apply();
            apply.setAid(rs.getInt("aid"));
            apply.setId(rs.getInt("id"));
            apply.setStatus(ApplyStatus.values()[rs.getInt("status")]);
            apply.setTime(rs.getTimestamp("time").getTime());
            apply.setTid(rs.getInt("tid"));
            return apply;
        }
    }

    /**
     * Get all applies a user applied (with specific apply status)
     *
     * @param id     user id
     * @param status apply status
     * @return an empty list if nothing
     */
    public List<Apply> getApplyById(int id, ApplyStatus status) {
        try {
            String sql = "select aid, time, id, tid, status from apply a where a.id = ? and a.status = ?";
            return jdbc.query(sql,new ApplyMapper(),id,status.ordinal());
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Get all applies a team received (with specific apply status)
     *
     * @param tid    team id
     * @param status apply status
     * @return an empty list if nothing
     */
    public List<Apply> getApplyByTid(int tid, ApplyStatus status) {
        try {
            String sql = "select aid, time, id, tid, status from apply a where a.tid = ? and a.status = ?";
            List<Apply> applies= jdbc.query(sql,new ApplyMapper(),tid,status.ordinal());
            if (applies.isEmpty()){
                log.warn("applies is empty");
            }else {
                log.warn("applies is not empty");
                log.warn(applies.toString());
            }
            return  applies;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param aid apply id
     * @return null if no such apply
     */
    public Apply getApplyByAid(int aid) {
        //todo
        String sql = " select *from apply where aid=?";
        try{
            Apply apply= jdbc.queryForObject(sql,new ApplyMapper(),aid);
        }
        catch (EmptyResultDataAccessException e){
            log.warn("no such apply");
            return null;
        }

        return null;
    }

    /**
     * Update an apply
     *
     * @param apply the new apply
     * @return false if update failed
     */
    public boolean updateApply(Apply apply) {
        //untest
        try {
            String sql = "update apply a set a.status=? where a.aid=?";
            int i = jdbc.update(sql,apply.getStatus().ordinal(),apply.getAid());
            if (i>0){
                return true;
            }else {
                log.warn("apply not changed");
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            log.warn("apply not changed");
            return false;
        }
    }

    /**
     * @param apply apply info, both aid and time are -1
     * @return apply's id
     */
    public int addApply(Apply apply) {
        //todo
        try {
            String sql = "insert into apply(time, id, tid, status)values(?,?,?,?)";
            KeyHolder keyHolder=new GeneratedKeyHolder();
            int i=jdbc.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    Date date =new Date();
                    PreparedStatement ps = con.prepareStatement(sql,new String[]{"aid"});
                    ps.setTimestamp(1,new Timestamp(date.getTime()));
                    ps.setInt(2,apply.getId());
                    ps.setInt(3,apply.getTid());
                    ps.setInt(4,ApplyStatus.PENDING.ordinal());
                    return ps;
                }
            },keyHolder);
            return keyHolder.getKey().intValue();
        }catch ( Exception e){
            e.printStackTrace();
            log.warn("err happened in addApply");
            return -1;
        }
    }
}
