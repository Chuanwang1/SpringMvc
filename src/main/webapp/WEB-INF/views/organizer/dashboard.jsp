<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/4
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>组织者仪表盘</title>
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
        function deleteActivity(activityId) {
            if (confirm('确定要删除该活动吗？此操作不可恢复！')) {
                fetch('${pageContext.request.contextPath}/organizer/activity/delete/' + activityId, {
                    method: 'POST'
                })
                    .then(response => response.text())
                    .then(data => {
                        if (data === 'success') {
                            alert('删除成功！');
                            location.reload();
                        } else if (data === 'unauthorized') {
                            alert('您没有权限删除此活动！');
                        } else {
                            alert('删除失败，请稍后再试！');
                        }
                    });
            }
        }
    </script>
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/organizer/dashboard" class="active">我的活动</a>
    <a href="${pageContext.request.contextPath}/organizer/activity/create">创建活动</a>
    <a href="${pageContext.request.contextPath}/organizer/logout" style="float:right">退出登录</a>
</div>

<div class="header">
    <h1>欢迎，${organizer.name}</h1>
    <a href="${pageContext.request.contextPath}/organizer/activity/create" class="btn">创建新活动</a>
</div>

<h2>我创建的活动</h2>

<c:if test="${empty activities}">
    <p>您还没有创建任何活动。</p>
</c:if>

<c:if test="${not empty activities}">
    <table class="activity-list">
        <tr>
            <th>活动名称</th>
            <th>活动时间</th>
            <th>活动地点</th>
            <th>状态</th>
            <th>操作</th>
        </tr>
        <c:forEach items="${activities}" var="activity">
            <tr>
                <td>${activity.title}</td>
                <td>${activity.time}</td>
                <td>${activity.location}</td>
                <td>${activity.status}</td>
                <td>
                    <a href="${pageContext.request.contextPath}/organizer/activity/edit/${activity.id}" class="btn">编辑</a>
                    <a href="${pageContext.request.contextPath}/organizer/activity/registrations/${activity.id}" class="btn">查看报名</a>
                    <button class="btn btn-danger" onclick="deleteActivity(${activity.id})">删除</button>
                </td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
