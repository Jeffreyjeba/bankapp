<%@page import="utility.UtilityHelper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK Account</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />

     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />
     <% JSONObject account=(JSONObject) request.getAttribute("accountDetail"); 
		long accountNumber=UtilityHelper.getLong(account,"AccountNumber");
		long branchId=UtilityHelper.getLong(account,"BranchId");
     
     
     %>
     

    <div class="content">

        <div class="credit">
        
            <form action="credit" method="post">
                <div class="transaction" id="credit">
                   <table>
                    <tr><th><h3>Account</h3></th></tr>
               		<tr><td><input name="amount" min="1" step="1" type="number"placeholder="Amount" required><br></td></tr>
               		<tr><td><input name="description" title="No arrow characters" pattern="[^<>]*" type="text"placeholder="Description" required><br></td></tr>
               		<tr><td><input name="password" type="password" placeholder="Password" required><br></td></tr>
               		<tr><td><button type="submit">credit</button></td></tr>
               		<tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                  </table>
                </div>
            </form>
        </div>
        <jsp:include page="/WEB-INF/common/support.jsp" />
    </div>


  
</body>
</html>