package GregTech.TopazBackend.metadata;

import GregTech.TopazBackend.tool.ToolClass;

import java.sql.Timestamp;

public class Doc {
    int did;
    String name;
    int owner;
    int team;
    boolean view;
    int edit;
    long create;
    long update;
    int count;
    String content;
    boolean isDel;
    boolean isLocked;

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public int getDid() {
        return did;
    }

    public void setDid(int did) {
        this.did = did;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getOwner() {
        return owner;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public int getTeam() {
        return team;
    }

    public void setTeam(int team) {
        this.team = team;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public int getEdit() {
        return edit;
    }

    public String getStrUpdate(){
        return ToolClass.stamp2time(new Timestamp(this.update));
    }
    public String getStrCreate(){
        return ToolClass.stamp2time(new Timestamp(this.create));
    }

    public void setEdit(int edit) {
        this.edit = edit;
    }

    public long getCreate() {
        return create;
    }

    public void setCreate(long create) {
        this.create = create;
    }

    public long getUpdate() {
        return update;
    }

    public void setUpdate(long update) {
        this.update = update;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }



    @Override
    public String toString() {
        return "Doc{" +
                "did=" + did +
                ", name='" + name + '\'' +
                ", owner=" + owner +
                ", team=" + team +
                ", view=" + view +
                ", edit=" + edit +
                ", create=" + create +
                ", update=" + update +
                ", count=" + count +
                ", content='" + content + '\'' +
                ", isDel=" + isDel +
                '}';
    }
}
