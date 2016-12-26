package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.CommentDAO;
import dao.UserDAO;
import entity.Comment;
import entity.Event;
import entity.User;
import model.CommentDetail;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by zhenghb on 2016/7/1.
 */
public class CommentAction extends ActionSupport {
    public String comment() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] contents = (String[]) params.get("comment");
        String content = contents[0];
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        int eventID = (int)session.get("eventID");

        Comment comment = new Comment();
        comment.setEventId(eventID);
        comment.setUserId(userID);
        comment.setContent(content);
        CommentDAO commentdao = new CommentDAO();
        return commentdao.add(comment);
    }

    public String viewcomment() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        CommentDAO commentdao = new CommentDAO();
        UserDAO userDAO = new UserDAO();
        List<Comment> comments = commentdao.getComments(eventID);

        List<CommentDetail> commentDetails = new ArrayList<>();
        for (Comment comment:comments){
            User user = userDAO.get(comment.getUserId());
            CommentDetail commentDetail = new CommentDetail();
            commentDetail.setcId(comment.getcId());
            commentDetail.setEventId(comment.getEventId());
            commentDetail.setUserId(comment.getUserId());
            commentDetail.setUsername(user.getUsername());
            commentDetail.setRealname(user.getRealname());
            commentDetail.setIcon(user.getIcon());
            commentDetail.setContent(comment.getContent());
            commentDetail.setAnswer(comment.getAnswer());
            commentDetail.setTime(comment.getTime());
            commentDetails.add(commentDetail);
        }
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("comments", commentDetails);
        return "success";
    }

    public String answer() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] answers = (String[]) params.get("answer");
        String answer = answers[0];
        String[] cIDs = (String[]) params.get("cid");
        int cID = Integer.parseInt(cIDs[0]);
        CommentDAO commentdao = new CommentDAO();
        return  commentdao.answer(cID,answer);
    }

    public String delete() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] cIDs = (String[]) params.get("cid");
        int cID = Integer.parseInt(cIDs[0]);
        CommentDAO commentdao = new CommentDAO();
        return commentdao.delete(cID);
    }
}
