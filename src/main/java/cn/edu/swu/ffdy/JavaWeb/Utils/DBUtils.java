package cn.edu.swu.ffdy.JavaWeb.Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBUtils {
	private static final String DB_DRIVER = "com.mysql.jdbc.Driver";
	private static final String DB_URL    = "jdbc:mysql://localhost:3307/books";
	private static final String DB_USER   = "admin";
	private static final String DB_PASS   = "123456";
	
	public static void insert(String sql) throws SQLException {
		execute(sql);
	}
	
	public static void delete(String sql) throws SQLException {
		execute(sql);
	}
	
	public static void update(String sql) throws SQLException {
		execute(sql);
	}
		
	private static void execute(String sql) throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			try (Statement statement = connection.createStatement()) {
				statement.execute(sql);
			}
		}
	}
	
	public static List<Book> getBooks(String sql) throws SQLException {
		try {
			Class.forName(DB_DRIVER);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		List<Book> books = new ArrayList<>();
		
		try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS)) {
			try (Statement statement = connection.createStatement()) {
				ResultSet rs = statement.executeQuery(sql);				
				while (rs.next()) {
					Book book = new Book();
					book.setId(rs.getInt("id"));
					book.setName(rs.getString("name"));
					book.setAuthor(rs.getString("author"));
					book.setType(rs.getString("type"));
					book.setPrice(rs.getInt("price"));
					book.setDescribe(rs.getString("describe"));					
					books.add(book);
				}
			}
		}
		
		return books;
	}

}
