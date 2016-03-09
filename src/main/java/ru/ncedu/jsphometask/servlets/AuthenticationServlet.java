package ru.ncedu.jsphometask.servlets;

import ru.ncedu.jsphometask.utils.AccountManager;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
        if (login != null && password != null) {
            if (!accounts.isExists(login)) {
                resp.sendRedirect("/registration");
                return;
            }
            if (!accounts.validate(login, password)) {
                resp.sendRedirect("/oops");
                return;
            }
            session.setAttribute("authorized", Boolean.TRUE);
            if (remember != null) {
                session.setAttribute("login", login);
                session.setAttribute("password", password);
            }
            resp.sendRedirect("/hello");
        }
    }
}
