<%@ page import="entity.Event" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/8
  Time: 14:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer authority = (Integer)session.getAttribute("authority");
    if (authority == null) {
        response.sendRedirect("login.jsp");
    } else if(authority != 1){
        response.sendRedirect("index");
    }
%>
<html>
<head>
    <title>校园里那些活动 - 管理</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <ul class="nav nav-pills">
        <li role="presentation"><a href="admin">新活动审核</a></li>
        <li role="presentation"><a href="verify">用户权限管理</a></li>
        <li role="presentation"><a href="manage">活动管理</a></li>
        <li role="presentation"><a href="vis.jsp">用户聚类</a></li>
    </ul>
    <div class="page-header">
        <h1 class="text-center">新的活动审核</h1>
    </div>
    <div class="table-responsive">
        <table class="table table-hover">
            <tr>
                <th>活动名称</th>
                <th>操作</th>
            </tr>
            <% List<Event> events = (List<Event>) request.getAttribute("events");
                if (events != null)
                    for (Event event : events) {
            %>
            <tr>
                <td><a href="event?eventid=<%=event.getEventId()%>"><%=event.getEventname()%></a></td>
                <td>
                    <button type="button" class="btn btn-default">
                        <a href="pass?eventid=<%=event.getEventId()%>">审核通过</a>
                    </button>
                    <button type="button" class="btn btn-default">
                        <a href="fail?eventid=<%=event.getEventId()%>">审核未通过</a>
                    </button>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
