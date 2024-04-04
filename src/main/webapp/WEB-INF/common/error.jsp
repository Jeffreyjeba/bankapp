<%@page import="java.io.Console"%>
<div>

	<%
	String errorMessage=(String) request.getAttribute("errorMessage");
	
	if(errorMessage!=null){
	%>
	<div id="timedMessage" style="color:red"><%=errorMessage %></div>
	<%}
	 String successMessage=(String) request.getAttribute("successMessage");
	 if(successMessage != null){
	%>
	<div id="timedMessage" style="color:green"><%=successMessage%></div>
	<%
	 }
	%>
	
	<script>
	setTimeout(timeout,5000);
	function timeout(){
		document.getElementById("timedMessage").style.display="none"
	}
	</script>
	
	
	
</div>