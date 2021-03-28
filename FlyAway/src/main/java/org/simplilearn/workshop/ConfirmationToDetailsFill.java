package org.simplilearn.workshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.workshop.objects.Locations;


@WebServlet("/fillDetails")
public class ConfirmationToDetailsFill extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static Locations location;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Cookie cookie = new Cookie("newID", request.getParameter("ID"));
		response.addCookie(cookie);
		Cookie cookie2[] = request.getCookies();
		int number = 0;
		for(Cookie temp : cookie2){
			if(temp.getName().equals("number")){
				number = Integer.parseInt(temp.getValue());
				break;
			}
		}
		String sql = "SELECT * FROM schedules WHERE Id = "+Integer.parseInt(request.getParameter("ID"));
		
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
			Locations newlocation = new Locations();
			while(rs.next()) {
				newlocation.setSource(rs.getString(2));
				newlocation.setDestination(rs.getString(3));
				newlocation.setAirlines(rs.getString(6));
				newlocation.setDate(rs.getString(5));
				newlocation.setRemaining(Integer.parseInt(rs.getString(4)));
				newlocation.setPrice(Integer.parseInt(rs.getString(7)));
				newlocation.setPassengers(number);
				newlocation.setFlight(rs.getString(8));
			}
			request.setAttribute("location", newlocation);
			rs.close();
			s.close();
			connection.close();
			setLocation(newlocation);
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("details.jsp").forward(request, response);
	}


	public static Locations getLocation() {
		return location;
	}


	public static void setLocation(Locations location) {
		ConfirmationToDetailsFill.location = location;
	}

}
