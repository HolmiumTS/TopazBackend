package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Team;
import GregTech.TopazBackend.response.CancelAdmin;
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
import java.util.ArrayList;
import java.util.List;


@Repository("teamDao")
public class Teams {
    private static final Logger log = LoggerFactory.getLogger(Teams.class);
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
        String sql = "select t.tid, name, owner, info from team t,u_t ut where ut.user=? and ut.team=t.tid";
        List<Team> teams = jdbc.query(sql, new TeamsMapper(),id);
        return teams;
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
            log.warn("Exception happened in getTeamByTid forEmptyResultDataAccess ");
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
        try {
            String sql = "update u_t ut set ut.isAdmin=? where team=? and user=?";
            int i = jdbc.update(sql, isAdmin ? 1 : 0, tid, id);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("err happened in setAdmin");
            return false;
        }

    }

    /**
     * Remove a user from a team
     *
     * @param tid team id
     * @param id  user id
     * @return false if the user is the owner of the team (and do not remove this user)
     */
    public boolean removeUser(int tid, int id) {
        try {
            String sql = "delete from u_t where team=? and user=?";
            int i = jdbc.update(sql, tid, id);
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("err happened in removeUser");
            return false;
        }

    }

    /**
     * Update team info
     *
     * @param team new team info
     * @return false if (I don't know)
     */
    public boolean updateTeam(Team team) {
        //untest
        try {
            String sql = "update team t set t.name=?,t.owner=?,t.info=? where t.tid=?";
            int i = jdbc.update(sql, team.getName(), team.getOwner(), team.getInfo(), team.getTid());
            if (i > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            log.warn("err happened in updateTeam");
            return false;
        }
    }

    /**
     * @param team team info, tid is set to -1
     * @return tid, -1 if failed
     */
    public int addTeam(Team team) {
        //untest
        try {
            String sql = "insert  into team( name, owner, info) values(?,?,?) ";
            KeyHolder keyHolder = new GeneratedKeyHolder();
            int i = jdbc.update(new PreparedStatementCreator() {
                @Override
                public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
                    PreparedStatement ps = con.prepareStatement(sql, new String[]{"tid"});
                    ps.setString(1, team.getName());
                    ps.setInt(2, team.getOwner());
                    ps.setString(3, team.getInfo());
                    return ps;
                }
            }, keyHolder);
            int id=keyHolder.getKey().intValue();
            String sql2 ="insert into u_t (user, team, isAdmin) values (?,?,?)";
            //set creator as admin of the created team
            jdbc.update(sql2,team.getOwner(),id,1);
            return id;
        } catch (Exception e) {
            log.warn("err happened in addTeam ");
            return -1;
        }

    }

    /**
     * Delete team, including all docs and all u_t relation
     *
     * @param tid team id
     * @return true
     */
    public boolean delTeam(int tid) {
        //todo
        try {
            String sql2 = "delete from u_t ut where ut.team =?";
            String sql = "delete from team t where t.tid=?";
            jdbc.update(sql2, tid);
            jdbc.update(sql, tid);
        } catch (Exception e) {
            log.warn("err happened in delTeam");
        }
        return false;
    }

    /**
     * Add new member to team (normal member)
     *
     * @param id  user id
     * @param tid team id
     * @return false if member is already in team
     */
    public boolean addMember(int id, int tid) {
        //todo
        return true;
    }

    /**
     * @param name team name
     * @return team whose name contains the given name
     */
    public List<Team> getTeamByName(String name) {
        //todo
        return null;
    }
}
