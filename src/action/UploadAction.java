package action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import dao.UserDAO;
import entity.User;
import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.UUID;
/**
 * Created by zhenghb on 2016/7/5.
 */
public class UploadAction extends ActionSupport {
    private File uploadFile;
    private String uploadFileContentType;
    private String uploadFileFileName;
    public File getUploadFile() {return uploadFile;}
    public void setUploadFile(File uploadFile) {this.uploadFile = uploadFile;}
    public String getUploadFileContentType() {return uploadFileContentType;}
    public void setUploadFileContentType(String uploadFileContentType) {this.uploadFileContentType = uploadFileContentType;}
    public String getUploadFileFileName() {return uploadFileFileName;}
    public void setUploadFileFileName(String uploadFileFileName) {this.uploadFileFileName = uploadFileFileName;}
    public String icon() throws IOException {
        String realPath = ServletActionContext.getServletContext().getRealPath("/images/user");
        ActionContext context = ActionContext.getContext();
        Map session = context.getSession();
        int userID = (int)session.get("userID");
        uploadFileFileName = userID + uploadFileFileName.substring(uploadFileFileName.lastIndexOf('.'));
        System.out.println(uploadFileContentType);
        if(uploadFileContentType.equals("image/gif") || uploadFileContentType.equals("image/jpeg") ||
                uploadFileContentType.equals("image/png") || uploadFileContentType.equals("image/bmp") ||
                uploadFileContentType.equals("image/x-icon") || uploadFileContentType.equals("image/pjpeg")) {
            if(uploadFile != null && uploadFile.length() < 2097152) {
                File filePath = new File(new File(realPath), uploadFileFileName);
                if(!filePath.getParentFile().exists()) {filePath.getParentFile().mkdirs();}
                try {
                    FileUtils.copyFile(uploadFile, filePath);
                    UserDAO userDAO = new UserDAO();
                    try{
                        userDAO.setIcon(userID,uploadFileFileName);
                    } catch(Exception e){
                    }
                } catch (IOException e) {
                    System.out.println("图片上传失败");
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }
}
