<%@ page import="entity.Take" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/29
  Time: 11:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    int eventID = (int) request.getAttribute("eventid");
%>
<html>
<head>
    <title>报名情况</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1>
            <p class="text-center">报名情况</p>
        </h1>
    </div>

    <ul class="nav nav-pills">
        <li role="presentation"><a href="#join">报名信息</a></li>
        <li role="presentation"><a href="#accept">确认名单</a></li>
        <li role="presentation"><a href="#refuse">拒绝名单</a></li>
        <li role="presentation"><a href="acceptall?eventid=<%=eventID%>">确认全部</a></li>
        <li role="presentation" class="dropdown">
            <a class="dropdown-toggle" data-toggle="dropdown" href="#" role="button" aria-haspopup="true" aria-expanded="false">
                按年级确认<span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
                <li role="presentation"><a href="acceptByGrade?eventid=<%=eventID%>&grade=2013">2013级</a></li>
                <li role="presentation"><a href="acceptByGrade?eventid=<%=eventID%>&grade=2014">2014级</a></li>
                <li role="presentation"><a href="acceptByGrade?eventid=<%=eventID%>&grade=2015">2015级</a></li>
                <li role="presentation"><a href="acceptByGrade?eventid=<%=eventID%>&grade=2016">2016级</a></li>
                <li role="presentation"><a href="acceptByGrade?eventid=<%=eventID%>&grade=研究生">研究生</a></li>
            </ul>
        </li>
    </ul>
    <br/>
    <div class="col-sm-12 col-md-12" id="join">
        <div class="panel panel-default">
            <div class="panel-heading">
                <h4>报名信息</h4>
            </div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>用户名</th>
                        <th>学号</th>
                        <th>年级</th>
                        <th>邮箱</th>
                        <th>手机</th>
                        <th>报名时间</th>
                        <th>状态</th>
                        <th>操作</th>
                    </tr>
                    <% List<Take> takes = (List<Take>) request.getAttribute("takes");
                        if (takes != null)
                            for (Take take : takes) {
                    %>
                    <tr>
                        <td><%=take.getUsername()%>
                        </td>
                        <td><%=take.getStudentId()%>
                        </td>
                        <td><%=take.getGrade()%>
                        </td>
                        <td><%=take.getEmail()%>
                        </td>
                        <td><%=take.getPhone()%>
                        </td>
                        <td><%=take.getTime()%>
                        </td>
                        <% if (take.getState() == 1) { %>
                        <td>待确认</td>
                        <td>
                            <div class="btn-group" role="group">
                                <button type="button" class="btn btn-default btn-sm">
                                    <a href="accept?eventid=<%=eventID%>&userid=<%=take.getUserId()%>">
                                        确认
                                    </a>
                                </button>
                                <button type="button" class="btn btn-default btn-sm">
                                    <a href="refuse?eventid=<%=eventID%>&userid=<%=take.getUserId()%>">
                                        拒绝
                                    </a>
                                </button>
                            </div>
                        </td>
                        <% } else if (take.getState() == 2) { %>
                        <td>已拒绝</td>
                        <td>
                            <button type="button" class="btn btn-default btn-sm">
                                <a href="cancel?eventid=<%=eventID%>&userid=<%=take.getUserId()%>">
                                    取消拒绝
                                </a>
                            </button>
                        </td>
                        <% } else if ((take.getState() == 3)||(take.getState() == 4)){ %>
                        <td>已确认</td>
                        <td>
                            <button type="button" class="btn btn-default btn-sm">
                                <a href="cancel?eventid=<%=eventID%>&userid=<%=take.getUserId()%>">
                                    取消确认
                                </a>
                            </button>
                        </td>
                        <% } %>

                    </tr>
                    <% } %>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-12  col-md-5" id="accept">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>已确认</h4></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>用户名</th>
                        <th>状态</th>
                        <th>签到</th>
                    </tr>
                    <% takes = (List<Take>) request.getAttribute("takes");
                        if (takes != null)
                            for (Take take2 : takes) {
                                if ((take2.getState() == 3) || (take2.getState() == 4)) {%>
                    <tr>
                        <td><%=take2.getUsername()%>
                        </td>
                        <%
                            if (take2.getState() == 3) {
                        %>
                        <td style="color:blue">未到</td>
                        <td>
                            <button type="button" class="btn btn-default btn-sm">
                                <a href="checkin?eventid=<%=eventID%>&userid=<%=take2.getUserId()%>">
                                    签到
                                </a>
                            </button>
                        </td>
                        <% } else { %>
                        <td>已到</td>
                        <td>
                            <button type="button" class="btn btn-default btn-sm">
                                <a href="accept?eventid=<%=eventID%>&userid=<%=take2.getUserId()%>">
                                    取消签到
                                </a>
                            </button>
                        </td>
                    </tr>
                    <% }
                    }
                    } %>
                </table>
            </div>
        </div>
    </div>
    <div class="col-sm-12 col-md-offset-2 col-md-5" id="refuse">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>已拒绝</h4></div>
            <div class="table-responsive">
                <table class="table table-hover">
                    <tr>
                        <th>用户名</th>
                    </tr>
                    <% takes = (List<Take>) request.getAttribute("takes");
                        if (takes != null)
                            for (Take take3 : takes) {
                                if (take3.getState() == 2) {%>
                    <tr>
                        <td><%=take3.getUsername()%>
                        </td>
                    </tr>
                    <%
                                }
                            }
                    %>
                </table>
            </div>
        </div>
    </div>
</div>
</body>
</html>
