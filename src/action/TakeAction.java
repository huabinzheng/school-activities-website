package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.EventDAO;
import dao.FavorDAO;
import dao.TakeDAO;
import dao.UserDAO;
import entity.Event;
import entity.User;
import model.Join;
import entity.Take;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenghb on 2016/6/29.
 */
public class TakeAction extends ActionSupport {
    public String participant() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);

        TakeDAO takedao = new TakeDAO();
        List<Take> takes = takedao.participant(eventID);
        if (takes != null){
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("eventid", eventID);
            request.setAttribute("takes", takes);
            return "success";
        } else return "error";
    }

    public String save() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int eventID = (int)session.get("eventID");
        int userID = (int)session.get("userID");
        String username = (String) session.get("username");
        Map params = context.getParameters();
        String[] studentIDs = (String[]) params.get("studentID");
        String studentID = studentIDs[0];
        String[] phones = (String[]) params.get("phone");
        String phone = phones[0];
        String[] emails = (String[]) params.get("email");
        String email = emails[0];

        UserDAO userDAO = new UserDAO();
        User user = userDAO.get(userID);
        Take take = new Take();
        take.setUserId(userID);
        take.setUsername(username);
        take.setEventId(eventID);
        take.setStudentId(studentID);
        take.setEmail(email);
        take.setPhone(phone);
        take.setState(1);
        take.setGrade(user.getGrade());

        EventDAO eventDAO = new EventDAO();
        Event event = eventDAO.get(eventID);
        FavorDAO favorDAO = new FavorDAO();
        favorDAO.update(userID, event.getCategory(), 5);

        TakeDAO takedao = new TakeDAO();
        return takedao.save(take);
    }

    public String canceljoin() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        Map session = context.getSession();
        int userID = (int)session.get("userID");

        TakeDAO takedao = new TakeDAO();
        return takedao.canceljoin(eventID,userID);
    }

    public String accept() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.accept(eventID,userID);
    }

    public String acceptall() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.acceptall(eventID);
    }

    public String acceptgrade() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] grades = (String[]) params.get("grade");
        String grade = grades[0];

        TakeDAO takedao = new TakeDAO();
        return takedao.acceptgrade(eventID,grade);
    }

    public String refuse() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.refuse(eventID,userID);
    }

    public String cancel() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.cancel(eventID,userID);
    }

    public String mark() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] scores = (String[]) params.get("score");
        int score = Integer.parseInt(scores[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.mark(eventID,userID,score);
    }

    public String checkin() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        String[] userIDs = (String[]) params.get("userid");
        int userID = Integer.parseInt(userIDs[0]);

        TakeDAO takedao = new TakeDAO();
        return takedao.checkin(eventID,userID);
    }

    public String viewjoin() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        TakeDAO takedao = new TakeDAO();
        List<Take> takes = takedao.viewjoin(userID);
        List<Join> joins = new ArrayList<>();
        if (takes != null){
            for (Take take : takes) {
                int eventID = take.getEventId();
                EventDAO eventdao = new EventDAO();
                Event event = eventdao.get(eventID);

                Join join = new Join();
                join.setEventId(event.getEventId());
                join.setEventname(event.getEventname());
                join.setEventstart(event.getEventstart());
                join.setEventend(event.getEventend());
                join.setLocation(event.getLocation());
                join.setCategory(event.getCategory());
                join.setEventstate(event.getState());
                join.setTime(take.getTime());
                join.setState(take.getState());
                if (take.getScore() == null) join.setScore(0);
                    else join.setScore(take.getScore());
                joins.add(join);
            }
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("events", joins);
            return "success";
        } else return "error";
    }
}
