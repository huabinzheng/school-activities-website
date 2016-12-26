package dao;

import entity.Event;
import entity.Favor;
import entity.Take;
import entity.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.*;

/**
 * Created by zhenghb on 2016/7/13.
 */
class Score {
    private int userID;
    private double sum;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }

    public int getUserID() {
        return userID;
    }

    public double getSum() {
        return sum;
    }
}

class ComparatorScore implements Comparator {
    public int compare(Object arg0, Object arg1) {
        Score s1 = (Score) arg0;
        Score s2 = (Score) arg1;
        int flag;
        if (s1.getSum() > s2.getSum()) {
            flag = -1;
        } else if (s1.getSum() == s2.getSum()) {
            flag = 0;
        } else flag = 1;
        return flag;
    }
}

public class FavorDAO {
    public void add(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Favor favor = new Favor();
            favor.setUserId(userID);
            favor.setCat1(0);
            favor.setCat2(0);
            favor.setCat3(0);
            favor.setCat4(0);
            favor.setCat5(0);
            session.save(favor);
            tx.commit();
        } catch (Exception ex) {
        }
    }
    public void update(int userID, String category, int score) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query;
            if (category.equals("讲座")) {
                query = session.createQuery("update Favor set cat1 = cat1 + :score where userId = :userId");
                query.setInteger("userId", userID);
                query.setInteger("score", score);
                query.executeUpdate();
            }
            if (category.equals("社团")) {
                query = session.createQuery("update Favor set cat2 = cat2 + :score where userId = :userId");
                query.setInteger("userId", userID);
                query.setInteger("score", score);
                query.executeUpdate();
            }
            if (category.equals("组织")) {
                query = session.createQuery("update Favor set cat3 = cat3 + :score where userId = :userId");
                query.setInteger("userId", userID);
                query.setInteger("score", score);
                query.executeUpdate();
            }
            if (category.equals("企业")) {
                query = session.createQuery("update Favor set cat4 = cat4 + :score where userId = :userId");
                query.setInteger("userId", userID);
                query.setInteger("score", score);
                query.executeUpdate();
            }
            if (category.equals("其他")) {
                query = session.createQuery("update Favor set cat5 = cat5 + :score where userId = :userId");
                query.setInteger("userId", userID);
                query.setInteger("score", score);
                query.executeUpdate();
            }
            tx.commit();
        } catch (Exception ex) {
        }
    }

    public Favor recommend(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from favor where userId = :userID");
            query.setInteger("userID", userID);
            query.addEntity(Favor.class);
            ArrayList<Favor> results = (ArrayList<Favor>) query.list();
            tx.commit();
            return results.get(0);
        } catch (Exception ex) {
            return null;
        }
    }

    public String getCategory(Favor favor) throws Exception {
        int maxScore = favor.getCat1();
        String cat = "讲座";
        if (favor.getCat2() > maxScore) {
            maxScore = favor.getCat1();
            cat = "社团";
        }
        if (favor.getCat3() > maxScore) {
            maxScore = favor.getCat1();
            cat = "组织";
        }
        if (favor.getCat4() > maxScore) {
            maxScore = favor.getCat1();
            cat = "企业";
        }
        if (favor.getCat5() > maxScore) {
            //maxScore = favor.getCat1();
            cat = "其他";
        }
        return cat;
    }

    public ArrayList<Take> cold(User user) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery sqlquery = session.createSQLQuery("select * from take where userID in (select userID from user where grade = :grade and gender = :gender) order by time desc");
            sqlquery.setString("grade", user.getGrade());
            sqlquery.setInteger("gender", user.getGender());
            sqlquery.setFirstResult(0);
            sqlquery.setMaxResults(4);
            sqlquery.addEntity(Take.class);
            ArrayList<Take> takes = (ArrayList<Take>) sqlquery.list();
            tx.commit();
            return takes;
        } catch (Exception ex) {
            return null;
        }
    }


    public List<Integer> similar(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from favor where userId = :userID");
            query.setInteger("userID", userID);
            query.addEntity(Favor.class);
            ArrayList<Favor> results = (ArrayList<Favor>) query.list();
            Favor target = results.get(0);

            Query query2 = session.createQuery("from Favor");
            List<Favor> favors = query2.list();
            ArrayList<Score> scores = new ArrayList<>();
            for (Favor compare : favors)
                if (compare.getUserId() != userID) {
                    int cnt = 0;
                    double sum = 0;
                    //计算欧几里德距离来计算相似度
                    if ((target.getCat1() != 0) && (compare.getCat1() != 0)) {
                        cnt++; sum += Math.pow((target.getCat1() - compare.getCat1()), 2);
                    }
                    if ((target.getCat2() != 0) && (compare.getCat2() != 0)) {
                        cnt++; sum += Math.pow((target.getCat2() - compare.getCat2()), 2);
                    }
                    if ((target.getCat3() != 0) && (compare.getCat3() != 0)) {
                        cnt++; sum += Math.pow((target.getCat3() - compare.getCat3()), 2);
                    }
                    if ((target.getCat4() != 0) && (compare.getCat4() != 0)) {
                        cnt++; sum += Math.pow((target.getCat4() - compare.getCat4()), 2);
                    }
                    if ((target.getCat5() != 0) && (compare.getCat5() != 0)) {
                        cnt++; sum += Math.pow((target.getCat5() - compare.getCat5()), 2);
                    }
                    if (cnt > 0) {
                        sum = Math.sqrt(sum / (double) cnt);
                        sum = 1 / (1 + sum);
                        Score score = new Score();
                        score.setUserID(compare.getUserId());
                        score.setSum(sum);
                        scores.add(score);
                    }
                }
            tx.commit();

            //按相似度排序
            ComparatorScore comparator = new ComparatorScore();
            Collections.sort(scores, comparator);

            List<Integer> userIDs = new ArrayList<>();
            for (Score score : scores) {
                userIDs.add(score.getUserID());
            }
            return userIDs;
        } catch (Exception ex) {
            return null;
        }
    }
}
