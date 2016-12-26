<%@ page import="entity.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/4
  Time: 10:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>搜索结果</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1 class="text-center">搜索结果</h1>
    </div>
    <div class="col-sm-12 col-md-8">
        <% String key = (String) request.getAttribute("key");
            List<User> users = (List<User>) request.getAttribute("users");
            if (users != null)
                for (User user : users) {
        %>
        <div class="col-sm-12 col-md-6">
            <div class="media">
                <div class="media-left">
                    <img class="media-object img-circle" src="<%=user.getIcon()%>" width="100" height="100">
                </div>
                <div class="media-body">
                    <h3 class="media-heading">
                        <%=user.getRealname()%>
                        <br/>
                        <small><%=user.getUsername()%>
                        </small>
                    </h3>
                    <br/>
                    <a href="applyfriend?key=<%=key%>&friendID=<%=user.getUserId()%>">
                        <button type="button" class="btn btn-primary">
                            加为好友
                        </button>
                    </a>
                </div>
            </div>

            <hr/>
        </div>
        <% } %>
    </div>
    <div class="col-sm-12 col-md-4" id="message">
        <% String message = (String) request.getAttribute("message");
            if (message != null) { %>
        <div class="alert alert-success" role="alert"><%=message%>
        </div>
        <% } %>
    </div>
</div>
</div>
</body>
</html>
