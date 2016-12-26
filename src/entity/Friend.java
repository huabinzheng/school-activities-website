package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhenghb on 2016/8/19.
 */
@Entity
public class Friend {
    private int fId;
    private Integer friender;
    private Integer friendee;

    @Id
    @Column(name = "fID")
    public int getfId() {
        return fId;
    }

    public void setfId(int fId) {
        this.fId = fId;
    }

    @Basic
    @Column(name = "friender")
    public Integer getFriender() {
        return friender;
    }

    public void setFriender(Integer friender) {
        this.friender = friender;
    }

    @Basic
    @Column(name = "friendee")
    public Integer getFriendee() {
        return friendee;
    }

    public void setFriendee(Integer friendee) {
        this.friendee = friendee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Friend friend = (Friend) o;

        if (fId != friend.fId) return false;
        if (friender != null ? !friender.equals(friend.friender) : friend.friender != null) return false;
        if (friendee != null ? !friendee.equals(friend.friendee) : friend.friendee != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = fId;
        result = 31 * result + (friender != null ? friender.hashCode() : 0);
        result = 31 * result + (friendee != null ? friendee.hashCode() : 0);
        return result;
    }
}
