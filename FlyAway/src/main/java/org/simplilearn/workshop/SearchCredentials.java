package org.simplilearn.workshop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.simplilearn.workshop.objects.Locations;


@WebServlet("/searchCredentials")
public class SearchCredentials extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Locations> locations = new ArrayList<Locations>();
		
		String sql = "SELECT * FROM schedules";
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
			while(rs.next()) {
				Locations location = new Locations();
				location.setSource(rs.getString(2));
				location.setDestination(rs.getString(3));
				locations.add(location);
			}
			rs.close();
			s.close();
			connection.close();
		}catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("locations", locations);
		
		RequestDispatcher rd = request.getRequestDispatcher("searchPage.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String source = request.getParameter("source");
		String destination = request.getParameter("destination");
		System.out.println(request.getParameter("date"));
		int number = Integer.parseInt(request.getParameter("number"));
		
		List<Locations> locations = new ArrayList<Locations>();
		
		String sql = "SELECT * FROM schedules WHERE Source='"+source+"' AND Destination='"+
				destination+"' AND Date='"+request.getParameter("date")+"'";
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
			boolean valid = false;
			while(rs.next()) {
				valid = true;
				Locations location = new Locations();
				location.setId(Integer.parseInt(rs.getString(1)));
				location.setSource(rs.getString(2));
				location.setDestination(rs.getString(3));
				location.setAirlines(rs.getString(6));
				location.setPrice(rs.getInt(7));
				location.setRemaining(Integer.parseInt(rs.getString(4)));
				location.setPassengers(number);
				locations.add(location);
			}
			if(valid) {
				Cookie cookie = new Cookie("number", ""+number);
				response.addCookie(cookie);
				request.setAttribute("locations", locations);
				RequestDispatcher rd = request.getRequestDispatcher("showingPrice.jsp");
				rd.forward(request, response);
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("noFlights.html");
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
