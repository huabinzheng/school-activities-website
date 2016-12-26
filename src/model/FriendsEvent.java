package model;

/**
 * Created by zhenghb on 2016/7/18.
 */
public class FriendsEvent {
    private int userId;
    private String username;
    private String icon;
    private int eventId;
    private String eventname;
    private String poster;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public int getUserId() {
        return userId;
    }

    public String getUsername() {
        return username;
    }

    public String getIcon() {
        return icon;
    }

    public int getEventId() {
        return eventId;
    }

    public String getEventname() {
        return eventname;
    }

    public String getPoster() {
        return poster;
    }
}
