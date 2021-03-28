package org.simplilearn.workshop;


import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




@WebServlet("/changePassword")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static boolean confirmation = false;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("comes");
		
		request.setAttribute("confirmation", confirmation);
		RequestDispatcher rd = request.getRequestDispatcher("admin/passwordChange.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		// storing both passwords in different strings
		String s1 = request.getParameter("password1");
		String s2 = request.getParameter("password2");
		
		// checking whether they are equal or not
		if(!s1.equals(s2)) {
			confirmation = true;
			request.setAttribute("confirmation", confirmation);
			RequestDispatcher rd = request.getRequestDispatcher("admin/passwordChange.jsp");
			rd.forward(request, response);
		}
		String sql = "UPDATE loginadmin SET Password = '"+ s1 +"' WHERE Id = ";
		Cookie cookie[] = request.getCookies();
		for(Cookie temp : cookie) {
			if(temp.getName().equals("ID") && !temp.getValue().equals("0")) {
				sql += Integer.parseInt(temp.getValue());
			}
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			System.out.println("JDBC driver loaded");
			
			
			String url = "jdbc:mysql://localhost:3306/flyaway?useSSL=false";
			String user = "root";
			String pass = "1234567890";
			
			// step 2: obtain a connection
			Connection connection = DriverManager.getConnection(url, user, pass);
			System.out.println("got connection");
			
			Statement s = connection.createStatement();
			s.executeUpdate(sql);
			Cookie cookie1 = new Cookie("ID", "");
			cookie1.setMaxAge(0);
			response.addCookie(cookie1);
			s.close();
			connection.close();
			RequestDispatcher rd = request.getRequestDispatcher("admin/confirmation.html");
			rd.forward(request, response);
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}


}
