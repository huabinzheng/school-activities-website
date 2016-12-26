<%@ page import="java.util.List" %>
<%@ page import="model.Invite" %>
<%@ page import="entity.User" %>
<%@ page import="model.Apply" %>
<%@ page import="entity.Event" %>
<%@ page import="model.EventDetail" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/1
  Time: 9:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的消息</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">我的消息</h1>
    </div>
    <div class="col-sm-12  col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>活动邀请</h4></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>来自</th>
                        <th>活动名称</th>
                        <th>操作</th>
                    </tr>
                    <% List<Invite> invites = (List<Invite>) request.getAttribute("invites");
                        if (invites != null)
                            for (Invite invite : invites) {
                    %>
                    <tr>
                        <td><%=invite.getSendername()%></td>
                        <td><a href="event?eventid=<%=invite.getEventId()%>"><%=invite.getEventname()%></a></td>
                        <td><button type="button" class="btn btn-default btn-sm">
                            <a href="deletemessage?mid=<%=invite.getMessageID()%>">
                                删除
                            </a>
                        </button></td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-12 col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>好友申请</h4></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th></th>
                        <th>用户名</th>
                        <th>姓名</th>
                        <th>操作</th>
                    </tr>
                    <% List<Apply> applys = (List<Apply>) request.getAttribute("applys");
                        if (applys != null)
                            for (Apply apply : applys) {
                    %>
                    <tr>
                        <td>
                            <img src="<%=apply.getIcon()%>" width="25" height="25"/>
                        </td>
                        <td>
                            <%=apply.getUsername()%>
                        </td>
                        <td><%=apply.getRealname()%></td>
                        <td>
                            <div class="btn-group" role="group" aria-label="...">
                                <button type="button" class="btn btn-default btn-sm">
                                    <a href="acceptfriend?mid=<%=apply.getmId()%>&friendID=<%=apply.getUserID()%>">同意</a>
                                </button>
                                <button type="button" class="btn btn-default btn-sm">
                                    <a href="deletemessage?mid=<%=apply.getmId()%>">删除</a>
                                </button>
                            </div>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-12  col-md-4">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>活动通知</h4></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>消息内容</th>
                        <th>操作</th>
                    </tr>
                    <% List<EventDetail> events = (List<EventDetail>) request.getAttribute("events");
                        if (events != null)
                            for (EventDetail event : events) { %>
                    <tr>
                        <td><%=event.getEventname()%> 已经被取消</td>
                        <td>
                            <button type="button" class="btn btn-default btn-sm">
                                <a href="deletemessage?mid=<%=event.getMessageID()%>">删除</a>
                            </button>
                        </td>
                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>

</body>
</html>
