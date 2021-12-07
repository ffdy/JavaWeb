package cn.edu.swu.ffdy.JavaWeb.Servlets;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

        if (user != null && pass != null) {
            if (user.equals("admin") && pass.equals("123456")) {
                response.sendRedirect("/admin/listBook");
            } else {
                response.sendRedirect("/login.html");
            }
        }
    }
}