package ru.ncedu.jsphometask.servlets;

import ru.ncedu.jsphometask.accounts.Account;
import ru.ncedu.jsphometask.utils.AccountManager;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Gorbatovskiy on 08.03.2016.
 */
public class AuthenticationServlet extends HttpServlet {
    private AccountManager accounts;

    public AuthenticationServlet() {
        accounts = AccountManager.INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        String login = (String) req.getParameter("login");
        String password = (String) req.getParameter("password");
        String remember = (String) req.getParameter("remember");
        if (login != null && password != null && !password.equals("")) {
            Account account = new Account(login, password);
            if (!accounts.isExists(account)) {
                resp.sendRedirect("/registration");
                return;
            }
            if (!accounts.validate(account)) {
                resp.sendRedirect("/oops");
                return;
            }
            Cookie cookie = new Cookie("authorized", "true");
            cookie.setPath("/myPath");
            cookie.setDomain("mydomain.com");
            cookie.setMaxAge(60 * 60 * 24);
            resp.addCookie(cookie);
            if (remember != null) {
                session.setAttribute("login", login);
                session.setAttribute("password", password);
            }
            resp.sendRedirect("/hello");
        } else {
            boolean authorized = false;
            for (Cookie cookie : req.getCookies()) {
                if ("authorized".equals(cookie.getName()) && "true".equals(cookie.getValue())) {
                    authorized = true;
                }
            }
            if (authorized) {
                resp.sendRedirect("/hello");
            } else {
                resp.sendRedirect("/registration");
            }
        }
    }
}
