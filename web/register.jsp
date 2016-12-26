<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/27
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        var name = document.getElementById('name').value;
        if((username == "")||(password == "")||(name == "")){
            alert("用户名、姓名或密码不能为空！")
            return false;
        }else{
            return true;
        }
    }
</script>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">注册</h1>
    </div>
    <form class="form-horizontal" action="register" method="post" onsubmit="return check();">
        <div class="form-group">
            <label for="username" class="col-sm-4 control-label">用户名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名">
            </div>
            <div class="col-sm-4">
            </div>
        </div>
        <div class="form-group">
            <label for="name" class="col-sm-4 control-label">姓名</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="name" name="name" placeholder="请输入姓名">
            </div>
            <div class="col-sm-4">
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-sm-4 control-label">密码</label>
            <div class="col-sm-4">
                <input type="password" class="form-control" id="password" name="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">性别</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio" value="1" name="gender" checked="true">男
                </label>
                <label class="radio-inline">
                    <input type="radio" value="0" name="gender">女
                </label>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">年级</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio" value="2013" name="grade" checked="true">2013
                </label>
                <label class="radio-inline">
                    <input type="radio" value="2014" name="grade">2014
                </label>
                <label class="radio-inline">
                    <input type="radio" value="2015" name="grade">2015
                </label>
                <label class="radio-inline">
                    <input type="radio" value="2016" name="grade">2016
                </label>
                <label class="radio-inline">
                    <input type="radio" value="研究生" name="grade">研究生
                </label>
                <label class="radio-inline">
                    <input type="radio" value="教职工" name="grade">教职工
                </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-2">
                <button type="submit" class="btn btn-default">注册</button>
            </div>
            <div class="col-sm-2" id="warning">
                <% String message = (String) request.getAttribute("message");
                    if (message != null)  { %>
                <div class="alert alert-warning" role="alert"><%=message%></div>
                <% } %>
            </div>
        </div>
    </form>
</div>
</body>
</html>
