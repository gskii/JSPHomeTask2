<%@ page import="ru.ncedu.jsphometask.accounts.Account" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Arrays" %><%--
  Created by IntelliJ IDEA.
  User: Gorbatovskiy
  Date: 08.03.2016
  Time: 10:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Hello page</title>
    <link rel="stylesheet" href="../styles/style.css" type="text/css">
</head>
<body>
<%
    boolean authorized = false;
    for (Cookie cookie : request.getCookies()) {
        if ("authorized".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
            authorized = true;
        }
    }
    if (!authorized) response.sendRedirect("/login");
    String login = (String) session.getAttribute("login");
    if (login == null) {
        login = "%Некто, кто слишком любит конфиденциальность%";
    }
%>

<h1>Hello, <%=login%>
</h1>
<form action="goodbye" method="post">
    <label>Форма логина:</label>
    <input type="submit" value="Забыть меня"><br>
</form>
</body>
</html>
