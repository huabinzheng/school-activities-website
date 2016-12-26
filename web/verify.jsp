<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/12
  Time: 9:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="java.util.List" %>
<%@ page import="entity.User" %>
<%@ taglib prefix="jso" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Integer authority = (Integer) session.getAttribute("authority");
    if (authority == null) {
        response.sendRedirect("login.jsp");
    } else if (authority != 1) {
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
        <h1 class="text-center">用户权限管理</h1>
    </div>
    <form class="navbar-form" action="searchusers" method="post" onsubmit="return adminCheckSearchUsers();">
        <div class="form-group">
            <input type="text" class="form-control" id="key2" name="key" placeholder="搜索用户">
        </div>
        <button type="submit" class="btn btn-default">搜索</button>
    </form>
    <div class="table-responsive">
        <table class="table table-hover">
            <tr>
                <th>用户名</th>
                <th>姓名</th>
                <th>年级</th>
                <th>发布活动权限</th>
                <th>操作</th>
                <th>报名活动权限</th>
                <th>操作</th>
                <th>邀请好友权限</th>
                <th>操作</th>
                <th>发布评论权限</th>
                <th>操作</th>
            </tr>
            <% List<User> users = (List<User>) request.getAttribute("users");
                if (users != null)
                    for (User user : users) {
            %>
            <tr>
                <td><%=user.getUsername()%>
                </td>
                <td><%=user.getRealname()%>
                </td>
                <td><%=user.getGrade()%>
                </td>
                <%
                    int launchAuthority = (user.getAuthority() >> 3) & 1;
                    int joinAuthority = (user.getAuthority() >> 2) & 1;
                    int inviteAuthority = (user.getAuthority() >> 1) & 1;
                    int commentAuthority = user.getAuthority() & 1;
                    if (launchAuthority == 1) { %>
                <td>
                    正常
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="banlaunch?userid=<%=user.getUserId()%>">禁止</a>
                    </button>
                </td>
                <% } else { %>
                <td>
                    禁止
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="restorelaunch?userid=<%=user.getUserId()%>">恢复</a>
                    </button>
                </td>
                <% } %>
                <% if (joinAuthority == 1) { %>
                <td>
                    正常
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="banjoin?userid=<%=user.getUserId()%>">禁止</a>
                    </button>
                </td>
                <% } else { %>
                <td>
                    禁止
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="restorejoin?userid=<%=user.getUserId()%>">恢复</a>
                    </button>
                </td>
                <% } %>
                <% if (inviteAuthority == 1) { %>
                <td>
                    正常
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="baninvite?userid=<%=user.getUserId()%>">禁止</a>
                    </button>
                </td>
                <% } else { %>
                <td>
                    禁止
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="restoreinvite?userid=<%=user.getUserId()%>">恢复</a>
                    </button>
                </td>
                <% } %>
                <% if (commentAuthority == 1) { %>
                <td>
                    正常
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="bancomment?userid=<%=user.getUserId()%>">禁止</a>
                    </button>
                </td>
                <% } else { %>
                <td>
                    禁止
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="restorecomment?userid=<%=user.getUserId()%>">恢复</a>
                    </button>
                </td>
                <% } %>

            </tr>
            <% } %>
        </table>
    </div>
</div>
<script>
    function adminCheckSearchUsers() {
        var key = document.getElementById('key2').value;
        if (key == "") {
            return false;
        } else {
            return true;
        }
    }
</script>
</body>
</html>
