package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.EventDAO;
import dao.FriendDAO;
import dao.MessageDAO;
import dao.UserDAO;
import entity.Event;
import entity.Message;
import entity.User;
import model.Apply;
import model.EventDetail;
import model.Invite;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.jws.soap.SOAPBinding;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenghb on 2016/7/1.
 */
public class MessageAction extends ActionSupport {
    public String browse() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        MessageDAO messagedao = new MessageDAO();
        List<Message> messages1 = messagedao.getApply(userID);
        List<Message> messages2 = messagedao.getInvite(userID);
        List<Message> messages3 = messagedao.getEvent(userID);
        List<Invite> invites = new ArrayList<>();
        List<Apply> applys = new ArrayList<>();
        List<EventDetail> events = new ArrayList<>();
        if (messages1 != null){
            for (Message message1:messages1){
                int senderID = message1.getSender();
                UserDAO userdao = new UserDAO();
                User sender = userdao.get(senderID);
                Apply apply = new Apply();
                apply.setmId(message1.getmId());
                apply.setUserID(sender.getUserId());
                apply.setUsername(sender.getUsername());
                apply.setRealname(sender.getRealname());
                apply.setIcon(sender.getIcon());
                applys.add(apply);
            }
        }
        if (messages2 != null){
            for (Message message2:messages2){
                int eventID = message2.getEventId();
                int senderID = message2.getSender();
                EventDAO eventdao = new EventDAO();
                Event event = eventdao.get(eventID);
                UserDAO userdao = new UserDAO();
                User sender = userdao.get(senderID);
                Invite invite = new Invite();
                invite.setMessageID(message2.getmId());
                invite.setSenderId(sender.getUserId());
                invite.setSendername(sender.getRealname());
                invite.setEventId(event.getEventId());
                invite.setEventname(event.getEventname());
                invite.setState(message2.getState());
                invites.add(invite);
            }
        }
        if (messages3 != null){
            for (Message message3:messages3){
                int eventID = message3.getEventId();
                EventDAO eventdao = new EventDAO();
                Event event = eventdao.get(eventID);
                EventDetail eventDetail = new EventDetail();
                eventDetail.setMessageID(message3.getmId());
                eventDetail.setEventId(message3.getEventId());
                eventDetail.setEventname(event.getEventname());
                events.add(eventDetail);
            }
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("invites", invites);
        request.setAttribute("applys",applys);
        request.setAttribute("events",events);
        return "success";
    }

    public String invite() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        Map params = context.getParameters();
        int eventID = (int)session.get("eventID");
        int userID = (int)session.get("userID");
        String[] friendIDs = (String[]) params.get("friendID");
        int friendID = Integer.parseInt(friendIDs[0]);
        String[] friendnames = (String[]) params.get("friendname");
        String friendname = friendnames[0];
        MessageDAO messagedao = new MessageDAO();
        if (messagedao.invite(eventID,userID,friendID).equals("success")){
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "已经成功邀请 " + friendname);
            return "success";
        } else {
            return "error";
        }
    }

    public String apply() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] receivers = (String[]) params.get("friendID");
        int receiver = Integer.parseInt(receivers[0]);
        Map session = context.getSession();
        int sender = (int)session.get("userID");

        if (sender == receiver) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "不能添加自己为好友");
            return "success";
        }
        FriendDAO friendDAO = new FriendDAO();
        int result = friendDAO.find(sender,receiver);
        if (result == 2) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "已经成为好友关系");
            return "success";
        } else {
            MessageDAO messagedao = new MessageDAO();
            messagedao.apply(sender,receiver);
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("message", "已经发送好友申请");
            return messagedao.apply(sender,receiver);
        }
    }

    public String delete() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] mIDs = (String[]) params.get("mid");
        int mID = Integer.parseInt(mIDs[0]);
        MessageDAO messagedao = new MessageDAO();
        return messagedao.delete(mID);
    }
}
