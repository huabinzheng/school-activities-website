<%@ page import="entity.Event" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/28
  Time: 19:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Integer joinAuthority = (Integer) session.getAttribute("joinAuthority");
    if (joinAuthority == null) response.sendRedirect("index");
    if (joinAuthority != null)
        if (joinAuthority != 1)
            response.sendRedirect("index");
%>
<html>
<head>
    <title>报名活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script>
        function check(){
            var email = document.getElementById('email').value;
            var phone = document.getElementById('phone').value;
            var studentID = document.getElementById('studentID').value;

            if((email == "")||(phone == "")||(studentID == "")){
                alert("请填写完整报名信息！")
                return false;
            }else{
                return true;
            }
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1><p class="text-center">报名活动<small>请填写有关信息</small></p></h1>
    </div>
    <form class="form-horizontal" action="join" method="post" onsubmit="return check();">
        <div class="form-group">
            <label for="studentID" class="col-sm-4 control-label">学号 / 工号</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="studentID" name="studentID" placeholder="请填写学号或工号">
            </div>
        </div>
        <div class="form-group">
            <label for="email" class="col-sm-4 control-label">邮箱</label>
            <div class="col-sm-4">
                <input type="email" class="form-control" id="email" name="email" placeholder="请填写邮箱">
            </div>
        </div>
        <div class="form-group">
            <label for="phone" class="col-sm-4 control-label">手机</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="phone" name="phone" placeholder="请填写手机">
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-1">
                <button type="submit" class="btn btn-default">报名</button>
            </div>
            <div class="col-sm-offset-1 col-sm-2">
                <% String message = (String) request.getAttribute("message");
                    if (message != null)  { %>
                <div class="alert alert-success" role="alert"><%=message%></div>
                <% } %>
            </div>

        </div>
    </form>
</div>
</body>
</html>
