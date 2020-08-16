package GregTech.TopazBackend.metadata;

import GregTech.TopazBackend.tool.ToolClass;

import java.sql.Timestamp;

public class Deldoc {
    int did;
    long time;
    int executor;
    public String getStrtime(){
        return ToolClass.stamp2time(new Timestamp(this.time));
    }
    public void setDid(int did) {
        this.did = did;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public void setExecutor(int executor) {
        this.executor = executor;
    }

    public int getDid() {
        return did;
    }

    public long getTime() {
        return time;
    }

    public int getExecutor() {
        return executor;
    }
}
