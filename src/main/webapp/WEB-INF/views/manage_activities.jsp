<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/4/29
  Time: 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>管理发布的活动</title>
</head>
<body>
<h2>我的发布活动</h2>
<table border="1">
  <tr>
    <th>活动标题</th>
    <th>时间</th>
    <th>地点</th>
    <th>状态</th>
    <th>操作</th>
  </tr>
  <c:forEach var="activity" items="${activities}">
    <tr>
      <td>${activity.title}</td>
      <td>${activity.eventTime}</td>
      <td>${activity.location}</td>
      <td>${activity.status}</td>
      <td>
        <form action="deleteActivity" method="post" style="display:inline;">
          <input type="hidden" name="activityId" value="${activity.id}">
          <input type="submit" value="删除">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<a href="createActivityPage">发布新活动</a> |
<a href="logout">退出登录</a>
</body>
</html>
