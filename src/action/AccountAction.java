package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.*;
import entity.*;
import model.FriendsEvent;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by zhenghb on 2016/6/28.
 */
public class AccountAction extends ActionSupport {
    public String register() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] usernames = (String[]) params.get("username");
        String[] passwords = (String[]) params.get("password");
        String[] names = (String[]) params.get("name");
        String[] grades = (String[]) params.get("grade");
        String[] genders = (String[]) params.get("gender");
        String username = usernames[0];
        String password = passwords[0];
        String name = names[0];
        String grade = grades[0];
        Integer gender = Integer.parseInt(genders[0]);

        UserDAO userdao = new UserDAO();
        int result = userdao.find(username, password);

        if (result == 1) { //不存在该用户名
            User user = new User();
            user.setUsername(username);
            user.setRealname(name);
            user.setPassword(password);
            user.setAuthority(15);
            /*15对应二进制01111
             * 第一位代表普通用户 第二位代表发布活动权限 第三位代表报名活动权限
             * 第四位代表邀请好友权限 第五位代表评价活动权限
             */
            user.setGrade(grade);
            user.setGender(gender);
            user.setIcon("images/user/default.png");
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "账号注册成功");
            return userdao.add(user);
        } else {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "用户名已经存在");
            return "error";
        }
    }

    /*
    * 登录
    * 1. 判断用户名密码是否正确
    * 2. session中存入用户名，权限等信息
    * 3. 基于内容推荐某分类下最新两项活动
    * 4. 协同推荐推荐相似的三名用户参加的活动
    */
    public String login() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] usernames = (String[]) params.get("username");
        String[] passwords = (String[]) params.get("password");
        String username = usernames[0];
        String password = passwords[0];

        UserDAO userdao = new UserDAO();
        int result = userdao.find(username, password);
        if ((result == 1) || (result == 2)) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "用户名不存在或密码错误");
            return "error";
        }

        /*
        * 权限转换二进制数
        * 第一位为0 代表普通用户
        * 第二位代表发布活动权限 第三位代表报名活动权限
        * 第四位代表邀请好友权限 第五位代表评价活动权限
        */
        User user = userdao.login(username);
        int userID = user.getUserId();
        String icon = user.getIcon();
        Map session = context.getSession();
        session.put("username", username);
        session.put("userID", userID);
        session.put("icon", icon);
        int authority = user.getAuthority() >> 4;
        int launchAuthority = (user.getAuthority() >> 3) & 1;
        int joinAuthority = (user.getAuthority() >> 2) & 1;
        int inviteAuthority = (user.getAuthority() >> 1) & 1;
        int commentAuthority = user.getAuthority() & 1;
        session.put("authority", authority);
        session.put("launchAuthority", launchAuthority);
        session.put("joinAuthority", joinAuthority);
        session.put("inviteAuthority", inviteAuthority);
        session.put("commentAuthority", commentAuthority);
        if (authority == 1) return "admin";
        //管理员权限为1(11111-31)  普通用户为0


        EventDAO eventDAO = new EventDAO();
        FavorDAO favorDAO = new FavorDAO();
        TakeDAO takeDAO = new TakeDAO();
        Favor favor = favorDAO.recommend(userID);
        HashSet results = new HashSet();
        if (favor == null) {
            //冷启动基于人口特征的推荐
            favorDAO.add(userID);
            List<Take> takes = favorDAO.cold(user);
            for (Take take : takes) {
                int eventID = take.getEventId();
                Event event = eventDAO.get(eventID);
                //活动未参加过且未结束
                if (event.getState() == 1) {
                    System.out.println("推荐活动" + eventID + event.getEventname());
                    results.add(eventID);
                }
            }
        } else {
            //基于内容的推荐
            String cat = favorDAO.getCategory(favor);
            results = eventDAO.recommend(cat);
            //协同推荐
            List<Integer> similarUsers = favorDAO.similar(userID);
            if (similarUsers != null && similarUsers.size() > 0)
                for (int i = 0; i < 3; i++) {
                    if (i >= similarUsers.size()) break;
                    Integer itr = similarUsers.get(i);
                    System.out.println("相似用户" + itr);
                    List<Take> takes = takeDAO.viewjoin(itr);
                    for (Take take : takes) {
                        int eventID = take.getEventId();
                        Event event = eventDAO.get(eventID);
                        //活动未参加过且未结束
                        if ((takeDAO.check(eventID, userID) == 1) && (event.getState() == 1)) {
                            System.out.println("推荐活动" + eventID + event.getEventname());
                            results.add(eventID);
                        }
                    }
                }
        }
        List<Event> recommends = new ArrayList<>();
        int cnt = 0;
        for (Iterator it = results.iterator(); it.hasNext(); ) {
            int eventID = (int) it.next();
            Event event = eventDAO.get(eventID);
            recommends.add(event);
            cnt++;
            if (cnt == 4) break;
        }
        session.put("recommends", recommends);

        //好友动态
        List<FriendsEvent> friendsEvents = takeDAO.friendsjoin(userID);
        session.put("friendsEvents", friendsEvents);
        return "success";
    }


    public String logout() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        session.remove("username");
        session.remove("userID");
        session.remove("recommend");
        session.remove("authority");
        session.remove("launchAuthority");
        session.remove("joinAuthority");
        session.remove("inviteAuthority");
        session.remove("commentAuthority");
        session.remove("recommends");
        session.remove("friendsEvents");
        return "success";
    }
}
