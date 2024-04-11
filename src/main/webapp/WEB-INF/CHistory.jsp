<%@page import="java.time.format.DateTimeFormatter"%>
<%@page import="utility.BankException"%>
<%@page import="utility.UtilityHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<%@page import="java.time.ZonedDateTime"%>
<%@page import="java.time.Instant"%>
<%@page import="java.time.ZoneId"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>REYBANK HISTORY</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
	<jsp:include page="/WEB-INF/common/commonHeader.jsp" />


	<% 
    JSONArray jArray=(JSONArray) request.getAttribute("jArray");
    ZoneId zId= ZoneId.systemDefault();
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    int curretPage=(int) request.getAttribute("currentPage");
    HttpSession customerSession=request.getSession();
	long currentAccount= (long)customerSession.getAttribute("currentAccount");
    %>


	<jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


	<div class="history">		
		<div class="filter">
		<h4>Type:</h4>
		<select id="myInput" onchange="myFunction()">
				     	<option></option>
				       <option>debit</option>
				       <option>credit</option>
				       <option>OBMoneyTransfer</option>
				        <option>moneyTransfer</option>
				      </select>
				
		<h4>Account:</h4>
		<form action="historySwitch" method="post" >
				<select name="account" onchange="this.form.submit()">
					<option><%=currentAccount%></option>
	                  	<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				            	  if(acc!=currentAccount){
				        	%>
							<option><%=acc%></option>
							<%}%>
	               			<%}%>
	               		</select>
		
		</form>
		</div>
	
	
		<table id="myTable">
		
		<tr>
			
			<tr>
				<th>TransactionId</th>
				<th>date</th>
				<th>AccountNumber</th>
				<th>Receiver</th>
				<th>Type</th>
				<th>Amount</th>
				<th>Balance</th>
				<th>Description</th>
			</tr>

			<div class="tableContent" id="switchPrimary">
				<%if(jArray != null){
						int length= jArray.length();
		               for(int i=0;i<length;i++){
		            	  JSONObject json=jArray.getJSONObject(i);
		            	  long tid= UtilityHelper.getLong(json, "TransactionId");
		            	  long accNo=UtilityHelper.getLong(json, "AccountNumber");
		            	  long tAmount=UtilityHelper.getLong(json, "TransactionAmount");
		            	  String tType=UtilityHelper.getString(json, "TransactionType");
		            	  long balance= UtilityHelper.getLong(json, "Balance");
		            	  String description=UtilityHelper.getString(json,"Description");
		            	  String tAccNo;
		            	  try{
		            	  tAccNo=Long.toString( UtilityHelper.getLong(json,"TransactionAccountNumber"));
		            	  }
		            	  catch(BankException e){
		            		  tAccNo="-";
		            	  }
		            	  Instant ins= Instant.ofEpochMilli(tid);
		              
		            	  
		       		%>
				<tr>
					<td><%= tid %></td>
					<td><%= ZonedDateTime.ofInstant(ins,zId).format(formatter)%></td>
					<td><%= accNo %></td>
					<td><%= tAccNo %></td>
					<td><%= tType %></td>
					<td><%= tAmount %></td>
					<td><%= balance %></td>
					<td><%= description %></td>
				</tr>
				<%} %>
				<%} %>


			</div>
		</table>
	</div>
		<div class="pagination">
			<form action="previousPage" method="post">
				<input name="page" type="hidden" value=<%=curretPage%>>
				<button type="submit"><</button>
			</form>
			<button><%=curretPage %></button>
			<form action="nextPage" method="post">
				<input name="page" type="hidden" value=<%=curretPage%>>
				<button type="submit">></button>
			</form>
		</div>
		
		
</body>


<script>
function myFunction() {
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("myInput");
  filter = input.value.toUpperCase();
  table = document.getElementById("myTable");
  tr = table.getElementsByTagName("tr");
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[4];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }       
  }
}
</script>
</html>