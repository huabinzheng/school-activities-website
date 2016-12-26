<%@ page import="entity.Event" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.CommentDetail" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Date" %>
<%@ page import="entity.User" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/28
  Time: 17:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();
    Event event = (Event) request.getAttribute("event");
    User host = (User) request.getAttribute("host");
    List<CommentDetail> comments = (List<CommentDetail>) request.getAttribute("comments");
    if (event != null) {
        Timestamp tsstart = event.getEventstart();
        Timestamp tsend = event.getEventend();
        Timestamp tslaunch = event.getTime();
        DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        String eventstart = fm.format(tsstart);
        String eventend = fm.format(tsend);
        String launchtime = fm.format(tslaunch);
        String curtime = fm.format(date);
%>
<html>
<head>
    <title>活动页</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script>
        function check() {
            var comment = document.getElementById('comment').value;
            if (comment == "") {
                alert("请完整填写评价内容");
                return false;
            } else return true;
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <!--标题-->
    <div class="page-header">
        <h1 class="text-center"><%=event.getEventname()%>
        </h1>
        <p class="text-right" style="color: grey;">
            发布时间：<%=launchtime%>
            浏览：<%=event.getView()%>
        </p>
    </div>
    <!--标题-->

    <div class="col-sm-12 col-md-offset-1 col-md-6">
        <div class="col-sm-12 col-md-12">
            <h3>内容</h3>
            <p>
                <%=event.getContent()%>
            </p>
            <img src="<%=event.getPoster()%>"/>
        </div>
        <hr/>
        <div class="col-sm-12 col-md-12">
            <h4>发表评价</h4>
            <form class="form-horizontal" action="comment" method="post" onsubmit="return check()">
                <div class="form-group col-md-12">
                    <textarea rows="5" class="form-control" id="comment" name="comment"></textarea>
                </div>
                <div class="form-group col-md-2">
                    <% Integer commentAuthority = (Integer) session.getAttribute("commentAuthority");
                        if (commentAuthority != null)
                            if (commentAuthority == 1) { %>
                    <button type="submit" class="btn btn-default">提交</button>
                    <% } else {%>
                    <button class="btn btn-default" disabled="disabled">无评价权限</button>
                    <% } %>
                </div>
            </form>
        </div>
        <div class="col-sm-12 col-md-12">
            <h4>评价</h4>
            <% if ((comments == null)||(comments.size() == 0)) { %>
            暂无评价
            <% } %>
            <ul class="media-list">
                <% if (comments != null)
                    for (CommentDetail comment : comments) { %>
                <li class="media">
                    <div class="media-left">
                        <img class="media-object" src="<%=comment.getIcon()%>" width="50" height="50">
                    </div>
                    <div class="media-body">
                        <h4 class="media-heading">
                            <%=comment.getRealname()%>
                            <small><%=fm.format(comment.getTime())%>
                            </small>
                        </h4>
                        <%=comment.getContent()%>
                        <% if (comment.getAnswer() != null) {%>
                        <div class="media">
                            <div class="media-body">
                                回复<%=comment.getRealname()%>：
                                <%=comment.getAnswer()%>
                            </div>
                        </div>
                        <% } %>
                    </div>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
    <div class="col-sm-12 col-md-offset-1 col-md-4">
        <ul class="list-group">
            <li class="list-group-item">分类：<%=event.getCategory()%></li>
            <li class="list-group-item">时间：<%=eventstart%>  至  <%=eventend%></li>
            <li class="list-group-item">地点：<%=event.getLocation()%></li>
            <li class="list-group-item">
                人数：<%=event.getJoinnum()%> / <% if (event.getLimitnum() == 0) {%> 不限 <% } else { %><%=event.getLimitnum()%> <%}%>
            </li>
        </ul>
        <img src="<%=host.getIcon()%>" class="img-rounded" width="50" height="50"/>
        发起：<%=host.getRealname()%>
        <hr/>

        <% Integer authority = (Integer) session.getAttribute("authority");
            if (curtime.compareTo(eventend) > 0) { %>
        <button type="button" class="btn btn-info">活动已结束</button>
        <% }
        if (authority != null) {
            int joined = (int) request.getAttribute("joined");
            if (joined == 1) { %>
        <button type="button" class="btn btn-primary">已经报名</button>
        <% Integer inviteAuthority = (Integer) session.getAttribute("inviteAuthority");
            if (inviteAuthority != null)
                if (inviteAuthority == 1) { %>
        <a href="invite"><button type="button" class="btn btn-info">邀请好友</button></a>
        <% } else { %>
        <button type="button" class="btn btn-default" disabled="disabled">无邀请权限</button>
        <% } %>
        <% } else if (event.getState() == 1) {
            Integer joinAuthority = (Integer) session.getAttribute("joinAuthority");
            if (joinAuthority != null)
                if (joinAuthority == 1) { %>
        <a href="join.jsp"><button type="button" class="btn btn-primary">我要报名</button></a>
        <% } else { %>
        <button type="button" class="btn btn-default" disabled="disabled">无报名权限</button>
        <% } %>
        <% Integer inviteAuthority = (Integer) session.getAttribute("inviteAuthority");
            if (inviteAuthority != null)
                if (inviteAuthority == 1) { %>
        <a href="invite">
            <button type="button" class="btn btn-info">邀请好友</button>
        </a>
        <% } else { %>
        <button type="button" class="btn btn-default" disabled="disabled">无邀请权限</button>
        <% } %>
        <% } else if (event.getState() == 2 || event.getState() == 3) { %>
        <button type="button" class="btn btn-info">截止报名</button>
        <% } %>
        <hr/>
        <% } %>
    </div>

</div>
</body>
</html>
<%}%>