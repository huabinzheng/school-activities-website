package dao;

import entity.Event;
import entity.Take;
import entity.User;
import model.FriendsEvent;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghb on 2016/6/30.
 */
public class TakeDAO {
    public List<Take> participant(int eventID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            //状态为0的已取消报名 其他状态的显示在已报名用户清单中
            Query query = session.createQuery("from Take take where take.eventId = :eventID and take.state > 0 order by take.state ,take.time");
            query.setInteger("eventID", eventID);
            List<Take> takes = query.list();
            tx.commit();
            return takes;
        } catch (Exception ex) {
            return null;
        }
    }

    public int check(int eventID, int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery sqlquery = session.createSQLQuery("select * from take where eventID = :eventID and userID = :userID");
            sqlquery.setInteger("eventID", eventID);
            sqlquery.setInteger("userID", userID);
            sqlquery.addEntity(Take.class);
            ArrayList<Take> result = (ArrayList<Take>) sqlquery.list();
            tx.commit();
            if (result.size() != 0) {
                return 2; //用户已经报名过该活动
            } else {
                return 1; // 用户未报名过该活动
            }
        } catch (Exception ex) {
            return 0;
        }
    }

    public String save(Take take) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(take);
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String canceljoin(int eventID, int userID) throws Exception {
        //设置take中state为0 取消报名
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 0 where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String mark(int eventID, int userID, int score) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.score = :score where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.setInteger("score",score);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String accept(int eventID, int userID) throws Exception {
        //设置take中state为3 报名通过
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 3 where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String acceptall(int eventID) throws Exception {
        //设置take中state为3 报名通过
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 3 where take.eventId = :eventId");
            query.setInteger("eventId", eventID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String acceptgrade(int eventID,String grade) throws Exception {
        //设置take中state为3 报名通过
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 3 where take.eventId = :eventId and take.grade = :grade");
            query.setInteger("eventId", eventID);
            query.setString("grade",grade);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String refuse(int eventID, int userID) throws Exception {
        //设置take中state为2 报名未通过
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 2 where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String cancel(int eventID, int userID) throws Exception {
        //设置take中state为1 恢复为待确认
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 1 where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String checkin(int eventID, int userID) throws Exception {
        //设置take中state为4 已签到
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Take take set take.state = 4 where take.eventId = :eventId and take.userId = :userId");
            query.setInteger("eventId", eventID);
            query.setInteger("userId",userID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public List<Take> viewjoin(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Take take where take.userId = :userId order by take.time desc ");
            query.setInteger("userId", userID);
            List<Take> takes = query.list();
            tx.commit();
            return takes;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<FriendsEvent> friendsjoin(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery sqlquery = session.createSQLQuery("select * from take where userID in (select friendee from friend where friender = :userID) order by time desc");
            sqlquery.setInteger("userID",userID);
            sqlquery.setFirstResult(0);
            sqlquery.setMaxResults(4);
            sqlquery.addEntity(Take.class);
            List<Take> takes = (List<Take>) sqlquery.list();
            List<FriendsEvent> friendsEvents = new ArrayList<>();
            for (Take take:takes){
                FriendsEvent friendsEvent = new FriendsEvent();
                User user = (User)session.get(User.class,take.getUserId());
                friendsEvent.setUserId(user.getUserId());
                friendsEvent.setUsername(user.getRealname());
                friendsEvent.setIcon(user.getIcon());
                Event event = (Event)session.get(Event.class,take.getEventId());
                friendsEvent.setEventId(event.getEventId());
                friendsEvent.setEventname(event.getEventname());
                friendsEvent.setPoster(event.getPoster());
                friendsEvents.add(friendsEvent);
            }
            tx.commit();
            return friendsEvents;
        } catch (Exception ex) {
            return null;
        }
    }
}
