<%@ page import="entity.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="model.CommentDetail" %><%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/7/1
  Time: 15:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    DateFormat fm = new SimpleDateFormat("yyyy/MM/dd HH:mm");
%>
<html>
<head>
    <title>查看评价</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
</head>
<body>
<script>
    function check() {
        var answer = document.getElementById('answer').value;
        if (answer == "") {
            alert("请完整填写回复内容");
            return false;
        } else return true;
    }
</script>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header">
        <h1>
            <p class="text-center">评价管理</p>
        </h1>
    </div>
    <div class="table-responsive">
        <table class="table table-hover">
            <tr>
                <th></th>
                <th>用户</th>
                <th>评价内容</th>
                <th>评价时间</th>
                <th>回复</th>
                <th>操作</th>
            </tr>
            <%
                List<CommentDetail> comments = (List<CommentDetail>) request.getAttribute("comments");
                if (comments != null)
                    for (CommentDetail comment : comments) { %>
            <tr>
                <td><img src="<%=comment.getIcon()%>" width="25" height="25"/></td>
                <td><%=comment.getRealname()%></td>
                <td><%=comment.getContent()%></td>
                <td><%=fm.format(comment.getTime())%></td>
                <td><% if (comment.getAnswer() == null){%>
                    <form class="form-inline" action="answer?eventid=<%=comment.getEventId()%>&cid=<%=comment.getcId()%>" method="post" onsubmit="return check()">
                        <div class="form-group">
                            <input type="text" class="form-control" id="answer" name="answer">
                        </div>
                        <button type="submit" class="btn btn-default btn-sm">回复</button>
                    </form>
                    <% } else { %>
                    <%=comment.getAnswer()%><% } %>
                </td>
                <td>
                    <button type="button" class="btn btn-default btn-sm">
                        <a href="deletecomment?eventid=<%=comment.getEventId()%>&cid=<%=comment.getcId()%>">删除</a>
                    </button>
                </td>
            </tr>
            <% } %>
        </table>
    </div>
</div>
</body>
</html>
