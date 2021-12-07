package cn.edu.swu.ffdy.JavaWeb.Servlets;

import cn.edu.swu.ffdy.JavaWeb.Utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/deleteBook")
public class DeleteBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1198763442666686380L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String sql = "DELETE FROM books WHERE id=" + id;
		
		try {
			DBUtils.delete(sql);
			response.sendRedirect("/admin/listBook");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
