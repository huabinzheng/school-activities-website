<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/27
  Time: 11:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>校园里那些活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
</head>
<body>
<script type="text/javascript">
    function check(){
        var username = document.getElementById('username').value;
        var password = document.getElementById('password').value;
        if((username == "")||(password == "")){
            alert("用户名或密码不能为空！")
            return false;
        }else{
            return true;
        }
    }
</script>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">登录</h1>
    </div>
    <form class="form-horizontal" action="login" method="post" onsubmit="return check();">
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">*用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
            </div>
            <div class="col-sm-4">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">*密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>

        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-1">
                <button type="submit" class="btn btn-default">登录</button>
            </div>
            <div class="col-sm-3">
                <% String message = (String) request.getAttribute("message");
                    if (message != null)  { %>
                <div class="alert alert-info" role="alert"><%=message%></div>
                <% } %>
            </div>
        </div>

    </form>
</div>
</body>
</html>

