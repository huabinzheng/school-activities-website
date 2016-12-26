<%@ page import="entity.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.FriendsEvent" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/27
  Time: 10:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <title>校园里那些活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <style>
        #poster {
            background: #000 url(img/index.jpg) no-repeat center center;
            background-size: cover;
            color:#ffffff;
            font-family: "Arial Black";
            text-shadow: #000 2px 2px 10px;

        }
    </style>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="jumbotron" id="poster">
        <h1>校园里那些活动</h1>
        <p>全新的校园活动聚集网站</p>
    </div>
    <!-- 最新活动 -->
    <div class="page-header">
        <h1>最新活动
            <small><a href="events">查看所有活动&raquo;</a></small>
        </h1>
    </div>
    <div class="row">
        <% List<Event> latests = (List<Event>) request.getAttribute("latests");
            if (latests != null)
                for (Event latest : latests) {
                    Timestamp tsstart = latest.getEventstart();
                    DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                    String eventstart = fm.format(tsstart);
        %>
        <div class="col-sm-6 col-md-3 event">
            <a href="event?eventid=<%=latest.getEventId()%>">
                <img class="img-rounded" src="<%=latest.getPoster()%>" width="150" height="150">
            </a>
            <a href="event?eventid=<%=latest.getEventId()%>">
                <h3><%=latest.getEventname()%></h3>
            </a>
            <p><%=eventstart%> <%=latest.getLocation()%></p>
        </div>
        <% } %>
    </div>
    <!-- 最新活动 -->

    <!-- 推荐活动 -->
    <% List<Event> recommends = (List<Event>) session.getAttribute("recommends");
        if (recommends != null) { %>
    <div class="page-header">
        <h1>猜你喜欢
            <small>&raquo;</small>
        </h1>
    </div>
    <div class="row">
        <% for (Event recommend : recommends) {
            Timestamp tsstart = recommend.getEventstart();
            DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            String eventstart = fm.format(tsstart);
        %>
        <div class="col-sm-6 col-md-3 event">
            <a href="event?eventid=<%=recommend.getEventId()%>">
                <img class="img-rounded" src="<%=recommend.getPoster()%>" width="150" height="150">
            </a>
            <a href="event?eventid=<%=recommend.getEventId()%>">
                <h3><%=recommend.getEventname()%></h3>
            </a>
            <p><%=eventstart%> <%=recommend.getLocation()%></p>
        </div>
        <% } %>
    </div>
    <% } %>
</div>
</body>
</html>
