package model;

/**
 * Created by zhenghb on 2016/7/4.
 */
public class Apply {
    private int mId;
    private int userID;
    private String username;
    private String realname;
    private String icon;

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return icon;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getmId() {
        return mId;
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }
}
