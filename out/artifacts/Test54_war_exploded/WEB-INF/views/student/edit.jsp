<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>编辑学生信息</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
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
        .form-container {
            max-width: 500px;
            margin: 0 auto;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], input[type="password"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            margin-right: 10px;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-secondary {
            background-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
        }
        .error-message {
            color: red;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/admin/dashboard">首页</a>
    <a href="${pageContext.request.contextPath}/admin/students">学生管理</a>
    <a href="${pageContext.request.contextPath}/admin/logout" style="float:right">退出登录</a>
</div>

<div class="form-container">
    <h2>编辑学生信息</h2>

    <% if(request.getAttribute("msg") != null) { %>
    <div class="error-message">${msg}</div>
    <% } %>

    <form action="${pageContext.request.contextPath}/admin/student/edit/${student.id}" method="post">
        <input type="hidden" name="id" value="${student.id}">

        <div class="form-group">
            <label for="username">用户名:</label>
            <input type="text" id="username" name="username" value="${student.username}" required>
        </div>

        <div class="form-group">
            <label for="password">密码:</label>
            <input type="password" id="password" name="password" value="${student.password}" required>
        </div>

        <div class="form-group">
            <label for="name">姓名:</label>
            <input type="text" id="name" name="name" value="${student.name}" required>
        </div>

        <div class="form-group">
            <label for="student_id">学号:</label>
            <input type="text" id="student_id" name="student_id" value="${student.student_id}" required>
        </div>

        <div class="form-group">
            <label for="class_name">班级:</label>
            <input type="text" id="class_name" name="class_name" value="${student.class_name}" required>
        </div>

        <button type="submit" class="btn">保存</button>
        <a href="${pageContext.request.contextPath}/admin/students" class="btn btn-secondary">取消</a>
    </form>
</div>
</body>
</html>
