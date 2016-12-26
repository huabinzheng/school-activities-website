<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/1
  Time: 9:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>我的好友</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">我的好友</h1>
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
                    <a href="deletefriend?friendid=<%=friend.getUserId()%>">
                        <button type="button" class="btn btn-warning btn-xs">
                            删除好友
                        </button>
                    </a>
                </div>
            </div>
            <hr/>
        </div>
        <% } %>
    </div>
</div>
</body>
</html>

</body>
</html>
