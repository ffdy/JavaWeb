package cn.edu.swu.ffdy.JavaWeb.Servlets;


import cn.edu.swu.ffdy.JavaWeb.Utils.Book;
import cn.edu.swu.ffdy.JavaWeb.Utils.DBUtils;
import cn.edu.swu.ffdy.JavaWeb.Utils.PageUtils;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/admin/listBook")
public class ListBookServlet extends HttpServlet {

	private static final long serialVersionUID = 1198763442711986380L;
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		this.doPost(request, response);
	}
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String key = request.getParameter("name");

		String sql;
		if (key != null && !key.isEmpty()) {
			sql = "SELECT * FROM `books` WHERE" +
					" `name` LIKE '%" + key + "%'" +
					" OR `describe` LIKE '%" + key + "%'" +
					" OR `author` = '" + key + "'";
		} else {
			sql = "SELECT * FROM `books` ORDER BY `id` DESC LIMIT 6";
		}

		try {			
			List<Book> books = DBUtils.getBooks(sql);
			response.setCharacterEncoding("UTF-8");
			try (Writer writer = response.getWriter()) {
				String sb = PageUtils.getAdminHeader() +
						"<center><form action='/admin/listBook' method='GET'>" +
						"<input type='text' name='name'/>&nbsp;&nbsp;&nbsp;" +
						"<input type='submit' value='查询'/>" +
						"</form></center>" +
						toHtmlTable(books) +
						PageUtils.getEnd();
				writer.write(sb);
			}			
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	private String toHtmlTable(List<Book> books) {
		StringBuilder sb = new StringBuilder();
		sb.append("<center><br><table style='width:70%' border='0' cellpadding='10'>");
		
		sb.append("<tr style='background-color:#336699;color:#ffffff'>" +
				"<th>编号</th><th>书名</th><th>作者</th><th>类型</th><th>价格</th><th>描述</th>" +
				"<th></th><th></th></tr>");
		for (int i=0; i<books.size(); i++) {
			Book book = books.get(i);
			if (i % 2 == 0) {
				sb.append("<tr style='background-color:#cdcdcd'>");
			} else {
				sb.append("<tr style='background-color:#efefef'>");
			}
			sb.append("<td>").append(book.getId()).append("</td>");
			sb.append("<td><b>").append(book.getName()).append("</b></td>");
			sb.append("<td>").append(book.getAuthor()).append("</td>");
			sb.append("<td>").append(book.getType()).append("</td>");
			sb.append("<td>").append(book.getPrice()).append("</td>");
			sb.append("<td>").append(book.getDescribe()).append("</td>");
			sb.append("<td>").append(
//					当前已经在/admin路径下
					String.format("<a href='/admin/editBook?id=%s'>修改</a>", book.getId())
			).append("</td>");
			sb.append("<td>").append(
					String.format("<a href='/admin/deleteBook?id=%s'>删除</a>", book.getId())
			).append("</td>");			
			sb.append("</tr>");
		}		
		sb.append("</table></center>");
		return sb.toString();
	}
	
}
