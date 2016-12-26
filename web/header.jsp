<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/28
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<script>
    function checksearchusers(){
        var key = document.getElementById('key').value;
        if (key == ""){
            return false;
        }else{
            return true;
        }
    }
</script>
<!-- 导航栏 -->
<nav class="navbar navbar-default">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index">校园里那些活动</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li><a href="index">首页</a></li>
                <%
                    Integer userID = (Integer) session.getAttribute("userID");
                    if (userID != null) {
                %>
                <%  int authority = (Integer) session.getAttribute("authority");
                     if (authority == 1) {
                %>
                <li><a href="admin">管理员</a></li>
                <% } %>
                <li><a href="events">活动</a></li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的账号
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li class="dropdown-header">管理</li>
                        <li><a href="profile.jsp">修改个人信息</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a href="logout">退出登录</a></li>
                    </ul>
                </li>
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的活动
                        <span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="viewjoin">我参加的活动</a></li>
                        <li><a href="viewlaunch">我发起的活动</a></li>
                    </ul>
                </li>
                <li><a href="friend">好友</a></li>
                <li><a href="message">消息</a></li>
            </ul>
            <form class="navbar-form navbar-right" action="search" method="post" onsubmit="return checksearchusers();">
                <div class="form-group">
                    <input type="text" class="form-control" id="key" name="key" placeholder="搜索用户">
                </div>
                <button type="submit" class="btn btn-default">搜索</button>
            </form>
            <% } else { %>
            <ul class="nav navbar-nav">
                <li><a href="register.jsp">注册</a></li>
                <li><a href="login.jsp">登录</a></li>
            </ul>
            <% } %>
        </div>
    </div>
</nav>
<!-- 导航栏 -->
</body>
</html>
