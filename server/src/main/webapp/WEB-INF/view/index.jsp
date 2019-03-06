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
<h1>Hello</h1>
<h2>${message}</h2>

<form action="/logout" method="post">
    <input value="Logout" type="submit"/>
</form>
</body>
</html>
