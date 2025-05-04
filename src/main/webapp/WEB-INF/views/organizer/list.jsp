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
    <title>活动报名情况</title>
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
        .student-list {
            width: 100%;
            border-collapse: collapse;
        }
        .student-list th, .student-list td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .student-list tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .student-list th {
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
        .activity-info {
            border: 1px solid #ddd;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            background-color: #f9f9f9;
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

<h1>活动报名情况</h1>

<div class="activity-info">
    <h2>${activity.title}</h2>
    <p><strong>时间:</strong> ${activity.time}</p>
    <p><strong>地点:</strong> ${activity.location}</p>
    <p><strong>状态:</strong> ${activity.status}</p>
</div>

<c:if test="${empty registrations}">
    <p>暂无学生报名此活动。</p>
</c:if>

<c:if test="${not empty registrations}">
    <h2>已报名学生列表</h2>
    <table class="student-list">
        <tr>
            <th>学生姓名</th>
            <th>学号</th>
            <th>班级</th>
            <th>报名时间</th>
        </tr>
        <c:forEach items="${registrations}" var="registration">
            <tr>
                <td>${registration.student_name}</td>
                <td>${registration.student_id}</td>
                <td>${registration.class_name}</td>
                <td>${registration.register_time}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/organizer/dashboard" class="btn">返回我的活动</a>
</div>
</body>
</html>
