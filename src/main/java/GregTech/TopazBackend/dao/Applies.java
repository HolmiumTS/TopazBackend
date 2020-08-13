package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.Apply;
import GregTech.TopazBackend.metadata.ApplyStatus;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("applyDao")
public class Applies {


    /**
     * Get all applies a user applied (with specific apply status)
     *
     * @param id     user id
     * @param status apply status
     * @return an empty list if nothing
     */
    public List<Apply> getApplyById(int id, ApplyStatus status) {
        //todo
        return new ArrayList<>();
    }

    /**
     * Get all applies a team received (with specific apply status)
     *
     * @param tid    team id
     * @param status apply status
     * @return an empty list if nothing
     */
    public List<Apply> getApplyByTid(int tid, ApplyStatus status) {
        //todo
        return new ArrayList<>();
    }

    /**
     * @param aid apply id
     * @return null if no such apply
     */
    public Apply getApplyByAid(int aid) {
        //todo
        return null;
    }

    /**
     * Update an apply
     *
     * @param apply the new apply
     * @return false if update failed
     */
    public boolean updateApply(Apply apply) {
        //todo
        return true;
    }

    /**
     * @param apply apply info, both aid and time are -1
     * @return apply's id
     */
    public int addApply(Apply apply) {
        //todo
        return -1;
    }
}
