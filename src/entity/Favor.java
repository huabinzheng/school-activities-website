package entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by zhenghb on 2016/8/19.
 */
@Entity
public class Favor {
    private int favorId;
    private int userId;
    private Integer cat1;
    private Integer cat2;
    private Integer cat3;
    private Integer cat4;
    private Integer cat5;

    @Id
    @Column(name = "favorID")
    public int getFavorId() {
        return favorId;
    }

    public void setFavorId(int favorId) {
        this.favorId = favorId;
    }

    @Basic
    @Column(name = "userID")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "cat1")
    public Integer getCat1() {
        return cat1;
    }

    public void setCat1(Integer cat1) {
        this.cat1 = cat1;
    }

    @Basic
    @Column(name = "cat2")
    public Integer getCat2() {
        return cat2;
    }

    public void setCat2(Integer cat2) {
        this.cat2 = cat2;
    }

    @Basic
    @Column(name = "cat3")
    public Integer getCat3() {
        return cat3;
    }

    public void setCat3(Integer cat3) {
        this.cat3 = cat3;
    }

    @Basic
    @Column(name = "cat4")
    public Integer getCat4() {
        return cat4;
    }

    public void setCat4(Integer cat4) {
        this.cat4 = cat4;
    }

    @Basic
    @Column(name = "cat5")
    public Integer getCat5() {
        return cat5;
    }

    public void setCat5(Integer cat5) {
        this.cat5 = cat5;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Favor favor = (Favor) o;

        if (favorId != favor.favorId) return false;
        if (userId != favor.userId) return false;
        if (cat1 != null ? !cat1.equals(favor.cat1) : favor.cat1 != null) return false;
        if (cat2 != null ? !cat2.equals(favor.cat2) : favor.cat2 != null) return false;
        if (cat3 != null ? !cat3.equals(favor.cat3) : favor.cat3 != null) return false;
        if (cat4 != null ? !cat4.equals(favor.cat4) : favor.cat4 != null) return false;
        if (cat5 != null ? !cat5.equals(favor.cat5) : favor.cat5 != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = favorId;
        result = 31 * result + userId;
        result = 31 * result + (cat1 != null ? cat1.hashCode() : 0);
        result = 31 * result + (cat2 != null ? cat2.hashCode() : 0);
        result = 31 * result + (cat3 != null ? cat3.hashCode() : 0);
        result = 31 * result + (cat4 != null ? cat4.hashCode() : 0);
        result = 31 * result + (cat5 != null ? cat5.hashCode() : 0);
        return result;
    }
}
