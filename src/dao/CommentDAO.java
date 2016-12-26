package dao;

import entity.Comment;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by zhenghb on 2016/7/1.
 */
public class CommentDAO {
    public String add(Comment comment) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(comment);
            tx.commit();
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }

    public List<Comment> getComments(int eventID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Comment comment where eventId = :eventID");
            query.setInteger("eventID",eventID);
            List<Comment> comments = query.list();
            tx.commit();
            return comments;
        } catch (Exception ex) {
            return null;
        }
    }

    public String answer(int cID, String answer) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Comment comment = (Comment) session.get(Comment.class,cID);
            comment.setAnswer(answer);
            session.save(comment);
            tx.commit();
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }

    public String delete(int cID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Comment comment = (Comment) session.get(Comment.class,cID);
            session.delete(comment);
            tx.commit();
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }
}
