package org.simplilearn.workshop;

import java.io.IOException;
import java.util.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.simplilearn.workshop.objects.Details;



@WebServlet("/showDetails")
public class DetailsView extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	
	private static List<Details> details;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		List<Details> newdetails = new ArrayList<Details>();
		Cookie cookie[] = request.getCookies();
		int number = 0;
		for(Cookie temp : cookie){
			if(temp.getName().equals("number")){
				number = Integer.parseInt(temp.getValue());
			}
		}
		for(int i=1;i<=number;i++) {
			Details det = new Details();
			det.setAge(Integer.parseInt(request.getParameter("age"+i)));
			det.setEmail(request.getParameter("email"));
			det.setFirstName(request.getParameter("firstName"+i));
			det.setGender(request.getParameter("gender"+i));
			det.setMobile(request.getParameter("mobile"));
			det.setLastName(request.getParameter("lastName"+i));
			det.setSNo(i);
			newdetails.add(det);
		}
		setDetails(newdetails);
		boolean flag = false;
		request.setAttribute("flag", flag);
		request.getRequestDispatcher("payment.jsp").forward(request, response);
		
	}


	public static List<Details> getDetails() {
		return details;
	}


	public static void setDetails(List<Details> details) {
		DetailsView.details = details;
	}
	

}
