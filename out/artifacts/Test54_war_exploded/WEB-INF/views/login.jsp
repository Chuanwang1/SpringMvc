<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/4/29
  Time: 10:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
  <title>登录⻚⾯</title>

</head>
<body>
<h2>校园活动管理系统登录</h2>
<form action="login" method="post">
  ⽤户名：<input type="text" name="username" required><br><br>
  密码：<input type="password" name="password" required><br><br>
  <select name="role">
    <option value="student">学⽣</option>
    <option value="organizer">社团负责⼈</option>
    <option value="admin">管理员</option>
  </select><br><br>
  <input type="submit" value="登录">
</form>
<c:if test="${not empty errorMessage}">
  <p style="color:red;">${errorMessage}</p>
</c:if>
</body>
</html>
