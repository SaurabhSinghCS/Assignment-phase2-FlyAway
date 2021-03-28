<%@page import="org.simplilearn.workshop.objects.Locations , java.util.*" %>

<html>
<head>
</head>
<body align="center">

	<h3>Want to go back <a href="admin">Click here</a></h3>
	<br/><br/>
	
	<table>
		<tr>
			<th>List of Air Lines</th>
		</tr>
		<%ArrayList<Locations> location = (ArrayList<Locations>)request.getAttribute("locations");
			List<String> airlines = new ArrayList<>();
			for(Locations temp : location){
				if(!airlines.contains(temp.getAirlines())){
					airlines.add(temp.getAirlines());
				}
			}
			Collections.sort(airlines);
			for(String temp : airlines){%>
					<tr>
						<td><%=temp %>	</td>
					</tr>
		<%  } %>
		
	</table>
	
	
</body>
</html>