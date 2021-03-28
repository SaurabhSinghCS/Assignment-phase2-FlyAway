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
<body>

	<h3 align="center">Want to go back <a href="admin">Click here</a></h3>
	<br/><br/>
	
	<table>
		<tr>
			<th>Flight</th>
			<th>Source</th>
			<th>Destination</th>
			<th>Air Lines</th>
			<th>Price Per Ticket</th>
		</tr>
		<%	ArrayList<Locations> location = (ArrayList<Locations>)request.getAttribute("locations");
			List<String> airlines = new ArrayList<>();
			for(Locations temp : location){%>
					<tr>
						<td><%=temp.getFlight() %></td>
						<td><%=temp.getSource() %></td>
						<td><%=temp.getDestination() %></td>
						<td><%=temp.getAirlines() %></td>
						<td><%=temp.getPrice() %></td>
					</tr>
		<%  } %>
		
	</table>
	
	
</body>
</html>