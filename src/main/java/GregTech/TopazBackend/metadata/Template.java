package GregTech.TopazBackend.metadata;

public class Template {
    int temid;
    int owner;
    String content;
    String name;

    public int getTemid() {
        return temid;
    }

    public int getOwner() {
        return owner;
    }

    public String getContent() {
        return content;
    }

    public String getName() {
        return name;
    }

    public void setTemid(int temid) {
        this.temid = temid;
    }

    public void setOwner(int owner) {
        this.owner = owner;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setName(String name) {
        this.name = name;
    }
}
