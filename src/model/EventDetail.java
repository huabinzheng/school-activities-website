package model;

/**
 * Created by zhenghb on 2016/7/20.
 */
public class EventDetail {
    public int getMessageID() {
        return messageID;
    }

    public Integer getEventId() {
        return eventId;
    }

    public String getEventname() {
        return eventname;
    }

    public Integer getState() {
        return state;
    }

    public String getContent() {
        return content;
    }

    private int messageID;
    private Integer eventId;
    private String eventname;
    private Integer state;
    private String content;

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
