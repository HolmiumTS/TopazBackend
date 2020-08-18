package GregTech.TopazBackend.metadata;

public class Comment {
    int cid;
    int did;
    String content;
    int uid;
    long time;
    public int getCid() {
        return cid;
    }

    public void setTime(long     time) {
        this.time = time;
    }

    public long getTime() {
        return time;
    }

    public int getDid() {
        return did;
    }

    public String getContent() {
        return content;
    }

    public int getUid() {
        return uid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }
}
