<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>活动详情</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .activity-detail {
            max-width: 800px;
            margin: 0 auto;
        }
        .activity-info {
            border: 1px solid #ddd;
            padding: 20px;
            border-radius: 5px;
            margin-bottom: 20px;
        }
        .activity-title {
            font-size: 24px;
            color: #333;
            margin-bottom: 10px;
        }
        .info-item {
            margin-bottom: 10px;
        }
        .info-label {
            font-weight: bold;
            display: inline-block;
            width: 100px;
        }
        .btn {
            padding: 8px 15px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 3px;
            margin-right: 10px;
        }
        .btn-secondary {
            background-color: #6c757d;
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
    <a href="${pageContext.request.contextPath}/organizer/dashboard">我的活动</a>
    <a href="${pageContext.request.contextPath}/organizer/activity/create">创建活动</a>
    <a href="${pageContext.request.contextPath}/organizer/logout" style="float:right">退出登录</a>
</div>

<div class="activity-detail">
    <h1>活动详情</h1>

    <div class="activity-info">
        <div class="activity-title">${activity.title}</div>
        <div class="info-item">
            <span class="info-label">活动时间:</span> ${activity.time}
        </div>
        <div class="info-item">
            <span class="info-label">活动地点:</span> ${activity.location}
        </div>
        <div class="info-item">
            <span class="info-label">状态:</span> ${activity.status}
        </div>
        <div class="info-item">
            <span class="info-label">描述:</span>
            <p>${activity.description}</p>
        </div>
    </div>

    <div>
        <a href="${pageContext.request.contextPath}/organizer/activity/edit/${activity.id}" class="btn">编辑活动</a>
        <a href="${pageContext.request.contextPath}/organizer/activity/registrations/${activity.id}" class="btn">查看报名情况</a>
        <a href="${pageContext.request.contextPath}/organizer/dashboard" class="btn btn-secondary">返回列表</a>
    </div>
</div>
</body>
</html>
