package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Team;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class Teams {

    @Resource
    private static JdbcTemplate jdbc = new JdbcTemplate();

    private static class TeamsMapper implements RowMapper<Team> {

        @Override
        public Team mapRow(ResultSet rs, int rowNum) throws SQLException {
            Team team = new Team();
            team.setTid(rs.getInt("tid"));
            team.setInfo(rs.getString("info"));
            team.setName(rs.getString("name"));
            team.setOwner(rs.getInt("owner"));
            return team;
        }
    }

    /**
     * @param id user's id
     * @return a list of all teams that include this user, return an empty list if none
     */
    public static List<Team> getTeamsById(int id) {
        //todo

            String sql ="";
            JdbcTemplate jdbc =new TemplateInit().getTemplate();
            List<Team> teams = jdbc.query(sql,new TeamsMapper());

        return new ArrayList<>();
    }

    /**
     * @param tid team id
     * @return null if no such team
     */
    public static Team getTeamByTid(int tid) {

    String sql="select * from Team T where tid=?";
    try {
        JdbcTemplate jdbc =new TemplateInit().getTemplate();
        Team team =jdbc.queryForObject(sql, new TeamsMapper(),tid);
        return team;
    }catch (EmptyResultDataAccessException e){
        return null;

    }}

    /**
     * Set a user in a team to be (or not to be) an admin
     *
     * @param tid     team id
     * @param id      user id
     * @param isAdmin true (add an admin), false (del an admin)
     * @return false if the admin is the owner
     */
    public static boolean setAdmin(int tid, int id, boolean isAdmin) {
        return true;
    }
}
