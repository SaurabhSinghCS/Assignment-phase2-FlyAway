<html>
<head>
<title>Password Change</title>
</head>
<body align="center">
<h2>Enter Passwords for Change</h2><br/>
<%  //checking weather new password and confirm password are equal
	if((boolean)request.getAttribute("confirmation")) {%>
	 <h3 style="color: red">Entered passwords are not equal</h3><br/><br/>
	<% } %>
<form action="changePassword" method="post">
<pre>
    New Password : <input type="password" name="password1" required="required"><br/><br/>
Confirm Password : <input type="password" name="password2" required="required"><br/><br/>
    	<input type="submit" value="submit"> <input type="reset" value="reser"><br/>
</pre>
</form>
</body>
</html>