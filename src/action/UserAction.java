package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import entity.User;
import org.apache.struts2.ServletActionContext;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenghb on 2016/6/27.
 */
public class UserAction extends ActionSupport {
    public String search() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] keys = (String[]) params.get("key");
        String key = keys[0];

        UserDAO userdao = new UserDAO();
        List<User> results = userdao.search(key);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("key", key);
        request.setAttribute("users", results);
        return "success";
    }

    public String searchall() throws Exception {
        UserDAO userdao = new UserDAO();
        List<User> results = userdao.searchall();
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("users", results);
        return "success";
    }

    public String adminsearch() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] keys = (String[]) params.get("key");
        String key = keys[0];

        UserDAO userdao = new UserDAO();
        List<User> results = userdao.adminsearch(key);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("users", results);
        return "success";
    }


    public String viewfriends() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int userID = (int)session.get("userID");

        UserDAO userdao = new UserDAO();
        List<User> friends = userdao.getFriend(userID);
        if (friends != null){
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("friends", friends);
            return "success";
        } else return "error";
    }

    public String profile() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int userID = (int)session.get("userID");

        UserDAO userdao = new UserDAO();
        User user = userdao.get(userID);
        if (user != null){
            session.put("icon", user.getIcon());
            return "success";
        } else return "error";
    }

    public String password() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] olds = (String[]) params.get("password0");
        String oldpw = olds[0];
        String[] news = (String[]) params.get("password");
        String newpw = news[0];
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(userID);
        System.out.println("old" + oldpw);
        System.out.println(user.getPassword());
        if (user.getPassword().equals(oldpw)) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "修改密码成功请重新登陆");
            return userDAO.password(userID,newpw);
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "旧密码错误");
            return "error";
        }
    }

    public String banlaunch() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.banlaunch(userID);
    }

    public String restorelaunch() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.restorelaunch(userID);
    }

    public String banjoin() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.banjoin(userID);
    }

    public String restorejoin() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.restorejoin(userID);
    }

    public String baninvite() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.baninvite(userID);
    }

    public String restoreinvite() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.restoreinvite(userID);
    }

    public String bancomment() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.bancomment(userID);
    }

    public String restorecomment() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        UserDAO userDAO = new UserDAO();
        return userDAO.restorecomment(userID);
    }
}
