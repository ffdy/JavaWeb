package cn.edu.swu.ffdy.JavaWeb.Servlets;

import cn.edu.swu.ffdy.JavaWeb.Utils.DBUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("/admin/addBook")
public class AddBookServlet extends HttpServlet {

	private static final String INSERT_TEMPLATE =
			"INSERT INTO books (`name`, `author`, `price`, `type`, `describe`) " +
			"VALUES ('%s', '%s', %s, '%s', '%s')";
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("UTF-8");
		String name     = request.getParameter("name");
		String author   = request.getParameter("author");
		String price    = request.getParameter("price");
		String type     = request.getParameter("type");
		String describe = request.getParameter("describe");
		
		String sql = String.format(INSERT_TEMPLATE, name, author, price, type, describe);
		System.out.println(sql);
		
		try {
			DBUtils.insert(sql);
			response.sendRedirect("/admin/listBook");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}



