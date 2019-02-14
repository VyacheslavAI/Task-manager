<%--
  Created by IntelliJ IDEA.
  User: DNS
  Date: 13.02.2019
  Time: 10:46
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/authentication" method="post">
    <label for="myTextBox1">
        Enter Login:</label>
    <input type="text" id="myTextBox1" name="login"/>
    <p></p>
    <label for="myTextBox2">
        Enter Password:</label>
    <input type="text" id="myTextBox2" name="password"/>
    <p></p>
    <input type="submit" value="submit"/>
</form>
</body>
</html>
