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
<form action="${pageContext.request.contextPath}/web/task/create" method="post">
    <label for="myTextBox1">
        Enter Project Name:</label>
    <input type="text" id="myTextBox1" name="projectName"/>
    <p></p>
    <label for="myTextBox1">
        Enter Task Name:</label>
    <input type="text" id="myTextBox2" name="taskName"/>
    <p></p>
    <input type="submit" value="submit"/>
</form>
<a href="http://localhost:8080/web/task/menutask">Bask to task menu</a>
</body>
</html>
