package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by zhenghb on 2016/8/19.
 */
@Entity
public class Event {
    private int eventId;
    private String eventname;
    private int host;
    private Timestamp eventstart;
    private Timestamp eventend;
    private Timestamp time;
    private String location;
    private int limitnum;
    private int joinnum;
    private String content;
    private int state;
    private int rule;
    private int view;
    private String category;
    private String poster;

    @Id
    @Column(name = "eventID")
    public int getEventId() {
        return eventId;
    }

    public void setEventId(int eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "eventname")
    public String getEventname() {
        return eventname;
    }

    public void setEventname(String eventname) {
        this.eventname = eventname;
    }

    @Basic
    @Column(name = "host")
    public int getHost() {
        return host;
    }

    public void setHost(int host) {
        this.host = host;
    }

    @Basic
    @Column(name = "eventstart")
    public Timestamp getEventstart() {
        return eventstart;
    }

    public void setEventstart(Timestamp eventstart) {
        this.eventstart = eventstart;
    }

    @Basic
    @Column(name = "eventend")
    public Timestamp getEventend() {
        return eventend;
    }

    public void setEventend(Timestamp eventend) {
        this.eventend = eventend;
    }

    @Basic
    @Column(name = "time")
    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "limitnum")
    public int getLimitnum() {
        return limitnum;
    }

    public void setLimitnum(int limitnum) {
        this.limitnum = limitnum;
    }

    @Basic
    @Column(name = "joinnum")
    public int getJoinnum() {
        return joinnum;
    }

    public void setJoinnum(int joinnum) {
        this.joinnum = joinnum;
    }

    @Basic
    @Column(name = "content")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Basic
    @Column(name = "state")
    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    @Basic
    @Column(name = "rule")
    public int getRule() {
        return rule;
    }

    public void setRule(int rule) {
        this.rule = rule;
    }

    @Basic
    @Column(name = "view")
    public int getView() {
        return view;
    }

    public void setView(int view) {
        this.view = view;
    }

    @Basic
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Basic
    @Column(name = "poster")
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Event event = (Event) o;

        if (eventId != event.eventId) return false;
        if (host != event.host) return false;
        if (limitnum != event.limitnum) return false;
        if (joinnum != event.joinnum) return false;
        if (state != event.state) return false;
        if (rule != event.rule) return false;
        if (view != event.view) return false;
        if (eventname != null ? !eventname.equals(event.eventname) : event.eventname != null) return false;
        if (eventstart != null ? !eventstart.equals(event.eventstart) : event.eventstart != null) return false;
        if (eventend != null ? !eventend.equals(event.eventend) : event.eventend != null) return false;
        if (time != null ? !time.equals(event.time) : event.time != null) return false;
        if (location != null ? !location.equals(event.location) : event.location != null) return false;
        if (content != null ? !content.equals(event.content) : event.content != null) return false;
        if (category != null ? !category.equals(event.category) : event.category != null) return false;
        if (poster != null ? !poster.equals(event.poster) : event.poster != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = eventId;
        result = 31 * result + (eventname != null ? eventname.hashCode() : 0);
        result = 31 * result + host;
        result = 31 * result + (eventstart != null ? eventstart.hashCode() : 0);
        result = 31 * result + (eventend != null ? eventend.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + limitnum;
        result = 31 * result + joinnum;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + state;
        result = 31 * result + rule;
        result = 31 * result + view;
        result = 31 * result + (category != null ? category.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        return result;
    }
}
