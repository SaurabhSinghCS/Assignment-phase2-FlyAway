<%@page import="org.simplilearn.workshop.objects.Locations , java.util.*" %>

<html>
<head>
<style>
	td,th{
	border: 1px solid black;
	padding-right: 30px;
	}
</style>
</head>
<body align="center">
<h2>Here are the list of Airlines with price from "<%=request.getParameter("source") %>" to "<%=request.getParameter("destination") %>".</h2>
<p>Click <strong>Book</strong> to Book the ticket.</p>
<br/><br/>

<%	ArrayList<Locations> location = (ArrayList<Locations>)request.getAttribute("locations");%>
<table align="center">
	<tr>
		<th>Air Lines</th>
		<th>price per person</th>
		<th>Your Price</th>
		<th>Want to Book</th>
	</tr>
		<% for(Locations temp : location){
			int price = temp.getPrice() * temp.getPassengers();
			%>
		<tr>
			<form action="fillDetails" method="post">
				<td><%=temp.getAirlines() %></td>
				<td><%=temp.getPrice() %></td>
				<td><%=price %></td>
				<% if(temp.getRemaining() >= temp.getPassengers()){ %><input type="text" name="ID" value="<%=""+temp.getId() %>" hidden="true">
					<td><input type="submit" placeholder="Book" value="Book"></td>
					<%}else{ %>
				<td style="color: red">Not Available</td>
					<%} %>
			</form>
			</tr>
		<%} %>
	
</table>

</body>
</html>