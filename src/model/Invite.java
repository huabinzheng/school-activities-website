package model;

/**
 * Created by zhenghb on 2016/7/1.
 */
public class Invite {
	//消息列表中的新活动邀请类
    private int messageID;
    private int senderId;
    private String sendername;
    private Integer eventId;
    private String eventname;
    private Integer state;
    private String content;

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public void setSendername(String sendername) {
        this.sendername = sendername;
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

    public void setMessageID(int messageID) {
        this.messageID = messageID;
    }

    public int getMessageID() {
        return messageID;
    }
    public int getSenderId() {
        return senderId;
    }

    public String getSendername() {
        return sendername;
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
}
