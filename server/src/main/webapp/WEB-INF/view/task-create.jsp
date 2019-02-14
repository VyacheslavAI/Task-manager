<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
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
            <td><a href="/task/delete/${task.id}">DELETE</a></td>
        </tr>
    </cc:forEach>
</table>

<form action="${pageContext.request.contextPath}/task/create" method="post">
    <label for="myTextBox1">
        Enter Task Name:</label>
    <input type="text" id="myTextBox1" name="taskName"/>
    <input type="text" id="myTextBox2" name="projectId" value="${project.id}" hidden="hidden"/>
    <p></p>
    <input type="submit" value="Add Task"/>
</form>
<a href="http://localhost:8080/task/list">Bask to task menu</a>
</body>
</html>
