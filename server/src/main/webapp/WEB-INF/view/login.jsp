<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: va.ivanov
  Date: 06.03.2019
  Time: 14:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Slava</title>
</head>
<body>
<h1>Spring MVC + Security</h1>
<h4>Login Form</h4>

<form action='<spring:url value="/loginAction"/>' method="post">
    <table>
        <tr>
            <td>Username</td>
            <td>
                <input type="text" name="username"/>
            </td>
        </tr>
        <tr>
            <td>Password</td>
            <td>
                <input type="password" name="password"/>
            </td>
        </tr>
        <tr>
            <td>
                <button type="submit">Login</button>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
