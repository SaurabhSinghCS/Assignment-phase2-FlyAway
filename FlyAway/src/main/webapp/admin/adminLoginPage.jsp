<html>
<head>
<title>Welcome Admin</title>
</head>
<body align="center">
	<h1>Welcome Admin</h1>
	<% if((boolean)request.getAttribute("isValid")) {%>
		<h3 style="color: red">Please enter valid User Name and Password.</h3>
	<% } %>
	<form action="admin" method="post">
	<pre>
User Name : <input type="text" name="username"><br/><br/>
Password  : <input type="password" name="password"><br/><br/>
    	<input type="submit" value="submit"> <input type="reset" value="reser"><br/><br/>
	</pre>
	</form>
	
</body>
</html>