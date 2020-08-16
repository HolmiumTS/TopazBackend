package GregTech.TopazBackend.metadata;

public class U_t {
    int user;
    int team;
    boolean isAdmin;

    public void setUser(int user) {
        this.user = user;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public int getUser() {
        return user;
    }

    public int getTeam() {
        return team;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
