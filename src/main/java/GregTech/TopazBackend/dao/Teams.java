package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Team;

import java.util.ArrayList;
import java.util.List;

public abstract class Teams {
    /**
     * @param id user's id
     * @return a list of all teams that include this user, return an empty list if none
     */
    public static List<Team> getTeamsById(int id) {
        //todo
        return new ArrayList<>();
    }


    /**
     * @param tid team id
     * @return null if no such team
     */
    public static Team getTeamByTid(int tid) {
        //todo
        return null;
    }
}
