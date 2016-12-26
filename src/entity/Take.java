package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Timestamp;

/**
 * Created by zhenghb on 2016/8/21.
 */
@Entity
public class Take {
    private int takeId;
    private Integer userId;
    private String username;
    private Integer eventId;
    private String studentId;
    private String email;
    private String phone;
    private Timestamp time;
    private Integer state;
    private String grade;
    private Integer score;

    @Id
    @Column(name = "takeID")
    public int getTakeId() {
        return takeId;
    }

    public void setTakeId(int takeId) {
        this.takeId = takeId;
    }

    @Basic
    @Column(name = "userID")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "eventID")
    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    @Basic
    @Column(name = "studentID")
    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
    @Column(name = "state")
    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    @Basic
    @Column(name = "grade")
    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    @Basic
    @Column(name = "score")
    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Take take = (Take) o;

        if (takeId != take.takeId) return false;
        if (userId != null ? !userId.equals(take.userId) : take.userId != null) return false;
        if (username != null ? !username.equals(take.username) : take.username != null) return false;
        if (eventId != null ? !eventId.equals(take.eventId) : take.eventId != null) return false;
        if (studentId != null ? !studentId.equals(take.studentId) : take.studentId != null) return false;
        if (email != null ? !email.equals(take.email) : take.email != null) return false;
        if (phone != null ? !phone.equals(take.phone) : take.phone != null) return false;
        if (time != null ? !time.equals(take.time) : take.time != null) return false;
        if (state != null ? !state.equals(take.state) : take.state != null) return false;
        if (grade != null ? !grade.equals(take.grade) : take.grade != null) return false;
        if (score != null ? !score.equals(take.score) : take.score != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = takeId;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (eventId != null ? eventId.hashCode() : 0);
        result = 31 * result + (studentId != null ? studentId.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (state != null ? state.hashCode() : 0);
        result = 31 * result + (grade != null ? grade.hashCode() : 0);
        result = 31 * result + (score != null ? score.hashCode() : 0);
        return result;
    }
}
