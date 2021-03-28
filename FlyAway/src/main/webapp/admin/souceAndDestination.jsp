<%@page import="org.simplilearn.workshop.objects.Locations , java.util.*" %>

<html>
<head>
</head>
<body>

	<h3>Want to go back <a href="admin">Click here</a></h3>
	<br/><br/>
	
	<table align="left" style="padding-right: 30px;">
		<tr>
			<th>List of Sources</th>
		</tr>
		<%ArrayList<Locations> location = (ArrayList<Locations>)request.getAttribute("locations");
			List<String> source = new ArrayList<>();
			List<String> destination = new ArrayList<>();
			for(Locations temp : location){
				if(!source.contains(temp.getSource())){
					source.add(temp.getSource());
				}
				if(!destination.contains(temp.getDestination())){
					destination.add(temp.getDestination());
				}
			}
			Collections.sort(source);
			Collections.sort(destination);
			for(String temp : source){%>
					<tr>
						<td><%=temp %>	</td>
					</tr>
		<%  } %>
		
	</table>
	<table align="left">
		<tr>
			<th>List of destination</th>
		</tr>
		<%for(String temp : destination){ %>
			<tr>
				<td><%=temp %></td>
			</tr>
		<%} %>
	</table>                          
	
	
	
</body>
</html>