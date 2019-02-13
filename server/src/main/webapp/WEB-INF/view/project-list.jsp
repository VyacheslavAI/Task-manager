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
<table>
    <cc:forEach items="${projectlist}" var="project">
        <tr>
            <td>${project.name}</td>
        </tr>
    </cc:forEach>
    <a href="${pageContext.request.contextPath}/web//project/menuproject">Back to project menu</a>
</table>
</body>
</html>
