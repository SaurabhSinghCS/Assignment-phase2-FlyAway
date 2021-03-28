package org.simplilearn.workshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



@WebServlet("/admin")
public class Admin extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static boolean isValid = false;
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie[] = request.getCookies();
		boolean flag = true;
		if(cookie != null) {
			for(Cookie temp : cookie) {
				if(temp.getName().equals("ID") && !temp.getValue().equals("")) {
					String sql = "SELECT * FROM loginadmin where ID = "+Integer.parseInt(temp.getValue());
					sendToAdminHome(request, response, sql);
					flag = false;
				}
			}
		}
		if(flag) {
			isValid = false;
			sendToAdminLogin(request,response);
		}
	}
	

	
	private void sendToAdminLogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("isValid", isValid);
		RequestDispatcher rd = request.getRequestDispatcher("admin/adminLoginPage.jsp");
		rd.forward(request, response);
	}
	
	



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String sql = "SELECT * FROM loginadmin where UserName='"+
				request.getParameter("username")+"' and Password='"+request.getParameter("password")+"'";
		sendToAdminHome(request,response,sql);
		
	}



	private void sendToAdminHome(HttpServletRequest request, HttpServletResponse response, String sql) {
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
			ResultSet rs = s.executeQuery(sql);
			if(!rs.next()) {
				isValid = true;
				sendToAdminLogin(request, response);
			}else {
				isValid = false;
				
				// adding Attributes to request
				request.setAttribute("FirstName", rs.getString(2));
				request.setAttribute("LastName", rs.getString(3));
				
				// cookies Declaration
				Cookie cookie = new Cookie("ID", ""+rs.getInt(1));
				response.addCookie(cookie);
				RequestDispatcher rd = request.getRequestDispatcher("admin/adminHome.jsp");
				rd.forward(request, response);
			}
			rs.close();
			s.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
