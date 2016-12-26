package dao;

import entity.Friend;
import entity.User;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhenghb on 2016/6/29.
 */
public class UserDAO {
    public User get(int userID) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            tx.commit();
            return user;
        }catch(Exception ex) {
            return null;
        }
    }

    public int find(String username, String password) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            SQLQuery query = session.createSQLQuery("select * from user where username = :username");
            query.setString("username", username);
            query.addEntity(User.class);
            ArrayList<User> result = (ArrayList<User>) query.list();
            tx.commit();
            if (result.size() == 0) {
                return 1;//不存在该用户名
            } else if (!result.get(0).getPassword().equals(password)) {
                return 2;//用户名的密码错误
            } else return 3;//存在一个
        } catch (Exception ex) {
            return 0;
        }
    }

    public User login(String username) throws Exception {
        Session session = HibernateUtil.getSession();
        Transaction tx = session.beginTransaction();
        SQLQuery query = session.createSQLQuery("select * from user where username = :username");
        query.setString("username", username);
        query.addEntity(User.class);
        ArrayList<User> result = (ArrayList<User>) query.list();
        tx.commit();
        return result.get(0);
    }

    public String add(User user) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            int id = (int)session.save(user);
            System.out.println(id);
            tx.commit();
            return "success";
        } catch (Exception ex) {
            return "error";
        }
    }

    public List<User> getFriend(int userID) throws Exception {
        try {
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from Friend where friender = :userID");
            query.setInteger("userID",userID);
            List<Friend> friendrelations = query.list();
            List<User> friends = new ArrayList<>();
            for (Friend friend : friendrelations){
                int friendID = friend.getFriendee();
                User user = (User)session.get(User.class,friendID);
                friends.add(user);
            }
            tx.commit();
            return friends;
        } catch (Exception ex) {
            return null;
        }
    }

    public List<User> search(String key) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from User where realname like :key or username like :key");
            query.setString("key","%"+key+"%");
            List<User> results = query.list();
            tx.commit();
            return results;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<User> searchall() throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from User user");
            List<User> results = query.list();
            tx.commit();
            return results;
        }catch(Exception ex) {
            return null;
        }
    }

    public List<User> adminsearch(String key) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            Query query = session.createQuery("from User where realname like :key or username like :key and authority >= 4");
            query.setString("key","%"+key+"%");
            List<User> results = query.list();
            tx.commit();
            return results;
        }catch(Exception ex) {
            return null;
        }
    }

    public String setIcon(int userID, String filename) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setIcon("images/user/"+filename);
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String password(int userID, String password) throws Exception {
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setPassword(password);
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String banlaunch(int userID) throws Exception {
        //禁止用户发布活动 将权限从1设置为0
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() - 8);
            //用户权限转换为二进制 表示发布活动权限的位是高位第2位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String restorelaunch(int userID) throws Exception {
        //恢复用户发布活动 将权限从0设置为1
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() + 8);
            //用户权限转换为二进制 表示发布活动权限的位是高位第2位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String banjoin(int userID) throws Exception {
        //禁止用户报名活动 将权限从1设置为0
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() - 4);
            //用户权限转换为二进制 表示发布活动权限的位是高位第3位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String restorejoin(int userID) throws Exception {
        //恢复用户报名活动 将权限从0设置为1
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() + 4);
            //用户权限转换为二进制 表示发布活动权限的位是高位第3位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String baninvite(int userID) throws Exception {
        //禁止用户邀请好友 将权限从1设置为0
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() - 2);
            //用户权限转换为二进制 表示发布活动权限的位是高位第4位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String restoreinvite(int userID) throws Exception {
        //恢复用户邀请好友 将权限从0设置为1
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() + 2);
            //用户权限转换为二进制 表示发布活动权限的位是高位第4位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String bancomment(int userID) throws Exception {
        //禁止用户发布活动 将权限从1设置为0
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() - 1);
            //用户权限转换为二进制 表示发布活动权限的位是最低位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }

    public String restorecomment(int userID) throws Exception {
        //恢复用户发布活动 将权限从0设置为1
        try{
            Session session = HibernateUtil.getSession();
            Transaction tx = session.beginTransaction();
            User user = (User)session.get(User.class,userID);
            user.setAuthority(user.getAuthority() + 1);
            //用户权限转换为二进制 表示发布活动权限的位是最低位
            session.save(user);
            tx.commit();
            return "success";
        }catch(Exception ex) {
            return "error";
        }
    }
}
