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
<style type="text/css">
    .simple-little-table {
        font-family:Arial, Helvetica, sans-serif;
        color:#666;
        font-size:12px;
        text-shadow: 1px 1px 0px #fff;
        background:#eaebec;
        margin:20px;
        border:#ccc 1px solid;
        border-collapse:separate;

        -moz-border-radius:3px;
        -webkit-border-radius:3px;
        border-radius:3px;

        -moz-box-shadow: 0 1px 2px #d1d1d1;
        -webkit-box-shadow: 0 1px 2px #d1d1d1;
        box-shadow: 0 1px 2px #d1d1d1;
    }

    .simple-little-table th {
        font-weight:bold;
        padding:21px 25px 22px 25px;
        border-top:1px solid #fafafa;
        border-bottom:1px solid #e0e0e0;

        background: #ededed;
        background: -webkit-gradient(linear, left top, left bottom, from(#ededed), to(#ebebeb));
        background: -moz-linear-gradient(top,  #ededed,  #ebebeb);
    }
    .simple-little-table th:first-child{
        text-align: left;
        padding-left:20px;
    }
    .simple-little-table tr:first-child th:first-child{
        -moz-border-radius-topleft:3px;
        -webkit-border-top-left-radius:3px;
        border-top-left-radius:3px;
    }
    .simple-little-table tr:first-child th:last-child{
        -moz-border-radius-topright:3px;
        -webkit-border-top-right-radius:3px;
        border-top-right-radius:3px;
    }
    .simple-little-table tr{
        text-align: center;
        padding-left:20px;
    }
    .simple-little-table tr td:first-child{
        text-align: left;
        padding-left:20px;
        border-left: 0;
    }
    .simple-little-table tr td {
        padding:18px;
        border-top: 1px solid #ffffff;
        border-bottom:1px solid #e0e0e0;
        border-left: 1px solid #e0e0e0;

        background: #fafafa;
        background: -webkit-gradient(linear, left top, left bottom, from(#fbfbfb), to(#fafafa));
        background: -moz-linear-gradient(top,  #fbfbfb,  #fafafa);
    }
    .simple-little-table tr:nth-child(even) td{
        background: #f6f6f6;
        background: -webkit-gradient(linear, left top, left bottom, from(#f8f8f8), to(#f6f6f6));
        background: -moz-linear-gradient(top,  #f8f8f8,  #f6f6f6);
    }
    .simple-little-table tr:last-child td{
        border-bottom:0;
    }
    .simple-little-table tr:last-child td:first-child{
        -moz-border-radius-bottomleft:3px;
        -webkit-border-bottom-left-radius:3px;
        border-bottom-left-radius:3px;
    }
    .simple-little-table tr:last-child td:last-child{
        -moz-border-radius-bottomright:3px;
        -webkit-border-bottom-right-radius:3px;
        border-bottom-right-radius:3px;
    }
    .simple-little-table tr:hover td{
        background: #f2f2f2;
        background: -webkit-gradient(linear, left top, left bottom, from(#f2f2f2), to(#f0f0f0));
        background: -moz-linear-gradient(top,  #f2f2f2,  #f0f0f0);
    }

    .simple-little-table a:link {
        color: #666;
        font-weight: bold;
        text-decoration:none;
    }
    .simple-little-table a:visited {
        color: #999999;
        font-weight:bold;
        text-decoration:none;
    }
    .simple-little-table a:active,
    .simple-little-table a:hover {
        color: #bd5a35;
        text-decoration:underline;
    }
</style>
<body>
<h1>Project List</h1>
<br/>
<table border="1" cellpadding="10" cellspacing="5" width="1850" bgcolor="#f0f8ff" class="simple-little-table">
    <tr>
        <th>Name</th>
        <th>ID</th>
        <th>Created</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>Project Task</th>
    </tr>
    <cc:forEach items="${projectList}" var="project">
        <tr>
            <td>${project.name}</td>
            <td>${project.id}</td>
            <td>${project.created}</td>
            <td><a href="/project/update/${project.id}">EDIT</a></td>
            <td><a href="/project/delete/${project.id}">DELETE</a></td>
            <td><a href="/task/list/${project.id}">SHOW TASKS</a></td>
        </tr>
    </cc:forEach>
</table>
<h3><a href="${pageContext.request.contextPath}/project/add">Add New Project</a></h3>
</body>
</html>