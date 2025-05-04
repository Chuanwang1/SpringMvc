<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/4/29
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <title>查看可报名活动</title>
</head>
<body>
<h2>欢迎，${student.name}！以下是当前可报名的活动：</h2>
<table border="1">
  <tr>
    <th>活动名称</th>
    <th>活动时间</th>
    <th>地点</th>
    <th>描述</th>
    <th>操作</th>
  </tr>
  <c:forEach var="activity" items="${activities}">
    <tr>
      <td>${activity.title}</td>
      <td>${activity.eventTime}</td>
      <td>${activity.location}</td>
      <td>${activity.description}</td>
      <td>
        <form action="registerActivity" method="post">
          <input type="hidden" name="activityId" value="${activity.id}">
          <input type="submit" value="报名">
        </form>
      </td>
    </tr>
  </c:forEach>
</table>
<br>
<a href="logout">退出登录</a>
</body>
</html>
