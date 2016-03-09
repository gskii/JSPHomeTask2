package ru.ncedu.jsphometask.servlets;

import ru.ncedu.jsphometask.accounts.Account;
import ru.ncedu.jsphometask.utils.AccountManager;
import ru.ncedu.jsphometask.utils.RegistrationState;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Gorbatovskiy on 08.03.2016.
 */
public class RegistrationServlet extends HttpServlet {
    private AccountManager accounts;

    public RegistrationServlet() {
        accounts = AccountManager.INSTANCE;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        if (login != null && password != null) {
            if (accounts.isExists(login)) {
                session.setAttribute("registrationState", RegistrationState.FAILED);
                resp.sendRedirect("/registration");
            } else {
                accounts.create(new Account(login, password));
                session.setAttribute("registrationState", RegistrationState.SUCCESS);
                resp.sendRedirect("/login");
            }
        }
    }
}
