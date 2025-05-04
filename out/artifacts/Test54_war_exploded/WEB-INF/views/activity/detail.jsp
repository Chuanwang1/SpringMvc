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
    <title>活动详情</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .activity-detail {
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
        .activity-info {
            margin-bottom: 5px;
        }
        .btn {
            padding: 8px 15px;
            text-decoration: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 3px;
            border: none;
            cursor: pointer;
            margin-right: 10px;
        }
        .btn-danger {
            background-color: #f44336;
        }
    </style>
    <script>
        function registerActivity(activityId) {
            if (confirm('确定要报名参加此活动吗？')) {
                fetch('${pageContext.request.contextPath}/student/activity/join/' + activityId, {
                    method: 'POST'
                })
                    .then(response => response.text())
                    .then(data => {
                        if (data === 'success') {
                            alert('报名成功！');
                            location.reload();
                        } else if (data === 'login_required') {
                            alert('请先登录！');
                            window.location.href = '${pageContext.request.contextPath}/student/login';
                        } else {
                            alert('报名失败，请稍后再试！');
                        }
                    });
            }
        }

        function cancelRegistration(activityId) {
            if (confirm('确定要取消报名吗？')) {
                fetch('${pageContext.request.contextPath}/student/activity/cancel/' + activityId, {
                    method: 'POST'
                })
                    .then(response => response.text())
                    .then(data => {
                        if (data === 'success') {
                            alert('取消报名成功！');
                            location.reload();
                        } else {
                            alert('取消报名失败，请稍后再试！');
                        }
                    });
            }
        }
    </script>
</head>
<body>
<h1>活动详情</h1>

<div class="activity-detail">
    <div class="activity-title">${activity.title}</div>
    <div class="activity-info"><strong>时间：</strong>${activity.time}</div>
    <div class="activity-info"><strong>地点：</strong>${activity.location}</div>
    <div class="activity-info"><strong>组织者：</strong>${activity.organizer_id}</div>
    <div class="activity-info"><strong>状态：</strong>${activity.status}</div>
    <div class="activity-info"><strong>描述：</strong>${activity.description}</div>
</div>

<div>
    <c:choose>
        <c:when test="${isRegistered}">
            <button class="btn btn-danger" onclick="cancelRegistration(${activity.id})">取消报名</button>
        </c:when>
        <c:otherwise>
            <button class="btn" onclick="registerActivity(${activity.id})">报名参加</button>
        </c:otherwise>
    </c:choose>
    <a href="${pageContext.request.contextPath}/activity/list" class="btn">返回列表</a>
</div>
</body>
</html>
