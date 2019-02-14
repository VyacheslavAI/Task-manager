<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 13.02.2019
  Time: 14:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Task list by ${project.name} project</h1>
<br/>
<table border="1" cellpadding="10" cellspacing="5" width="700" bgcolor="#f0f8ff">
    <tr>
        <th>Name</th>
        <th>ID</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <cc:forEach items="${taskList}" var="task">
        <tr>
            <td>${task.name}</td>
            <td>${task.id}</td>
            <td><a href="/task/update/${project.id}/${task.id}">EDIT</a></td>
            <td><a href="/task/delete/${project.id}/${task.id}">DELETE</a></td>
        </tr>
    </cc:forEach>
</table>
<h3><a href="${pageContext.request.contextPath}/task/add/${project.id}">Add New Task</a></h3>
<br/>
<h3><a href="${pageContext.request.contextPath}/project/list">Back to project menu</a></h3>
</body>
</html>