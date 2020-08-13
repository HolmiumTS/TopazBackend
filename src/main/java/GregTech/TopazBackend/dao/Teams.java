package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository("teamDao")
public class Teams {

    private final JdbcTemplate jdbc;

    @Autowired
    public Teams(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

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
    public List<Team> getTeamsById(int id) {
        //todo
        String sql = "select t.tid, name, owner, info from team t,u_t ut where ut.user=? and ut.team=t.tid";
        List<Team> teams = jdbc.query(sql, new TeamsMapper());

        return new ArrayList<>();
    }

    /**
     * @param tid team id
     * @return null if no such team
     */
    public Team getTeamByTid(int tid) {

        String sql = "select * from team T where tid=?";
        try {
            Team team = jdbc.queryForObject(sql, new TeamsMapper(), tid);
            return team;
        } catch (EmptyResultDataAccessException e) {
            return null;

        }
    }

    /**
     * Set a user in a team to be (or not to be) an admin
     *
     * @param tid     team id
     * @param id      user id
     * @param isAdmin true (add an admin), false (del an admin)
     * @return false if the admin is the owner
     */
    public boolean setAdmin(int tid, int id, boolean isAdmin) {
        //todo
        return true;
    }

    /**
     * Remove a user from a team
     *
     * @param tid team id
     * @param id  user id
     * @return false if the user is the owner of the team (and do not remove this user)
     */
    public boolean removeUser(int tid, int id) {
        //todo
        return true;
    }
}
