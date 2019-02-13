<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 12.02.2019
  Time: 19:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Update</title>
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
<br/>
<form:form action="/web/project/update" modelAttribute="project">
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
    <br/>
    <tr>
        <td>
            <input type="submit" value="<spring:message text="Edit Project"/>"/>
        </td>
    </tr>
</form:form>
</body>
</html>