<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%@ page import="ru.ivanov.todoproject.entity.Project" %>
<%@taglib prefix="cc" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.List" %>
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
     <cc:forEach items="${list}" var="project">
         <tr>
             <td>${project.name}</td>
         </tr>
     </cc:forEach>
 </table>
</body>
</html>
