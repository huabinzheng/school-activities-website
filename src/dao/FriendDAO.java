package dao;

import entity.Friend;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;

/**
 * Created by zhenghb on 2016/7/4.
 */
public class FriendDAO {
    public String add(int friender, int friendee) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Friend friend = new Friend();
            friend.setFriender(friender);
            friend.setFriendee(friendee);
            session.save(friend);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public int find(int friender, int friendee) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from friend where friender = :id1 and friendee = :id2");
            query.setInteger("id1", friender);
            query.setInteger("id2", friendee);
            query.addEntity(Friend.class);
            ArrayList<Friend> result = (ArrayList<Friend>) query.list();
            tx.commit();
            if (result.size() == 0) {
                return 1;//不存在该好友关系
            } else return 2;//已经成为好友
        }catch(Exception ex) {
            return 0;
        }
    }

    public String delete(int userID, int friendID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from friend where friender = :id1 and friendee = :id2");
            query.setInteger("id1", userID);
            query.setInteger("id2", friendID);
            query.addEntity(Friend.class);
            ArrayList<Friend> result1 = (ArrayList<Friend>) query.list();
            query = session.createSQLQuery("select * from friend where friender = :id1 and friendee = :id2");
            query.setInteger("id1", friendID);
            query.setInteger("id2", userID);
            query.addEntity(Friend.class);
            ArrayList<Friend> result2 = (ArrayList<Friend>) query.list();

            Friend f1 = (Friend)session.load(Friend.class,result1.get(0).getfId());
            Friend f2 = (Friend)session.load(Friend.class,result2.get(0).getfId());
            session.delete(f1);
            session.delete(f2);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }
}
