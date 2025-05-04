<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>编辑活动</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        .form-container {
            max-width: 600px;
            margin: 0 auto;
        }
        .form-group {
            margin-bottom: 15px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            font-weight: bold;
        }
        input[type="text"], textarea, input[type="datetime-local"] {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
            box-sizing: border-box;
        }
        textarea {
            height: 100px;
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
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/organizer/dashboard">我的活动</a>
    <a href="${pageContext.request.contextPath}/organizer/activity/create">创建活动</a>
    <a href="${pageContext.request.contextPath}/organizer/logout" style="float:right">退出登录</a>
</div>

<div class="form-container">
    <h1>编辑活动</h1>

    <form action="${pageContext.request.contextPath}/organizer/activity/edit/${activity.id}" method="post">
        <div class="form-group">
            <label for="title">活动名称:</label>
            <input type="text" id="title" name="title" value="${activity.title}" required>
        </div>

        <div class="form-group">
            <label for="description">活动描述:</label>
            <textarea id="description" name="description" required>${activity.description}</textarea>
        </div>

        <div class="form-group">
            <label for="time">活动时间:</label>
            <input type="datetime-local" id="time" name="time" value="${activity.time}" required>
        </div>

        <div class="form-group">
            <label for="location">活动地点:</label>
            <input type="text" id="location" name="location" value="${activity.location}" required>
        </div>

        <button type="submit" class="btn">保存修改</button>
        <a href="${pageContext.request.contextPath}/organizer/dashboard" class="btn btn-secondary">取消</a>
    </form>
</div>
</body>
</html>
