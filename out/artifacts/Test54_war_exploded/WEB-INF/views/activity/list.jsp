<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/4
  Time: 15:43
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
    </style>
</head>
<body>
<h1>校园活动列表</h1>

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
                <a href="${pageContext.request.contextPath}/activity/detail/${activity.id}" class="btn">查看详情</a>
            </td>
        </tr>
    </c:forEach>
</table>

<div style="margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/student/index" class="btn">返回首页</a>
</div>
</body>
</html>
