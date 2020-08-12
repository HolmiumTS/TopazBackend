package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Users {
    @Resource
    private static JdbcTemplate jdbc = new JdbcTemplate();

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
            return user1;
        }
    }


    /**
     * @param id user's id
     * @return null if no such user
     */
    public static User getById(int id) {
        try {
            String sql = "select  * from User U where U.id=?";
            User user = jdbc.queryForObject(sql, new UserMapper(), id);
            return user;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

    }

    /**
     * @param email user's email
     * @return null if no such email
     */
    public static User getByEmail(String email) {
        try {
            String sql = "select  * from User U where U.email=?";
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
    public static User getByTel(String tel) {
        try {
            String sql = "select  * from User U where U.tel=?";
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
    public static boolean updateUser(User user) {
        String sql = "update User u set u.id=?,u.name=?,u.password=?,u.email=?" +
                ",u.latestDoc=?,u.tel=?,u.avatar=? where u.id=?";
        int i = jdbc.update(sql, user.getId(), user.getName(), user.getPassword()
                , user.getEmail(), user.getLatestDoc(), user.getTel(), user.getAvatar(), user.getId());
        if (i > 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * @param user user's info, notice that 'id' is set to -1
     * @return user's id, -1 if failed
     */
    public static int addUser(User user) {
        String sql = "insert  into User(name,password,email,latestDoc,tel,avatar) values(?,?,?,?,?,?) ";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int i = jdbc.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                PreparedStatement ps = con.prepareStatement(sql, new String[]{"id"});
                ps.setString(1, user.getName());
                ps.setString(2, user.getPassword());
                ps.setString(3, user.getEmail());
                ps.setString(4, I2S(user.getLatestDoc()));
                ps.setString(5, user.getTel());
                ps.setString(6, user.getAvatar());
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    private static String I2S(int[] docs) {
        String[] bb = new String[docs.length];
        for (int i = 0; i < docs.length; i++) {
            bb[i] = Integer.toString(docs[i]);
        }
        String s = "";
        s.join(";", docs.toString());
        return s;
    }

    /**
     * get all user who is not an admin in a team
     *
     * @param tid id of the team
     * @return an empty list if no such user exists
     */
    public static List<User> getNormalUserByTid(int tid) {
        //todo
        return new ArrayList<>();
    }

    /**
     * get all admin in a team
     * @param tid id of the team
     * @return a list, (of course there is at least one admin in a team that actually exists)
     */
    public static List<User> getAdminUserByTid(int tid) {
        //todo
        return new ArrayList<>();
    }
}
