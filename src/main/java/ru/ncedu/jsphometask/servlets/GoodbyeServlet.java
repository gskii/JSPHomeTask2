package ru.ncedu.jsphometask.servlets;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.io.IOException;

/**
 * Created by Gorbatovskiy on 09.03.2016.
 */
public class GoodbyeServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect("/login");
        for (Cookie cookie : req.getCookies()) {
            // Все равно не смог удалить cookie...
            if ("authorized".equals(cookie.getName())) {
                System.out.println("coockie!");
                cookie.setValue("false");
                cookie.setPath("/badPath");
                cookie.setDomain("baddomain.com");
                cookie.setMaxAge(0);
                resp.addCookie(cookie);
            }
        }
    }
}
