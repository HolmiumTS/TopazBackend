package GregTech.TopazBackend.dao;

import GregTech.TopazBackend.metadata.User;

public abstract class Users {
    /**
     * @param id user's id
     * @return null if no such user
     */
    public static User getById(int id) {
        return null;
    }

    /**
     * @param email user's email
     * @return null if no such email
     */
    public static User getByEmail(String email) {
        return null;
    }

    /**
     * @param tel user's tel
     * @return null if no such email
     */
    public static User getByTel(String tel) {
        return null;
    }

    /**
     * @param user user with new info
     * @return false if email or tel is duplicate, otherwise true
     */
    public static boolean updateUser(User user) {
        return false;
    }

    /**
     * @param user user's info, notice that 'id' is set to -1
     * @return user's id, -1 if failed
     */
    public static int addUser(User user) {
        return -1;
    }

}
