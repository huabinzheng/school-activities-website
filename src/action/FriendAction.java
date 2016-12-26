package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.FriendDAO;
import dao.MessageDAO;
import entity.User;

import java.util.Map;

/**
 * Created by zhenghb on 2016/7/4.
 */
public class FriendAction extends ActionSupport {
    public String accept() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] mIDs = (String[]) params.get("mid");
        int mID = Integer.parseInt(mIDs[0]);
        String[] friendIDs = (String[]) params.get("friendID");
        int friendID = Integer.parseInt(friendIDs[0]);
        Map session = context.getSession();
        int userID = (int)session.get("userID");

        FriendDAO friendDAO = new FriendDAO();
        String result1 = friendDAO.add(userID, friendID);
        String result2 = friendDAO.add(friendID,userID);
        if (result1.equals("success") && (result2.equals("success"))){
            MessageDAO messageDAO = new MessageDAO();
            messageDAO.delete(mID);
            return "success";
        } else return "error";
    }

    public String delete() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] friendIDs = (String[]) params.get("friendid");
        int friendID = Integer.parseInt(friendIDs[0]);
        Map session = context.getSession();
        int userID = (int)session.get("userID");

        FriendDAO friendDAO = new FriendDAO();
        return friendDAO.delete(userID,friendID);
    }

}
