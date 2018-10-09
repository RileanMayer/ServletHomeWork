package com.epam.lab;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CoolServlet extends HttpServlet {
    private String innerState = "";
    Cookie cookie = new Cookie("counter", "");

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        boolean ajax = "XMLHttpRequest".equals(req.getHeader("X-Requested-With"));
        innerState = "GET";
        if (ajax) {
            resp.getWriter().print("State:" + innerState);

        } else {
            RequestDispatcher rd = req.getRequestDispatcher("WEB-INF\\jsp\\index.jsp");
            rd.forward(req, resp);
        }
        veiwsCounterIntrementer(req, resp);
    }

    private void veiwsCounterIntrementer(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Cookie[] requestCookies = req.getCookies();
        Cookie counterCookie = null;
        for(Cookie cookie: requestCookies) {
            if(cookie.getName().equals("counter")) {
                counterCookie = cookie;
                break;
            }
        }

        if(counterCookie != null) {
            counterCookie.setValue(String.valueOf(Integer.parseInt(counterCookie.getValue()) + 1));
        } else {
            counterCookie = new Cookie("counter", "1");
        }

        resp.addCookie(counterCookie);
        resp.getWriter().write(" Views counter: " + counterCookie.getValue());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        innerState = "POST";
        resp.getWriter().print("State:" + innerState);
        veiwsCounterIntrementer(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        innerState = "DELETE";
        resp.getWriter().print("State:" + innerState);
        veiwsCounterIntrementer(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        innerState = "PUT";
        resp.getWriter().print("State:" + innerState);
        veiwsCounterIntrementer(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
        System.out.println("destroy method");
    }
}
