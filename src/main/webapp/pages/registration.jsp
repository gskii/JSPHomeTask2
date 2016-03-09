<%@ page import="ru.ncedu.jsphometask.utils.RegistrationState" %><%--
  Created by IntelliJ IDEA.
  User: Gorbatovskiy
  Date: 08.03.2016
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="/styles/style.css" type="text/css">
</head>
<body>
<h1><b>Регистрация в системе</b></h1>
<p>Введите имя и пароль для регистрации в системе</p>
<form action="register">
    <label>Логин: </label>
    <input type="TEXT" name="login" required><br>
    <label>Пароль: </label>
    <input type="PASSWORD" name="password" required><br>
    <label>&nbsp;</label>
    <input type="submit" value="Создать" formmethod="post"><br>
    <br>
</form>
<h3>
    <%
        String regMsg = "";
        RegistrationState registrationState = (RegistrationState) session.getAttribute("registrationState");
        if (registrationState != null && registrationState == RegistrationState.FAILED) {
            regMsg = "Данный логин уже занят";
            session.removeAttribute("registrationState");
        }
    %>
    <%= regMsg %>
</h3>
</body>
</html>
