package cn.edu.swu.ffdy.JavaWeb.Servlets;

import cn.edu.swu.ffdy.JavaWeb.Utils.SessionContext;
import cn.edu.swu.ffdy.JavaWeb.Utils.ValidateCode;

import javax.imageio.ImageIO;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;

@WebServlet("/validateCode.png")
public class ValidateCodeServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) {
        response.setContentType("image/png");
        HttpSession session = request.getSession(true);

        final String Code = ValidateCode.getCodeNum();
        session.setAttribute(SessionContext.LOGIN_VALIDATE_CODE, Code);
        try (OutputStream out = response.getOutputStream()) {
            ImageIO.write(ValidateCode.getCodePic(Code), "png", out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
