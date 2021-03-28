<html>
<head>
<title>Admin Home</title>
</head>
<body align="center">
<h2>Welcome <%=(String)request.getAttribute("FirstName") %> <%=(String)request.getAttribute("LastName") %></h2>
<h4 align="right"><a href="logout">Log Out</a></h4>
<br/><br/><br/><br/>
<div>Want to change Password <a href="changePassword">click here</a>.</div>
<div>Want to see list of Source and Destinations <a href="source">click here</a>.</div>
<div>Want to see Airlines List <a href="airlines">click here</a>.</div>
<div>Want to see a list of Flights <a href="flights">click here</a>.</div>
</body>
</html>