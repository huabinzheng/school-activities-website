package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.*;
import entity.Comment;
import entity.Event;

import entity.Take;
import entity.User;
import model.CommentDetail;
import model.FriendsEvent;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;


import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhenghb on 2016/6/28.
 */
public class EventAction extends ActionSupport {
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;

    public File getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(File uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getUploadFileContentType() {
        return uploadFileContentType;
    }

    public void setUploadFileContentType(String uploadFileContentType) {
        this.uploadFileContentType = uploadFileContentType;
    }

    public String getUploadFileFileName() {
        return uploadFileFileName;
    }

    public void setUploadFileFileName(String uploadFileFileName) {
        this.uploadFileFileName = uploadFileFileName;
    }


    public String index() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        EventDAO eventdao = new EventDAO();
        List<Event> latests = eventdao.latest();
        request.setAttribute("latests", latests);
        return "success";
    }

    public String admin() throws Exception {
        EventDAO eventdao = new EventDAO();
        List<Event> events = eventdao.adminbrowse();
        if (events != null) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("events", events);
            return "success";
        } else return "error";
    }

    public String pass() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);

        EventDAO eventdao = new EventDAO();
        return eventdao.pass(eventID);
    }

    public String fail() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);

        EventDAO eventdao = new EventDAO();
        return eventdao.fail(eventID);
    }

    public String browse() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] pages = (String[]) params.get("page");
        int page;
        if (pages == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pages[0]);
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("page", page);
        if (page == 1) {
            request.setAttribute("firstpage", 1);
        } else {
            request.setAttribute("firstpage", 0);
        }
        EventDAO eventdao = new EventDAO();
        List<Event> events = eventdao.browse(page);
        if (events != null && events.size() < 5)  {
            request.setAttribute("lastpage", 1);
        } else {
            request.setAttribute("lastpage", 0);
        }
        if (events != null) {
            request.setAttribute("events", events);
        }
        return "success";
    }

    public String browseByCategory() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] categories = (String[]) params.get("category");
        String category = categories[0];
        String[] pages = (String[]) params.get("page");
        int page;
        if (pages == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pages[0]);
        }
        EventDAO eventdao = new EventDAO();
        List<Event> events = eventdao.browseByCategory(category,page);

        FavorDAO favorDAO = new FavorDAO();
        Map session = context.getSession();
        int userID = (int) session.get("userID");
        favorDAO.update(userID, category, 1);

        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("page", page);
        if (page == 1) {
            request.setAttribute("firstpage", 1);
        } else {
            request.setAttribute("firstpage", 0);
        }
        if (events.size() < 5)  {
            request.setAttribute("lastpage", 1);
        } else {
            request.setAttribute("lastpage", 0);
        }
        if (events != null) {
            request.setAttribute("events", events);
        }
        return "error";
    }

    public String search() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] keys = (String[]) params.get("eventkey");
        String key = keys[0];
        String[] pages = (String[]) params.get("page");
        int page;
        if (pages == null) {
            page = 1;
        } else {
            page = Integer.parseInt(pages[0]);
        }

        EventDAO eventDAO = new EventDAO();
        List<Event> events = eventDAO.search(key,page);
        HttpServletRequest request = ServletActionContext.getRequest();
        request.setAttribute("events", events);
        request.setAttribute("page", page);
        if (page == 1) {
            request.setAttribute("firstpage", 1);
        } else {
            request.setAttribute("firstpage", 0);
        }
        if (events.size() < 5)  {
            request.setAttribute("lastpage", 1);
        } else {
            request.setAttribute("lastpage", 0);
        }
        return "success";
    }


    public String view() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        Map session = context.getSession();

        int eventID;
        String[] eventIDs = (String[]) params.get("eventid");
        if (eventIDs == null) {
            eventID = (int) session.get("eventID");
        } else {
            eventID = Integer.parseInt(eventIDs[0]);
            session.put("eventID", eventID);
        }

        HttpServletRequest request = ServletActionContext.getRequest();
        TakeDAO takedao = new TakeDAO();
        Integer userID = (Integer) session.get("userID");
        if (userID != null) {
            int result = takedao.check(eventID, userID);
            if (result == 1) {
                request.setAttribute("joined", 0);
            } else request.setAttribute("joined", 1);
            // joined 为 1 已报名活动
        }

        EventDAO eventdao = new EventDAO();
        Event event = eventdao.view(eventID);
        UserDAO userDAO = new UserDAO();
        User host = userDAO.get(event.getHost());
        CommentDAO commentDAO = new CommentDAO();
        List<Comment> comments = commentDAO.getComments(eventID);

        FavorDAO favorDAO = new FavorDAO();
        favorDAO.update(userID, event.getCategory(), 1);

        if (event != null) {
            List<CommentDetail> commentDetails = new ArrayList<>();
            for (Comment comment : comments) {
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
            request.setAttribute("event", event);
            request.setAttribute("host", host);
            request.setAttribute("comments", commentDetails);
            return "success";
        } else return "error";
    }

    public String save() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventnames = (String[]) params.get("eventname");
        String[] locations = (String[]) params.get("location");
        String[] eventstarts = (String[]) params.get("eventstart");
        String[] eventends = (String[]) params.get("eventend");
        String[] limitnums = (String[]) params.get("limitnum");
        String[] rules = (String[]) params.get("rule");
        String[] contents = (String[]) params.get("content");
        String[] categorys = (String[]) params.get("category");
        String eventname = eventnames[0];
        String location = locations[0];
        String eventstart = eventstarts[0];
        eventstart = eventstart.substring(0, 10) + " " + eventstart.substring(11, 16) + ":00";
        Timestamp tsstart = Timestamp.valueOf(eventstart);
        System.out.println("start time"+tsstart);
        String eventend = eventends[0];
        eventend = eventend.substring(0, 10) + " " + eventend.substring(11, 16) + ":00";
        Timestamp tsend = Timestamp.valueOf(eventend);
        int limitnum = Integer.parseInt(limitnums[0]);
        int rule = Integer.parseInt(rules[0]);
        String content = contents[0];
        String category = categorys[0];

        String realPath = ServletActionContext.getServletContext().getRealPath("/images/event");
        uploadFileFileName = UUID.randomUUID().toString() + uploadFileFileName.substring(uploadFileFileName.lastIndexOf('.'));
        if (uploadFileContentType.equals("image/gif") || uploadFileContentType.equals("image/jpeg") ||
                uploadFileContentType.equals("image/png") || uploadFileContentType.equals("image/bmp") ||
                uploadFileContentType.equals("image/x-icon") || uploadFileContentType.equals("image/pjpeg")) {
            if (uploadFile != null && uploadFile.length() < 2097152) {
                File filePath = new File(new File(realPath), uploadFileFileName);
                if (!filePath.getParentFile().exists()) {
                    filePath.getParentFile().mkdirs();
                }
                try {
                    FileUtils.copyFile(uploadFile, filePath);
                } catch (IOException e) {
                    System.out.println("图片上传失败");
                    e.printStackTrace();
                }
            }
        }

        Map session = context.getSession();
        int userID = (int) session.get("userID");
        Event event = new Event();
        event.setEventname(eventname);
        event.setHost(userID);
        event.setEventstart(tsstart);
        event.setEventend(tsend);
        event.setLocation(location);
        event.setLimitnum(limitnum);
        event.setJoinnum(0);
        event.setContent(content);
        event.setRule(rule);
        event.setState(0);
        event.setView(0);
        event.setCategory(category);
        event.setPoster("images/event/" + uploadFileFileName);
        EventDAO eventdao = new EventDAO();
        eventdao.save(event);
        return "success";
    }


    public String viewlaunch() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map ses = context.getSession();
        int userID = (int) ses.get("userID");

        EventDAO eventdao = new EventDAO();
        List<Event> events = eventdao.viewlaunch(userID);
        if (events != null) {
            HttpServletRequest request = ServletActionContext.getRequest();
            request.setAttribute("events", events);
            return "success";
        } else return "error";
    }

    public String manage() throws Exception {
        ActionContext context = ActionContext.getContext();
        HttpServletRequest request = ServletActionContext.getRequest();
        EventDAO eventdao = new EventDAO();
        List<Event> events = eventdao.manage();
        request.setAttribute("events", events);
        return "success";
    }

    public String stop() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);

        EventDAO eventDAO = new EventDAO();
        return eventDAO.stop(eventID);
    }

    public String cancelevent() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        TakeDAO takeDAO = new TakeDAO();
        List<Take> takes = takeDAO.participant(eventID);
        MessageDAO messageDAO = new MessageDAO();
        for (Take take:takes){
            int userID = take.getUserId();
            messageDAO.cancel(eventID,userID);
        }
       EventDAO eventDAO = new EventDAO();
        return eventDAO.cancel(eventID);
    }

    public String deleteevent() throws Exception {
        ActionContext context = ActionContext.getContext();
        Map params = context.getParameters();
        String[] eventIDs = (String[]) params.get("eventid");
        int eventID = Integer.parseInt(eventIDs[0]);
        TakeDAO takeDAO = new TakeDAO();
        List<Take> takes = takeDAO.participant(eventID);
        MessageDAO messageDAO = new MessageDAO();
        for (Take take:takes){
            int userID = take.getUserId();
            messageDAO.admincancel(eventID,userID);
        }
        EventDAO eventDAO = new EventDAO();
        Event event = eventDAO.get(eventID);
        messageDAO.admincancel(eventID,event.getHost());
        return eventDAO.cancel(eventID);
    }
}
