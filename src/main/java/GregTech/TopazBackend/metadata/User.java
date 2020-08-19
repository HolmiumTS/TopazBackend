package GregTech.TopazBackend.metadata;

import GregTech.TopazBackend.config.Constant;

import java.util.Arrays;

public class User {
    int id;
    String name;
    String password;
    String email;
    int[] latestDoc;
    String tel;
    String avatar;


    public User() {
        id = -1;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int[] getLatestDoc() {
        //todo
        return latestDoc;
    }

    public void setLatestDoc(int[] latestDoc) {
        this.latestDoc = latestDoc;
    }

    public void setLatestDoc(String latestDoc) {
        //todo 这里可能后面要改
       if (latestDoc==null){
           this.latestDoc=new int[0];
           return;
       }
        if ("".equals(latestDoc)) {
            this.latestDoc = new int[0];
            return;
        }
        this.latestDoc = Arrays.stream(latestDoc.split(Constant.SEPARATOR))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    /**
     * @param latestDocId id of new doc
     * @return true if latestDoc is full (means one of the latest docs is missed), otherwise false
     */
    public boolean addLatestDoc(int latestDocId) {
        if (latestDoc.length == Constant.MAX_LATEST_DOC) {
            int len = latestDoc.length;
            int[] tmp = new int[len];
            System.arraycopy(latestDoc, 0, tmp, 1, len - 1);
            tmp[0] = latestDocId;
            latestDoc = tmp;
            return true;
        } else if (latestDoc.length == 0) {
            latestDoc = new int[1];
            latestDoc[0] = latestDocId;
            return false;
        } else {
            int len = latestDoc.length;
            int[] tmp = new int[len + 1];
            System.arraycopy(latestDoc, 0, tmp, 1, len);
            tmp[0] = latestDocId;
            latestDoc = tmp;
            return false;
        }
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getStrRecent(){
        return I2S(this.latestDoc);
    }


    private  String I2S(int[] docs) {
        String[] bb = new String[docs.length];
        for (int i = 0; i < docs.length; i++) {
            bb[i] = Integer.toString(docs[i]);
        }
        String s = "";
        s.join(";", docs.toString());
        return s;
    }
}
