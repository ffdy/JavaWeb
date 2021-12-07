package cn.edu.swu.ffdy.JavaWeb.Filters;

import cn.edu.swu.ffdy.JavaWeb.Utils.SessionContext;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/admin/*")
public class AuthFilter extends HttpFilter {
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
        throws IOException, ServletException {
        HttpSession session = request.getSession(true);
        Boolean isLogin = (Boolean) session.getAttribute(SessionContext.LOGIN_STATUS);

        if(isLogin == null || isLogin.equals(Boolean.FALSE)) {
            response.sendRedirect("/login.html");
        } else {
            chain.doFilter(request, response);
        }
    }
}