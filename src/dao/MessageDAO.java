package dao;

import entity.Message;
import entity.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;

/**
 * Created by zhenghb on 2016/7/1.
 */
public class MessageDAO {
    public List<Message> getInvite(int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Message message where type = 2 and receiver = :userID order by message.mId desc");
            query.setInteger("userID",userID);
            List<Message> messages = query.list();
            tx.commit();
            return messages;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Message> getApply(int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Message message where type = 1 and receiver = :userID order by message.mId desc");
            query.setInteger("userID",userID);
            List<Message> messages = query.list();
            tx.commit();
            return messages;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Message> getEvent(int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Message message where type = 3 or type = 4 and receiver = :userID order by message.mId desc");
            query.setInteger("userID",userID);
            List<Message> messages = query.list();
            tx.commit();
            return messages;
        }catch(Exception ex) {
            return null;
        }
    }

    public String invite(int eventID, int userID, int friendID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Message message = new Message();
            message.setType(2);//type 2 邀请好友参加活动
            message.setSender(userID);
            message.setReceiver(friendID);
            message.setEventId(eventID);
            message.setState(0);//state 0 默认未读消息
            session.save(message);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String cancel(int eventID, int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Message message = new Message();
            message.setType(3);//type 3 活动取消通知
            message.setReceiver(userID);
            message.setEventId(eventID);
            message.setState(0);//state 0 默认未读消息
            session.save(message);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String admincancel(int eventID, int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Message message = new Message();
            message.setType(4);//type 4 活动管理员删除通知
            message.setReceiver(userID);
            message.setEventId(eventID);
            message.setState(0);//state 0 默认未读消息
            session.save(message);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String apply(int sender, int receiver) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Message message = new Message();
            message.setType(1);//type 1 申请好友
            message.setSender(sender);
            message.setReceiver(receiver);
            message.setState(0);//state 0 默认未读消息
            session.save(message);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String delete(int mID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Message message = (Message)session.load(Message.class,mID);
            session.delete(message);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

}
