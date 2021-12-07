package cn.edu.swu.ffdy.JavaWeb;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1198763442666686380L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String id = request.getParameter("id");
		String sql = "DELETE FROM books WHERE id=" + id;
		
		try {
			cn.edu.swu.ffdy.JavaWeb.DBUtils.delete(sql);
			response.sendRedirect("./listBook");			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
