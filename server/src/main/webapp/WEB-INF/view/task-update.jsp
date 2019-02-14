<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
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

<form:form action="/task/update" modelAttribute="task">
    <tr>
        <td>
            <form:label path="id">
                <spring:message text="ID"/>
            </form:label>
        </td>
        <td>
            <form:input path="id" readonly="true" size="36"/>
        </td>
    </tr>
    <br/>
    <tr>
        <td>
            <form:label path="name">
                <spring:message text="Name"/>
            </form:label>
        </td>
        <td>
            <form:input path="name"/>
        </td>
    </tr>
    <br/>
    <tr>
        <td>
            <form:label path="created">
                <spring:message text="Created"/>
            </form:label>
        </td>
        <td>
            <form:input path="created"/>
        </td>
    </tr>
    <br/>
    <tr>
        <td>
            <form:label path="userId">
                <spring:message text="UserId"/>
            </form:label>
        </td>
        <td>
            <form:input path="userId"/>
        </td>
    </tr>
    <tr>
        <td>
            <form:label path="projectId">
                <spring:message text="projectId"/>
            </form:label>
        </td>
        <td>
            <form:input path="projectId"/>
        </td>
    </tr>
    <br/>
    <tr>
        <td>
            <input type="submit" value="<spring:message text="Edit Task"/>"/>
        </td>
    </tr>
</form:form>
<a href="http://localhost:8080/web/task/menutask">Bask to task menu</a>
</body>
</html>
