<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/4/29
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>校园活动管理系统</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .container {
            background-color: white;
            padding: 30px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 400px;
            text-align: center;
        }
        h1 {
            color: #333;
            margin-bottom: 30px;
        }
        .role-buttons {
            display: flex;
            flex-direction: column;
            gap: 15px;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 12px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
            text-decoration: none;
            display: block;
        }
        .btn:hover {
            background-color: #45a049;
        }
        .btn-admin {
            background-color: #2196F3;
        }
        .btn-admin:hover {
            background-color: #0b7dda;
        }
        .btn-organizer {
            background-color: #ff9800;
        }
        .btn-organizer:hover {
            background-color: #e68a00;
        }
    </style>
</head>
<body>
<div class="container">
    <h1>校园活动管理系统</h1>
    <div class="role-buttons">
        <a href="${pageContext.request.contextPath}/student/login" class="btn">学生登录</a>
        <a href="${pageContext.request.contextPath}/admin/login" class="btn btn-admin">管理员登录</a>
        <a href="${pageContext.request.contextPath}/organizer/login" class="btn btn-organizer">组织者登录</a>
    </div>
</div>
</body>
</html>