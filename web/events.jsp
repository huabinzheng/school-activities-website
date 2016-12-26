<%@ page import="entity.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.FriendsEvent" %>
<%@ page import="java.util.Date" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/28
  Time: 12:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();
    DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String curtime = fm.format(date);
%>
<html>
<head>
    <title>活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<script>
    function checksearchevents() {
        var key = document.getElementById('eventkey').value;
        if (key == "") {
            return false;
        } else {
            return true;
        }
    }
</script>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1>全部活动</h1>
    </div>
    <div class="col-sm-12 col-md-8">
        <nav class="navbar navbar-default">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="events">全部</a>
                </div>
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="category?category=讲座">讲座</a></li>
                        <li><a href="category?category=社团">社团</a></li>
                        <li><a href="category?category=组织">组织</a></li>
                        <li><a href="category?category=企业">企业</a></li>
                        <li><a href="category?category=其他">其他</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" action="searchevents" method="post"
                          onsubmit="return checksearchevents();">
                        <div class="form-group">
                            <input type="text" class="form-control" id="eventkey" name="eventkey" placeholder="搜索活动">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
        </nav>

        <% List<Event> events = (List<Event>) request.getAttribute("events");
            if (events != null)
                for (Event event : events) {
                    Timestamp tsstart = event.getEventstart();
                    Timestamp tsend = event.getEventend();
                    String eventstart = fm.format(tsstart);
                    String eventend = fm.format(tsend);

        %>
        <div class="media">
            <div class="media-left">
                <a href="event?eventid=<%=event.getEventId()%>">
                    <img class="media-object" src="<%=event.getPoster()%>" height="120" width="120"
                         alt="<%=event.getEventname()%>">
                </a>
            </div>
            <div class="media-body">
                <h3 class="media-heading">
                    <a href="event?eventid=<%=event.getEventId()%>">
                        <%=event.getEventname()%>
                    </a>
                </h3>
                <p>分类 <%=event.getCategory()%>
                </p>
                <p>时间 <%=eventstart%> - <%=eventend%>
                </p>
                <p>地点 <%=event.getLocation()%>
                </p>
                <p>
                    <% if (curtime.compareTo(eventend) > 0) { %>
                    <span class="label label-success">活动已结束</span>
                    <% } else if (curtime.compareTo(eventstart) > 0) { %>
                    <span class="label label-success">活动已开始</span>
                    <% } else if (event.getState() == 1) {%>
                    <span class="label label-primary">报名进行中</span>
                    <% } else if (event.getState() == 2) { %>
                    <span class="label label-success">报名已人满</span>
                    <% } else if (event.getState() == 3) { %>
                    <span class="label label-success">报名已截止</span>
                    <% } %>
                </p>
            </div>
        </div>
        <hr/>
        <% } %>
        <%
            int pagenum = (int) request.getAttribute("page");
            int firstpage = (int) request.getAttribute("firstpage");
            int lastpage = (int) request.getAttribute("lastpage");
        %>
        <nav>
            <ul class="pager">
                <% if (firstpage == 0) { %>
                <li class="previous">
                    <a href="events?page=<%=pagenum - 1%>">
                        <span aria-hidden="true">&larr;</span> 上一页
                    </a>
                </li>
                <% } %>
                <% if (lastpage == 0) { %>
                <li class="next">
                    <a href="events?page=<%=pagenum + 1%>">
                        下一页 <span aria-hidden="true">&rarr;</span>
                    </a>
                </li>
                <% } %>
            </ul>
        </nav>
    </div>

    <div class="col-sm-12 col-md-3">
        <% Integer launchAuthority = (Integer) session.getAttribute("launchAuthority");
            if (launchAuthority != null)
                if (launchAuthority == 1) { %>
        <a href="launch.jsp">
            <button type="button" class="btn btn-primary btn-lg">发起活动</button>
        </a>
        <% } else { %>
            <button type="button" class="btn btn-default btn-lg" disabled="disabled">无发起活动权限</button>
        <% } %>
        <div class="col-sm-12 col-md-12">
            <% List<Event> recommends = (List<Event>) session.getAttribute("recommends");
                if (recommends != null) { %>
            <div class="page-header">
                <h3>推荐活动</h3>
            </div>
            <% for (Event recommend : recommends) {
                Timestamp tsstart = recommend.getEventstart();
                String eventstart = fm.format(tsstart);
            %>
            <div class="media">
                <div class="media-left">
                    <img src="<%=recommend.getPoster()%>" width="50" height="50">
                </div>
                <div class="media-body">
                    <h4><a href="event?eventid=<%=recommend.getEventId()%>">
                        <%=recommend.getEventname()%>
                    </a></h4>
                    <p><%=eventstart%> <%=recommend.getLocation()%>
                    </p>
                </div>
                <% } %>
            </div>
            <% } %>

            <!-- 好友动态 -->
            <% List<FriendsEvent> friendsEvents = (List<FriendsEvent>) session.getAttribute("friendsEvents");
                if (friendsEvents != null) { %>
            <div class="page-header">
                <h3>好友动态</h3>
            </div>
            <% for (FriendsEvent friendsEvent : friendsEvents) { %>
            <div class="media">
                <div class="media-left">
                    <img class="img-circle" src="<%=friendsEvent.getIcon()%>" width="50" height="50">
                </div>

                <div class="media-body">
                    <p>
                        <%=friendsEvent.getUsername()%>
                    </p>
                    <a href="event?eventid=<%=friendsEvent.getEventId()%>">
                        <%=friendsEvent.getEventname()%>
                    </a>
                </div>
            </div>
            <% } %>
        </div>
        <% } %>
    </div>
</div>
</div>
</body>
</html>
