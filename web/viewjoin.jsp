<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/29
  Time: 14:58
  To change this template use File | Settings | File Templates.
--%>

<%@ page import="java.util.List" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.Join" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();
    DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
    String curtime = fm.format(date);
%>
<html>
<head>
    <title>参加的活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1>
            <p class="text-center">参加的活动</p>
        </h1>
    </div>

        <table class="table table-hover">
            <tr>
                <th>活动分类</th>
                <th>活动名称</th>
                <th>活动地点</th>
                <th>活动时间</th>
                <th>活动状态</th>
                <th>报名时间</th>
                <th>报名状态</th>
                <th>操作</th>
            </tr>
            <% List<Join> events = (List<Join>) request.getAttribute("events");
                if (events != null)
                    for (Join event : events) {
                        Timestamp tsstart = event.getEventstart();
                        Timestamp tsend = event.getEventend();
                        Timestamp tslaunch = event.getTime();
                        String eventstart = fm.format(tsstart);
                        String eventend = fm.format(tsend);
                        String launchtime = fm.format(tslaunch);
            %>
            <tr>
                <td><%=event.getCategory()%>
                </td>
                <td><a href="event?eventid=<%=event.getEventId()%>"><%=event.getEventname()%>
                </a></td>
                <td><%=event.getLocation()%>
                </td>
                <td><%=eventstart%> - <%=eventend%>
                </td>
                <td>
                    <% if (event.getEventstate() == -2) {%>
                    <span class="label label-danger">活动被取消</span>
                    <% } else if (curtime.compareTo(eventend) > 0) {%>
                    <span class="label label-success">活动已结束</span>
                    <% } else if (curtime.compareTo(eventstart) > 0) {%>
                    <span class="label label-success">活动已开始</span>
                    <% } else if (event.getEventstate() == 1) {%>
                    <span class="label label-primary">报名进行中</span>
                    <% } else if (event.getEventstate() == 2) { %>
                    <span class="label label-success">报名已人满</span>
                    <% } else if (event.getEventstate() == 3) { %>
                    <span class="label label-success">报名已截止</span>
                    <% } else if (event.getEventstate() == 4) { %>
                    <span class="label label-success">活动已结束</span>
                    <% } %>
                </td>
                <td><%=launchtime%>
                </td>
                <% if (event.getState() == 0) {%>
                <td>
                    <span class="label label-info">已取消报名</span>
                </td>
                <td></td>
                <% }else if (event.getState() == 1) {%>
                <td>
                    <span class="label label-primary">报名待确认</span>
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-xs">
                        <a href="canceljoin?eventid=<%=event.getEventId()%>">取消报名</a>
                    </button>
                </td>
                <% } else if (event.getState() == 2) {%>
                <td>
                    <span class="label label-warning">报名被拒绝</span>
                </td>
                <td></td>
                <% } else if (event.getState() == 3) { %>
                <td>
                    <span class="label label-success">报名已成功</span>
                </td>
                <td></td>
                <% } else if (event.getState() == 4) { %>
                <td>
                    <span class="label label-success">活动已签到</span>
                </td>
                <td>
                    <% if (event.getScore() != 0) { %>
                    <%=event.getScore()%>分
                    <% } else { %>
                    <div class="dropdown">
                        <button class="btn btn-default btn-xs dropdown-toggle" type="button" id="dropdownMenu1" data-toggle="dropdown" aria-haspopup="true" aria-expanded="true">
                            评分
                            <span class="caret"></span>
                        </button>
                        <ul class="dropdown-menu" aria-labelledby="dropdownMenu1">
                            <li><a href="mark?eventid=<%=event.getEventId()%>&score=5">5分</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="mark?eventid=<%=event.getEventId()%>&score=4">4分</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="mark?eventid=<%=event.getEventId()%>&score=3">3分</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="mark?eventid=<%=event.getEventId()%>&score=2">2分</a></li>
                            <li role="separator" class="divider"></li>
                            <li><a href="mark?eventid=<%=event.getEventId()%>&score=1">1分</a></li>
                        </ul>
                    </div>
                    <% }%>
                </td>
                <% } %>


            </tr>
            <% } %>
        </table>

</div>
</body>
</html>

</html>
