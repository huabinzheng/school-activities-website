<%@ page import="entity.Event" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/29
  Time: 10:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    Date date = new Date();
    String curtime = fm.format(date);
%>
<html>
<head>
    <title>发起的活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1>
            <p class="text-center">发起的活动</p>
        </h1>
    </div>
    <div class="table-responsive">
        <table class="table table-hover">
            <tr>
                <th>活动名称</th>
                <th>发布时间</th>
                <!--th>活动地点</th>
                <th>活动时间</th-->
                <th>已报名</th>
                <th>浏览</th>
                <th>活动状态</th>
                <th>管理</th>
                <th>操作</th>
            </tr>
            <% List<Event> events = (List<Event>) request.getAttribute("events");
                if (events != null)
                    for (Event event : events) {
                        Timestamp tsstart = event.getEventstart();
                        //Timestamp tsend = event.getEventend();
                        Timestamp tslaunch = event.getTime();
                        String eventstart = fm.format(tsstart);
                        //String eventend = fm.format(tsend);
                        String launchtime = fm.format(tslaunch);
            %>
            <tr>
                <td><a href="event?eventid=<%=event.getEventId()%>"><%=event.getEventname()%></a></td>
                <td><%=launchtime%></td>
                <!--td>%=event.getLocation()%</td>
                <td>%=eventstart% - %=eventend%</td-->
                <td><%=event.getJoinnum()%></td>
                <td><%=event.getView()%></td>

                <td>
                    <% if (event.getState() == -2) { %>
                    <span class="label label-danger">活动已取消</span>
                    <% } if (event.getState() == -1) { %>
                    <span class="label label-danger">审核未通过</span>
                    <% } if (event.getState() == 0) { %>
                    <span class="label label-info">活动审核中</span>
                    <% } else if (event.getState() == 1) {%>
                    <span class="label label-primary">报名进行中</span>
                    <% } else if (event.getState() == 2) { %>
                    <span class="label label-success">报名已人满</span>
                    <% } else if (event.getState() == 3) { %>
                    <span class="label label-success">报名已截止</span>
                    <% } %>
                </td>
                <td>
                    <div class="btn-group" role="group" aria-label="...">
                        <% if (event.getState() == 1) { %>
                        <button type="button" class="btn btn-default btn-sm">
                            <a href="stop?eventid=<%=event.getEventId()%>">截止报名</a>
                        </button>
                        <% } else { %>
                        <button type="button" class="btn btn-default btn-sm" disabled="disabled">
                            <a href="#">截止报名</a>
                        </button>
                        <% } %>
                        <button type="button" class="btn btn-default btn-sm">
                            <a href="participant?eventid=<%=event.getEventId()%>">用户管理</a>
                        </button>
                        <button type="button" class="btn btn-default btn-sm">
                            <a href="viewcomment?eventid=<%=event.getEventId()%>">评价管理</a>
                        </button>
                    </div>
                </td>
                <td>
                    <%  if ((curtime.compareTo(eventstart) < 0) && (event.getState() != -1)){ %>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="cancelevent?eventid=<%=event.getEventId()%>">取消活动</a>
                    </button>
                    <% } %>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
