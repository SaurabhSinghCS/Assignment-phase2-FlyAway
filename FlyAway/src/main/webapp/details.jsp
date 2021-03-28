<%@page import="org.simplilearn.workshop.objects.Locations , java.util.*" %>

<html>
<head>
<style>
	td,th{
	padding-right: 30px;
	}
</style>
</head>
<body align="center">
<h2>Welcome to Details Filling page</h2>
<% 	Locations location = (Locations)request.getAttribute("location");
	Cookie cookie[] = request.getCookies();
	int number = 0;
	for(Cookie temp : cookie){
		if(temp.getName().equals("number")){
			number = Integer.parseInt(temp.getValue());
			break;
		} } %>
<div>
<h3>Your Flight are :- </h3>
<table>
	<tr>
		<th>Air Lines</th>
		<th>Source</th>
		<th>Destination</th>
		<th>Date</th>
		<th>Number of Passengers</th>
		<th>Your Price</th>
	</tr>
	<tr>
		<td><%=location.getAirlines() %></td>
		<td><%=location.getSource() %></td>
		<td><%=location.getDestination() %></td>
		<td><%=location.getDate() %></td>
		<td><%=location.getPassengers() %></td>
		<td><% String price = ""+(location.getPrice()*number); %><%=price %></td>
	</tr>
</table>
</div>
<br/><br/>
<form action="showDetails" method="post">
	<table align="center">
		<tr>
			<th>S.No.</th>
			<th>First Name</th>
			<th>Last Name</th>
			<th>Gender</th>
			<th>Age</th>
		</tr>
		<% for(int i=1;i<=number;i++) {%>
		<tr>
			<td><%=i %></td>
			<td><input type="text" name="firstName<%=i %>" required="required"></td>
			<td><input type="text" name="lastName<%=i %>" required="required"></td>
			<td><select name="gender<%=i %>" required="required">
				<option value=""></option>
				<option value="Male">Male</option>
				<option value="Female">Female</option>
				<option value="Other">Other</option>
				<option value="NA">Don't want to Disclose</option>
			</select></td>
			<td><input type="number" name="age<%=i %>" min="1" max="90" required="required"></td>
		</tr>
		<%} %>
	</table>
	Enter your Email address :- <input type="email" name="email" required="required"/><br/>
	Enter your Mobile Number :- <input type="number" name="mobile" min="6000000001" max="9999999999" maxlength="10" required="required"/>
	<br/><pre><input type="submit" value="Submit To Move Payment Gateway"> <input type="reset" value="reset"></pre>
</form>
</body>
</html>