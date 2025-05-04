<%--
  Created by IntelliJ IDEA.
  User: LCW69
  Date: 2025/5/3
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>学生列表</title>
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
        .student-list {
            width: 100%;
            border-collapse: collapse;
        }
        .student-list th, .student-list td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: left;
        }
        .student-list tr:nth-child(even) {
            background-color: #f2f2f2;
        }
        .student-list th {
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
</head>
<body>
<div class="nav-menu">
    <a href="${pageContext.request.contextPath}/admin/dashboard">首页</a>
    <a href="${pageContext.request.contextPath}/admin/students" class="active">学生管理</a>
    <a href="${pageContext.request.contextPath}/admin/logout" style="float:right">退出登录</a>
</div>

<div class="header">
    <h1>学生列表</h1>
</div>

<table class="student-list">
    <tr>
        <th>ID</th>
        <th>用户名</th>
        <th>姓名</th>
        <th>学号</th>
        <th>班级</th>
        <th>操作</th>
    </tr>
    <c:forEach items="${students}" var="student">
        <tr>
            <td>${student.id}</td>
            <td>${student.username}</td>
            <td>${student.name}</td>
            <td>${student.student_id}</td>
            <td>${student.class_name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/admin/student/view/${student.id}" class="btn">查看</a>
                <a href="${pageContext.request.contextPath}/admin/student/edit/${student.id}" class="btn">编辑</a>
                <a href="javascript:void(0);" onclick="deleteStudent(${student.id})" class="btn btn-danger">删除</a>
            </td>
        </tr>
    </c:forEach>
</table>

<script>
    function deleteStudent(studentId) {
        if (confirm('确定要删除该学生吗？此操作不可恢复！')) {
            fetch('${pageContext.request.contextPath}/admin/student/delete/' + studentId, {
                method: 'POST'
            })
                .then(response => response.text())
                .then(data => {
                    if (data === 'success') {
                        alert('删除成功！');
                        location.reload();
                    } else {
                        alert('删除失败，请稍后再试！');
                    }
                });
        }
    }
</script>
</body>
</html>
