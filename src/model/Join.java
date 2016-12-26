package model;

import java.sql.Timestamp;

/**
 * Created by zhenghb on 2016/6/30.
 */
public class Join {
    //event相关
    private int eventId;
    private String eventname;
    private Timestamp eventstart;
    private Timestamp eventend;
    private String location;
    private String category;
    private int eventstate;

    public void setEventstate(int eventstate) {
        this.eventstate = eventstate;
    }

    public int getEventstate() {
        return eventstate;
    }

    //take相关
    private Timestamp time;//报名的时间
    private int state;//报名的状态
    private int score;

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public void setEventstart(Timestamp eventstart) {
        this.eventstart = eventstart;
    }

    public void setEventend(Timestamp eventend) {
        this.eventend = eventend;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventname() {
        return eventname;
    }

    public Timestamp getTime() {
        return time;
    }

    public Timestamp getEventstart() {
        return eventstart;
    }

    public Timestamp getEventend() {
        return eventend;
    }

    public String getLocation() {
        return location;
    }

    public String getCategory() {
        return category;
    }

    public int getState() {
        return state;
    }
}
