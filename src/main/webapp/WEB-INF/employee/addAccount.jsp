<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>REYBANK ADD ACCOUNT</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
	<jsp:include page="/WEB-INF/common/commonHeader.jsp" />
	<jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />


	<div class="content">
		<div class="addAccount">
			<form action="addAccount" method="post">
				<div class="transaction" id="moneyTransfer">
				<table>
					<tr><th><h3>ADD ACCOUNT</h3></th></tr>
					<tr><td><input min="1" step="1" max="999999" name="userId" type="number" placeholder="User Id" required></td></tr>
					<!-- <tr><td><input  min="1" step="1" max="999999" name="accountNumber" type="number" placeholder="New Account Number" required></tr></td> -->
					<%if(request.getSession().getAttribute("auth").equals("employee")){ 
					int branchId=(int) request.getSession().getAttribute("branchId");%>
					<input name="branchId" type="hidden" value=<%=branchId %>>
					<%}else if(request.getSession().getAttribute("auth").equals("admin")){	
					 	JSONArray allBranch= (JSONArray)request.getAttribute("allBranch");
				       %>
						<tr>
						<td>Branch Id :<select name="branchId">
						<% 
                        	int length=allBranch.length();
                        for(int temp=0;length>temp;temp++){%>
							<option><%=allBranch.getJSONObject(temp).getLong("BranchId")%></option>
							<%} %>
						</select></tr></td>
						<%} %> 
					<tr><td><input name="balance" type="number"  min="0" step="1" max="999999999" placeholder="Initial Amount" required></tr></td>
					<tr><td><button type="submit">Create</button></tr></td>
					 <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
				</table>
				</div>
			</form>
		</div>
	</div>



</body>
</html>