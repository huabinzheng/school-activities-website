<%@ page import="entity.User" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/5
  Time: 10:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>修改个人资料</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<%
    String iconpath = (String) session.getAttribute("icon");
%>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">个人资料</h1>
    </div>
    <!--div class="col-sm-12 col-md-3">
        <div class="list-group">
            <a href="#" class="list-group-item">修改头像</a>
            <a href="#" class="list-group-item">修改密码</a>
        </div>
    </div-->
    <div class="col-sm-12 col-md-5">
        <form class="form-horizontal" enctype="multipart/form-data" action="uploadicon" method="post"
              onsubmit="return iconcheck()">
            <div class="form-group">
                <div class="col-md-offset-4 col-md-8">
                    <img src="<%=iconpath%>" class="img-circle" width="150" height="150"/>
                </div>
            </div>
            <div class="form-group">
                <label for="file" class="col-md-4 control-label">上传头像</label>
                <div class="col-md-8">
                    <input type="file" id="file" name="uploadFile"/>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-4 col-sm-8">
                    <button type="submit" class="btn btn-default">上传</button>
                </div>
            </div>
        </form>
    </div>
    <div class="col-sm-12 col-md-offset-1 col-md-5">
        <div class="col-md-offset-4 col-md-8"><h3>修改密码</h3></div>
        <form class="form-horizontal" enctype="multipart/form-data" action="password" method="post" onsubmit="return pwcheck()">
            <div class="form-group">
                <label for="old" class="col-md-4 control-label">原密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="old" name="password0" placeholder="请输入旧密码">
                </div>
            </div>
            <div class="form-group">
                <label for="new" class="col-md-4 control-label">新密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="new" name="password" placeholder="请输入新密码">
                </div>
            </div>
            <div class="form-group">
                <label for="new2" class="col-md-4 control-label">重复新密码</label>
                <div class="col-md-8">
                    <input type="password" class="form-control" id="new2" name="password2" placeholder="请再次输入新密码">
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-offset-4 col-md-4">
                    <button type="submit" class="btn btn-default">确认</button>
                </div>
                <div class="col-md-4">
                    <% String message = (String) request.getAttribute("message");
                        if (message != null)  { %>
                    <div class="alert alert-warning" role="alert"><%=message%></div>
                    <% } %>
            </div>
        </form>
    </div>
</div>
</div>
<script>
    function iconcheck() {
        var file = document.getElementById("file").value;
        if (file == "") {
            alert("请选择上传的头像图片！");
            return false;
        } else {
            return true;
        }
    }
    function pwcheck() {
        var oldpw = document.getElementById('old').value;
        var newpw = document.getElementById('new').value;
        var newpw2 = document.getElementById('new2').value;

        if ((oldpw == "") || (newpw == "") || (newpw2 == "")) {
            alert("请完整填写密码");
            return false;
        } else if (newpw != newpw2) {
            alert("新密码两次输入不一致");
            return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>
