<html>
<head>
<title>Payment</title>
</head>
<body>
<h3 align="center">Enter your Debit Card Details</h3>
<% if((boolean)request.getAttribute("flag")){ %>
<p align="center" style="color: red;">Please enter write Credentials.</p>
<%} %>
<form action="payment" method="post">
<pre>
Card Number            <input type="text" name="cardNumber" required="required"/>


Expiry Date Month/Year <select name="month" required="required">
<option value=""></option>
<% for(int i=1;i<=12;i++){
	String s = ""+i;
if(i<10) {
	s = "0"+s;
	}%>
<option value="0<%=s%>"><%=s%></option>
<%} %>
</select> <select name="years" required="required">
<option value=""></option>
<% for(int i=2022;i<=2035;i++){
	String s = ""+i;%>
<option value="0<%=s%>"><%=s%></option>
<%} %>
</select>


Security Number        <input type="number" name="number" min="1" max="999" maxlength="3" required="required"/>


Account Holder Name    <input type="text" name="holder" required="required" />


<input type="submit" value="submit"/> <input type="reset" value="reset"/>

</pre>
</form>
</body>
</html>