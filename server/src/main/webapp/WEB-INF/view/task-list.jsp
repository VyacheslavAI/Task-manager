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
<cc:forEach items="${taskList}" var="task">
    <tr>
        <td>${task.name}</td>
    </tr>
</cc:forEach>
<a href="http://localhost:8080/web/task/menutask">Bask to task menu</a>
</body>
</html>
