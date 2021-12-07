package cn.edu.swu.ffdy.JavaWeb.Servlets;

import cn.edu.swu.ffdy.JavaWeb.Utils.SessionContext;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet  extends HttpServlet {

    private static final long serialVersionUID = 1198763434511986380L;

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("UTF-8");
        String user = request.getParameter("user");
        String pass = request.getParameter("pass");

        HttpSession session = request.getSession(true);
        if (user != null && pass != null) {
            if (user.equals("admin") && pass.equals("123456")) {
                session.setAttribute(SessionContext.LOGIN_STATUS, Boolean.TRUE);
                response.sendRedirect("/admin/listBook");
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }
}
