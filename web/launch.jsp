<%@ page import="java.util.Date" %>
<%@ page import="java.text.DateFormat" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%--
  Created by IntelliJ IDEA.
  User: zhenghb
  Date: 2016/6/28
  Time: 9:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Date date = new Date();
    DateFormat fm = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    String curtime = fm.format(date);
    curtime = curtime.substring(0, 10) + "T" + curtime.substring(11, 16);
    Integer launchAuthority = (Integer) session.getAttribute("launchAuthority");
    if (launchAuthority == null) response.sendRedirect("index");
    if (launchAuthority != null)
        if (launchAuthority != 1)
            response.sendRedirect("index");
%>
<html>
<head>
    <title>发起活动</title>
    <link rel="stylesheet" href="css/bootstrap.css">
    <script src="js/jquery.js"></script>
    <script src="js/bootstrap.js"></script>
    <script>
        function check(){
            var eventname = document.getElementById('eventname').value;
            var location = document.getElementById('location').value;
            var content = document.getElementById('content').value;
            var file = document.getElementById("file").value;
            var eventstart = document.getElementById('eventstart').value;
            var eventend = document.getElementById('eventend').value;
            if (eventstart >= eventend){
                alert("活动时间结束时间不能早于开始时间");
                return false;
            }
            if((eventname == "")||(location == "")||(content == "")||(file == "")){
                alert("请填写完整活动信息！")
                return false;
            }else{
                return true;
            }
        }
    </script>
</head>
<body>
<jsp:include page="header.jsp"></jsp:include>
<div class="container">
    <div class="page-header" align="center">
        <h1>发起活动
            <small>请填写有关信息</small>
        </h1>
    </div>
    <form class="form-horizontal" enctype="multipart/form-data" action="launch" method="post" onsubmit="return check();">
        <div class="form-group">
            <label for="eventname" class="col-sm-4 control-label">活动名称</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="eventname" name="eventname" placeholder="请填写活动名称">
            </div>
        </div>
        <div class="form-group">
            <label for="location" class="col-sm-4 control-label">活动地点</label>
            <div class="col-sm-4">
                <input type="text" class="form-control" id="location" name="location" placeholder="请填写活动地点">
            </div>
        </div>
        <div class="form-group">
            <label for="eventstart" class="col-sm-4 control-label">活动开始时间</label>
            <div class="col-sm-3">
                <input type="datetime-local" class="form-control" id="eventstart" name="eventstart"
                       min=<%=curtime%> max="2017-12-31T00:00" value="2016-09-01T00:00"/>
            </div>
        </div>
        <div class="form-group">
            <label for="eventend" class="col-sm-4 control-label">活动结束时间</label>
            <div class="col-sm-3">
                <input type="datetime-local" class="form-control" id="eventend" name="eventend"
                       min=<%=curtime%> max="2017-12-31T00:00" value="2016-09-01T00:00"/>
            </div>
        </div>
        <div class="form-group">
            <label for="limitnum" class="col-sm-4 control-label">最大限制人数</label>
            <div class="col-sm-2">
                <input type="number" class="form-control" id="limitnum" name="limitnum" placeholder="0" value="0" min="0" max="1000">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">选择报名策略</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio" name="rule" id="opt1" value="0" checked="true">先到先得
                </label>
                <label class="radio-inline">
                    <input type="radio" name="rule" id="opt2" value="1">报名后筛选
                </label>
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-4 control-label">活动内容</label>
            <div class="col-sm-4">
                <textarea rows="5" class="form-control" id="content" name="content"></textarea>
            </div>
        </div>
        <div class="form-group">
            <label for="content" class="col-sm-4 control-label">海报</label>
            <div class="col-sm-4">
                <input type="file" id="file" name="uploadFile"/>
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-4 control-label">活动分类</label>
            <div class="col-sm-8">
                <label class="radio-inline">
                    <input type="radio" value="志愿者" id="cat1" name="category" checked="true">讲座
                </label>
                <label class="radio-inline">
                    <input type="radio" value="社团" id="cat2" name="category">社团
                </label>
                <label class="radio-inline">
                    <input type="radio" value="讲座" id="cat3" name="category">组织
                </label>
                <label class="radio-inline">
                    <input type="radio" value="企业" id="cat4" name="category">企业
                </label>
                <label class="radio-inline">
                    <input type="radio" value="其他" id="cat5" name="category">其他
                </label>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-4 col-sm-8">
                <button type="submit" class="btn btn-default">提交</button>
            </div>
        </div>
    </form>
</div>
</body>
</html>
