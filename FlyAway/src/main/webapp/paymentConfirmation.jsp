<%@page import="org.simplilearn.workshop.objects.Locations , org.simplilearn.workshop.objects.Details , java.util.*" %>

<html>
<head>
<title>Payment Confirmation</title>
<style>
	td,th{
	padding-right: 30px;
	}
</style>
</head>
<br/><br/>
<% 	ArrayList<Details> details = (ArrayList<Details>)request.getAttribute("details");
	Locations location = (Locations)request.getAttribute("location");%>
<h2 align="center" style="color: lightgreen;">Payment Confirmed</h2>
<h4>Your Flight details are</h4>
<table>
	<tr>
		<th>Flight</th>
		<th>Source</th>
		<th>Destination</th>
		<th>Air Lines</th>
		<th>Date</th>
		<th>Number of Passengers</th>
		<th>Booking Status</th>
	</tr>
	<tr>
		<td><%=location.getFlight() %></td>
		<td><%=location.getSource() %></td>
		<td><%=location.getDestination() %></td>
		<td><%=location.getAirlines() %></td>
		<td><%=location.getDate() %></td>
		<td><%=details.size() %></td>
		<td style="color: lightgreen">Confirmed</td>
	</tr>
</table><br/><br/>
<h4>Passenger Details are</h4>
<table>
	<tr>
		<th>S.No.</th>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Age</th>
		<th>Gender</th>
	</tr>
	<% for(Details temp : details){ %>
	<tr>
		<td><%=temp.getSNo() %></td>
		<td><%=temp.getFirstName() %></td>
		<td><%=temp.getLastName() %></td>
		<td><%=temp.getAge() %></td>
		<td><%=temp.getGender() %></td>
	</tr>
	<% } %>
</table>
</html>