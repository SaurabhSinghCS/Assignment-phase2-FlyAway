<%@page import="org.simplilearn.workshop.objects.Locations , java.util.*" %>


<html>
<head>
<title>Search</title>
</head>
<body align="center">

<h1> Welcome User </h1>

<%ArrayList<Locations> location = (ArrayList<Locations>)request.getAttribute("locations");
	Set<String> sour = new HashSet<>();
	Set<String> desti = new HashSet<>();
	
	
	Date today = new Date();
	Date tomorrow = new Date(today.getTime() + (1000 * 60 * 60 * 24));
	String tomo = ""+(tomorrow.getYear()+1900)+"-"+(tomorrow.getMonth()<9 ? "0" : "")+(tomorrow.getMonth()+1)+
		"-"+(tomorrow.getDate()<9 ? "0" : "")+tomorrow.getDate();
	Date twoMonths = tomorrow;
	twoMonths.setMonth(tomorrow.getMonth()+2);
	String twoMon = ""+(twoMonths.getYear()+1900)+"-"+(twoMonths.getMonth()<9 ? "0" : "")+
		(twoMonths.getMonth()+1)+"-"+(twoMonths.getDate()<9 ? "0" : "")+twoMonths.getDate();
%>


<form method="post" action="searchCredentials">
<pre>
  Source Location :- <select name="source" required="required">
<option value=""></option>
<% for(Locations temp : location){
if(sour.add(temp.getSource())){%>
<option value="<%=temp.getSource() %>"><%=temp.getSource() %></option>
<%  }} %>
</select>
	
	
Destination Location :- <select name="destination" required="required">
<option value=""></option>
<%for(Locations temp : location){
if(desti.add(temp.getDestination())){%>
<option value="<%=temp.getDestination() %>"><%=temp.getDestination() %></option>
<%  }} %>
</select>


	    Date of Journey :- <input type="date" name="date" min="<%=tomo%>" max="<%=twoMon%>" required="required"/>


 No. of passengers :- <input type="number" name="number" min="1" max="10" required="required"/>


<input type="submit" value="submit"> <input type="reset" value="reset">

</pre>
</form>


</body>
</html>