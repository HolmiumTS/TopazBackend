package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("userDao")
public class Users {
    private final JdbcTemplate jdbc;
    private static final Logger log = LoggerFactory.getLogger(Users.class);

    @Autowired
    public Users(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    /**
     * @return return RowMapper<User> as the second argument of queryForObject
     */
    private static class UserMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet rs, int rowNum) throws SQLException {
            User user1 = new User();
            user1.setId(rs.getInt("id"));
            user1.setName(rs.getString("name"));
            user1.setPassword(rs.getString("password"));
            user1.setEmail(rs.getString("email"));
            user1.setLatestDoc(rs.getString("latestDoc"));
            user1.setTel(rs.getString("tel"));
            user1.setAvatar(rs.getString("avatar"));
            user1.setLatestDoc(rs.getString("latestDoc"));
            return user1;
        }
    }


    /**
     * @param id user's id
     * @return null if no such user
     */

    public User getById(int id) {
        // Tested
        try {
            String sql = "select  * from user u where u.id=?";
            User user = jdbc.queryForObject(sql, new UserMapper(), id);
            log.warn("user is {}", user);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * @param email user's email
     * @return null if no such email
     */
    public User getByEmail(String email) {
        try {//Tested
            String sql = "select  * from user u where u.email=?";
            User user = jdbc.queryForObject(sql, new UserMapper(), email);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * @param tel user's tel
     * @return null if no such email
     */
    public User getByTel(String tel) {
        try {//Tested
            String sql = "select  * from user u where u.tel=?";
            User user = jdbc.queryForObject(sql, new UserMapper(), tel);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * @param user user with new info
     * @return false if email or tel is duplicate, otherwise true
     */
    public boolean updateUser(User user) {
        try {
            String sql = "update user u set u.id=?,u.name=?,u.password=?,u.email=?" +
                    ",u.tel=?,u.avatar=?,u.latestDoc=? where u.id=?";
            int i = jdbc.update(sql, user.getId(), user.getName(), user.getPassword()
                    , user.getEmail(), user.getTel(), user.getAvatar(), user.getId(),user.getStrRecent());
            if (i > 0) {
                return true;
            } else {
                log.error("{}", i);
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }

    /**
     * @param user user's info, notice that 'id' is set to -1
     * @return user's id, -1 if failed
     */
    public int addUser(User user) {
        try {

            String sql = "insert  into user(name,password,email,tel,avatar) values(?,?,?,?,?) ";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int i = jdbc.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                    ps.setString(1, user.getName());
                    ps.setString(2, user.getPassword());
                    ps.setString(3, user.getEmail());
                    ps.setString(4, user.getTel());
                    ps.setString(5, "http://qexiy12gt.hd-bkt.clouddn.com/%E9%BB%84%E7%8E%89.png");
                    return ps;
                }
            }, keyHolder);
            return keyHolder.getKey().intValue();
        } catch (Exception e) {
            return -1;
        }
    }

    /**
     * get all user who is not an admin in a team
     *
     * @param tid id of the team
     * @return an empty list if no such user exists
     */
    public List<User> getNormalUserByTid(int tid) {
        // unTest
        String sql = "select id, name, password, email, latestdoc, tel, avatar, user, team, isadmin from user u,u_t ut where u.id=ut.user and ut.team=? and ut.isAdmin=0";
        return jdbc.query(sql, new UserMapper(), tid);
    }

    /**
     * get all admin in a team
     *
     * @param tid id of the team
     * @return a list, (of course there is at least one admin in a team that actually exists)
     */
    public List<User> getAdminUserByTid(int tid) {
        //untest
        String sql = "select id, name, password, email, latestdoc, tel, avatar, user, team, isadmin from user u,u_t ut where u.id=ut.user and ut.team=? and ut.isAdmin=1";
        return jdbc.query(sql, new UserMapper(), tid);
    }


    /**
     * @param  did id of doc
     * @param  id id of user
     * @return true if did is collected by id,otherwise false
     */
    /*public boolean isCollected(int did,int id) {
        int[]collectList=this.getById(id).getCollectedDoc();
        for (int i : collectList) {
            if (did==i){
                return true;
            }
        }
        return false;
    }*/

}
