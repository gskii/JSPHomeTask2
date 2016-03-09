<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Objects" %>
<%@ page import="ru.ncedu.jsphometask.utils.RegistrationState" %><%--
  Created by IntelliJ IDEA.
  User: Gorbatovskiy
  Date: 08.03.2016
  Time: 15:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
    <link rel="stylesheet" href="/styles/style.css" type="text/css">
</head>
<body>
<%
    String login = (String) session.getAttribute("login");
    String password = (String) session.getAttribute("password");
    RegistrationState registrationState = (RegistrationState) session.getAttribute("registrationState");
%>
<h1><b>Вход в систему</b></h1>
<p>Введите ваше имя и пароль для входа в систему</p>
<form action="authentication">
    <label>Логин: </label>
    <%=login != null ?
            "<input type=\"TEXT\" name=\"login\" value=" + login + "><br>"
            :
            "<input type=\"TEXT\" name=\"login\"><br>"
    %>
    <label>Пароль: </label>
    <%= (password != null) ?
            "<input type=\"TEXT\" name=\"password\" value=" + password + "><br>"
            :
            "<input type=\"TEXT\" name=\"password\"><br>"
    %>
    <label>&nbsp;</label>
    <input type="submit" value="Отправить" formmethod="post"><br>
    <label>&nbsp;</label>
    <div class="checkbox"><input type="checkbox" name="remember" value="remember">Запомнить меня</div>
    <br>
</form>
<h3>
    <%
        String regMsg = "";
        if (registrationState != null && registrationState == RegistrationState.SUCCESS) {
            regMsg = "С успешной регистрацией";
            session.removeAttribute("registrationState");
        }
    %>
    <%=regMsg%>
</h3>
</body>
</html>
