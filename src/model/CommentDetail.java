package model;

import java.sql.Timestamp;

/**
 * Created by zhenghb on 2016/7/6.
 */
public class CommentDetail {
    private int cId;
    private int eventId;
    private int userId;
    private String username;
    private String realname;
    private String icon;
    private String content;
    private String answer;
    private Timestamp time;

    public void setcId(int cId) {
        this.cId = cId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public int getcId() {
        return cId;
    }

    public int getEventId() {
        return eventId;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getRealname() {
        return realname;
    }

    public String getIcon() {
        return icon;
    }

    public String getContent() {
        return content;
    }

    public String getAnswer() {
        return answer;
    }

    public Timestamp getTime() {
        return time;
    }
}
