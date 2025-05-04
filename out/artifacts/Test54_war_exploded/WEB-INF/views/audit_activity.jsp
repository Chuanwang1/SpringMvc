<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/4/29
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>审核活动</title>
</head>
<body>
<h2>待审核活动列表</h2>
<table border="1">
    <tr>
        <th>活动标题</th>
        <th>描述</th>
        <th>时间</th>
        <th>地点</th>
        <th>操作</th>
    </tr>
    <c:forEach var="activity" items="${pendingActivities}">
        <tr>
            <td>${activity.title}</td>
            <td>${activity.description}</td>
            <td>${activity.eventTime}</td>
            <td>${activity.location}</td>
            <td>
                <form action="approveActivity" method="post" style="display:inline;">
                    <input type="hidden" name="activityId" value="${activity.id}">
                    <input type="submit" value="审核通过">
                </form>
                <form action="rejectActivity" method="post" style="display:inline;">
                    <input type="hidden" name="activityId" value="${activity.id}">
                    <input type="submit" value="驳回">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<br>
<a href="logout">退出登录</a>
</body>
</html>
