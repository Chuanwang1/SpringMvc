<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 19:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>学生信息</title>
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
        .student-info {
            max-width: 500px;
            margin: 0 auto;
            background-color: #f9f9f9;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .info-item {
            margin-bottom: 15px;
        }
        .info-label {
            font-weight: bold;
            display: inline-block;
            width: 100px;
        }
        .btn {
            background-color: #4CAF50;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            text-decoration: none;
            display: inline-block;
            margin-top: 20px;
        }
        .btn:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/admin/dashboard">首页</a>
    <a href="${pageContext.request.contextPath}/admin/students">学生管理</a>
    <a href="${pageContext.request.contextPath}/admin/logout" style="float:right">退出登录</a>
</div>

<div class="student-info">
    <h2>学生信息</h2>

    <div class="info-item">
        <span class="info-label">ID:</span>
        <span>${student.id}</span>
    </div>

    <div class="info-item">
        <span class="info-label">用户名:</span>
        <span>${student.username}</span>
    </div>

    <div class="info-item">
        <span class="info-label">姓名:</span>
        <span>${student.name}</span>
    </div>

    <div class="info-item">
        <span class="info-label">学号:</span>
        <span>${student.student_id}</span>
    </div>

    <div class="info-item">
        <span class="info-label">班级:</span>
        <span>${student.class_name}</span>
    </div>

    <a href="${pageContext.request.contextPath}/admin/students" class="btn">返回列表</a>
</div>
</body>
</html>
