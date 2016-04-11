<%--
  Created by IntelliJ IDEA.
  User: andrew
  Date: 11.04.16
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<%@ page import="org.springframework.security.core.Authentication" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<html>
<head>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/angularjs/1.4.2/angular.min.js"></script>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script type="text/javascript" src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
    <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
    <link rel="stylesheet" href="resources/css/todolist.css">


    <script type="text/javascript" src="resources/js/app/tasks/tasks.module.js"></script>
    <script type="text/javascript" src="resources/js/app/tasks/tasks.controller.js"></script>

    <script type="text/javascript" src="resources/js/app/users/users.module.js"></script>
    <script type="text/javascript" src="resources/js/app/users/users.controller.js"></script>

    <script type="text/javascript" src="resources/app.module.js"></script>

    <title></title>
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <a href="#" class="navbar-brand">Todo List</a>

            <ul class="nav navbar-nav navbar">
                <li class="active"><a href="#">Dashboard</a></li>
                <li><a href="#">Charts</a></li>
                <li><a href="#">Admin</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">
                    <% Authentication auth = SecurityContextHolder.getContext().getAuthentication();
                        String userName = auth.getName();
                        pageContext.getOut().write("I'm " + userName);
                    %>
                    <span class="glyphicon glyphicon-user" aria-hidden="true"></span>
                </a></li>
                <li><a href="/todoList/logoff"><span class="glyphicon glyphicon-log-out" aria-hidden="true"></span></a>
                </li>
            </ul>
        </div>
    </div>



</nav>
<div ng-app="taskListApp" class="container main-container">
    <row>
        <div id="tasklist" class="col-lg-9">
            <jsp:include page="app/tasks/tasks.view.jsp"/>
        </div>
        <div id="userlist" class="col-lg-3">
            <jsp:include page="app/users/users.view.jsp"/>
        </div>
    </row>
</div>
</body>
</html>