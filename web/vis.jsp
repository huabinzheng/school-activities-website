<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/9/1
  Time: 16:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校园里那些活动 - 聚类</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script src="js/d3.v3.js"></script>
    <script src="js/galaxy.js"></script>
    <script src="js/main.js"></script>
    <style>

        .chart rect {
            fill: steelblue;
        }

        .chart text {
            fill: white;
            font: 10px sans-serif;
            text-anchor: end;
        }

        circle {
            fill: rgb(31, 79, 80);
            fill-opacity: .75;
            stroke: rgb(31, 119, 80);
            stroke-width: 1px;
        }

        .leaf circle {
            fill: #ff7f0e;
            fill-opacity: 1;
        }

        text {
            font: 10px sans-serif;
        }

        .link {
            stroke: #999;
            stroke-opacity: .6;
        }

    </style>
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
        <h1 class="text-center">用户聚类</h1>
    </div>
    <div>
        <svg class="chart"></svg>
        <script src="js/main.js"></script>
        <script type="text/javascript">
            main("admin.csv");
        </script>
    </div>

</div>
</body>
</html>
