<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/4
  Time: 15:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>活动审批</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }
        .activity-list {
            width: 100%;
            border-collapse: collapse;
        }
        .activity-list th, .activity-list td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .activity-list tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .activity-list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #4CAF50;
            color: white;
        }
        .btn {
            padding: 5px 10px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 3px;
            margin-right: 5px;
        }
        .btn-danger {
            background-color: #f44336;
        }
        .nav-menu {
            background-color: #333;
            overflow: hidden;
            margin-bottom: 20px;
        }
        .nav-menu a {
            float: left;
            color: white;
            text-align: center;
            padding: 14px 16px;
            text-decoration: none;
        }
        .nav-menu a:hover {
            background-color: #ddd;
            color: black;
        }
        .nav-menu a.active {
            background-color: #4CAF50;
        }
    </style>
    <script>
        function approveActivity(activityId) {
            if (confirm('确定要批准该活动吗？')) {
                fetch('${pageContext.request.contextPath}/admin/activity/approve/' + activityId, {
                    method: 'POST'
                })
                    .then(response => response.text())
                    .then(data => {
                        if (data === 'success') {
                            alert('活动已批准！');
                            location.reload();
                        } else {
                            alert('操作失败，请稍后再试！');
                        }
                    });
            }
        }

        function rejectActivity(activityId) {
            if (confirm('确定要驳回该活动吗？')) {
                fetch('${pageContext.request.contextPath}/admin/activity/reject/' + activityId, {
                    method: 'POST'
                })
                    .then(response => response.text())
                    .then(data => {
                        if (data === 'success') {
                            alert('活动已驳回！');
                            location.reload();
                        } else {
                            alert('操作失败，请稍后再试！');
                        }
                    });
            }
        }
    </script>
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/admin/dashboard">首页</a>
    <a href="${pageContext.request.contextPath}/admin/ybp" class="active">活动审批</a>
    <a href="${pageContext.request.contextPath}/admin/students">学生管理</a>
    <a href="${pageContext.request.contextPath}/admin/logout" style="float:right">退出登录</a>
</div>

<div class="header">
    <h1>待审核活动</h1>
</div>

<table class="activity-list">
    <tr>
        <th>活动名称</th>
        <th>活动时间</th>
        <th>活动地点</th>
        <th>组织者</th>
        <th>描述</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${pendingActivities}" var="activity">
        <tr>
            <td>${activity.title}</td>
            <td>${activity.time}</td>
            <td>${activity.location}</td>
            <td>${activity.organizer_id}</td>
            <td>${activity.description}</td>
            <td>
                <button class="btn" onclick="approveActivity(${activity.id})">批准</button>
                <button class="btn btn-danger" onclick="rejectActivity(${activity.id})">驳回</button>
            </td>
        </tr>
    </c:forEach>
</table>

<div class="header" style="margin-top: 30px;">
    <h1>已审核活动</h1>
</div>

<table class="activity-list">
    <tr>
        <th>活动名称</th>
        <th>活动时间</th>
        <th>活动地点</th>
        <th>组织者</th>
        <th>状态</th>
    </tr>
    <c:forEach items="${approvedActivities}" var="activity">
        <tr>
            <td>${activity.title}</td>
            <td>${activity.time}</td>
            <td>${activity.location}</td>
            <td>${activity.organizer_id}</td>
            <td>${activity.status}</td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
