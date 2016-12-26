package dao;

import com.opensymphony.xwork2.ActionContext;
import entity.Event;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenghb on 2016/6/29.
 */
public class EventDAO {
    public List<Event> latest() throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where state > 0 order by event.eventId desc");
            query.setFirstResult(0);
            query.setMaxResults(4);
            List<Event> events = query.list();
            tx.commit();
            return events;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Event> manage() throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where state > 0 order by event.eventId desc");
            List<Event> events = query.list();
            tx.commit();
            return events;
        }catch(Exception ex) {
            return null;
        }
    }


    public List<Event> browse(int page) throws Exception {
        int pagelimit = 5;
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where state > 0 order by event.eventId desc");
            query.setFirstResult(pagelimit * (page - 1));
            query.setMaxResults(pagelimit);
            List<Event> events = query.list();
            tx.commit();
            return events;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Event> browseByCategory(String category,int page) throws Exception {
        int pagelimit = 5;
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where state > 0 and category = :category order by event.eventId desc");
            query.setString("category",category);
            query.setFirstResult(pagelimit * (page - 1));
            query.setMaxResults(pagelimit);
            List<Event> events = query.list();

            /*ActionContext context = ActionContext.getContext();
            Map ses = context.getSession();
            int userID = (int)ses.get("userID");
            FavorDAO favorDAO = new FavorDAO();
            favorDAO.update(userID,category,userID);*/
            tx.commit();
            return events;
        }catch(Exception ex) {
            return null;
        }
    }

    public HashSet recommend(String category) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where state > 0 and category = :category order by event.eventId desc");
            query.setString("category",category);
            query.setFirstResult(0);
            query.setMaxResults(2);
            List<Event> events = query.list();
            tx.commit();
            HashSet result =  new HashSet();
            for (Event event:events) result.add(event.getEventId());
            return result;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Event> search(String key,int page) throws Exception {
        int pagelimit = 5;
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event where eventname like :key order by eventId desc");
            query.setString("key","%"+key+"%");
            query.setFirstResult(pagelimit * (page - 1));
            query.setMaxResults(pagelimit);
            List<Event> results = query.list();
            tx.commit();
            return results;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<Event> adminbrowse() throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where event.state = 0 order by event.eventId desc");
            List<Event> events = query.list();
            tx.commit();
            return events;
        }catch(Exception ex) {
            return null;
        }
    }

    public String pass(int eventID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Event event set event.state = 1 where event.eventId = :eventId");
            query.setInteger("eventId", eventID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String fail(int eventID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Event event set event.state = -1 where event.eventId = :eventId");
            query.setInteger("eventId", eventID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public String cancel(int eventID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            //取消活动 设置状态为-2
            Query query = session.createQuery("update Event event set event.state = -2 where event.eventId = :eventId");
            query.setInteger("eventId", eventID);
            query.executeUpdate();
            tx.commit();
            return "success";
        } catch(Exception ex) {
            return "error";
        }
    }

    public Event view(int eventID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Event set view = view + 1 where eventId = :eventId");
            query.setInteger("eventId",eventID);
            query.executeUpdate();
            Event event = (Event)session.get(Event.class,eventID);
            tx.commit();
            return event;
        }catch(Exception ex) {
            return null;
        }
    }

    public Event get(int eventID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Event event = (Event)session.get(Event.class,eventID);
            tx.commit();
            return event;
        }catch(Exception ex) {
            return null;
        }
    }

    public String save(Event event) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            session.save(event);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public List<Event> viewlaunch(int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Event event where event.host = :host order by event.eventId desc ");
            query.setInteger("host",userID);
            List<Event> events = query.list();
            tx.commit();
            return events;
        } catch(Exception ex) {
            return null;
        }
    }

    public String stop(int eventID) throws Exception{
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("update Event set state = 3 where eventId = :eventId");
            query.setInteger("eventId",eventID);
            query.executeUpdate();
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }
}
