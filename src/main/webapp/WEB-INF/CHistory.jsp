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
    %>


	<jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


	<div class="history">
		<table>
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
				<%	
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
				<%}%>


			<jsp:include page="/WEB-INF/common/error.jsp" />

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
</html>