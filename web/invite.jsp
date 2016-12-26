<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/1
  Time: 8:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% Integer inviteAuthority = (Integer) session.getAttribute("inviteAuthority");
    if (inviteAuthority != null)
        if (inviteAuthority != 1)
            response.sendRedirect("index");
%>
<html>
<head>
    <title>邀请好友</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">邀请好友</h1>
    </div>
    <div class="col-sm-12 col-md-8">
        <% List<User> friends = (List<User>) request.getAttribute("friends");
            if (friends != null)
                for (User friend : friends) {
        %>
        <div class="col-sm-12 col-md-6">
            <div class="media">
                <div class="media-left">
                    <img class="media-object img-circle" src="<%=friend.getIcon()%>" width="100" height="100">
                </div>
                <div class="media-body">
                    <h3 class="media-heading">
                        <%=friend.getRealname()%>
                        <small><%=friend.getUsername()%></small>
                    </h3>
                    <br/><br/>
                    <a href="sendinvite?friendID=<%=friend.getUserId()%>&friendname=<%=friend.getRealname()%>">
                        <button type="button" class="btn btn-primary">邀请</button>
                    </a>
                </div>
            </div>
            <hr/>
        </div>
        <% } %>
    </div>

    <div class="col-sm-12 col-md-4">
        <% String message = (String) request.getAttribute("message");
            if (message != null)  { %>
        <div class="alert alert-success" role="alert"><%=message%></div>
        <% } %>
    </div>
</div>
</body>
</html>
