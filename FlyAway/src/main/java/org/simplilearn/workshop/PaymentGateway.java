package org.simplilearn.workshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.workshop.objects.Locations;


@WebServlet("/payment")
public class PaymentGateway extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		boolean flag = false;
		if(request.getParameter("cardNumber").length() != 16 || request.getParameter("number").length() != 3 || request.getParameter("holder").length() < 4) {
			flag = true;
			request.setAttribute("flag", flag);
			request.getRequestDispatcher("payment.jsp").forward(request, response);
		}else {
			Locations location = ConfirmationToDetailsFill.getLocation();
			request.setAttribute("location", location);
			request.setAttribute("details", DetailsView.getDetails());
			int capicity = location.getRemaining() - location.getPassengers();
			String sql = "UPDATE schedules SET Capacity = "+ capicity +" WHERE Id = ";
			Cookie tem = new Cookie("number","");
			tem.setMaxAge(0);
			response.addCookie(tem);
			Cookie cookie[] = request.getCookies();
			for(Cookie temp : cookie) {
				if(temp.getName().equals("newID") && !temp.getValue().equals("0")) {
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
			}catch (SQLException e) {
				e.printStackTrace();
			} catch (Exception e) {
				e.printStackTrace();
			}
			request.getRequestDispatcher("paymentConfirmation.jsp").forward(request, response);
		}
	}

}
