<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 18:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>活动列表</title>
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
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/student/index">首页</a>
    <a href="${pageContext.request.contextPath}/student/activities" class="active">活动列表</a>
    <a href="${pageContext.request.contextPath}/student/my-activities">我的活动</a>
    <a href="${pageContext.request.contextPath}/student/update">个人信息</a>
    <a href="${pageContext.request.contextPath}/student/logout" style="float:right">退出登录</a>
</div>

<div class="header">
    <h1>校园活动列表</h1>
</div>

<table class="activity-list">
    <tr>
        <th>活动名称</th>
        <th>活动时间</th>
        <th>活动地点</th>
        <th>组织者</th>
        <th>状态</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${activities}" var="activity">
        <tr>
            <td>${activity.title}</td>
            <td>${activity.time}</td>
            <td>${activity.location}</td>
            <td>${activity.organizer_id}</td>
            <td>${activity.status}</td>
            <td>
                <a href="${pageContext.request.contextPath}/student/activity/${activity.id}" class="btn">查看详情</a>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
