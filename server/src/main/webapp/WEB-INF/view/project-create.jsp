<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 12.02.2019
  Time: 19:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border="1" cellpadding="10" cellspacing="5" width="700" bgcolor="#f0f8ff">
    <tr>
        <th>Name</th>
        <th>ID</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <cc:forEach items="${projectList}" var="project">
        <tr>
            <td>${project.name}</td>
            <td>${project.id}</td>
            <td><a href="/web//project/update/${project.id}">EDIT</a></td>
            <td><a href="/web//project/delete/${project.id}">DELETE</a></td>
        </tr>
    </cc:forEach>
</table>
<br/>
<br/>
<form action="${pageContext.request.contextPath}/project/create" method="post">
    <label for="myTextBox1">Enter Project Name:</label>
    <input type="text" id="myTextBox1" name="projectName"/>
    <input type="submit" value="Add Project"/>
</form>
</body>
</html>