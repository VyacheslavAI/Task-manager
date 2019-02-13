<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%--

  Created by IntelliJ IDEA.
  User: DNS
  Date: 12.02.2019
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Project List</h1>
<br/>
<table border="1" cellpadding="10" cellspacing="5" width="700" bgcolor="#f0f8ff">
    <tr>
        <th>Name</th>
        <th>ID</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <cc:forEach items="${projectlist}" var="project">
        <tr>
            <td>${project.name}</td>
            <td>${project.id}</td>
            <td><a href="/web//project/update/${project.id}">EDIT</a></td>
            <td><a href="/web//project/delete/${project.id}">DELETE</a></td>
        </tr>
    </cc:forEach>
</table>
<h3><a href="${pageContext.request.contextPath}/web//project/add">Add New Project</a></h3>
<br/>
<a href="${pageContext.request.contextPath}/web//project/menuproject">Back to project menu</a>
</body>
</html>
