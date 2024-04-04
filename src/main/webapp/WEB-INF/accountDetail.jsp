<%@page import="utility.BankException"%>
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
		long branchId=UtilityHelper.getLong(account,"BranchId");
     	long balance=UtilityHelper.getLong(account,"Balance");
     	String status=UtilityHelper.getString(account,"Status");
     	String priority;
     	try{
     		UtilityHelper.getString(account,"Priority");
     		priority= "yes";
     		}
     	catch(BankException e){
     		priority="no";
     	}
     	
     %>
     

    <div class="content">

        <div class="profile">
                   <table>
                    <tr><th><h3>Account</h3></th></tr>
               		<tr> 
               		 <td>Branch Id :</td>
               		 <td><%=branchId %> </td>
               		</tr>
               		<tr> 
               		 <td>Balance :</td>
               		 <td><%=balance %> </td>
               		</tr>
               		<tr> 
               		 <td>Status :</td>
               		 <td><%=status %> </td>
               		</tr>
               		<tr> 
               		 <td>primary Account :</td>
               		 <td><%=priority %></td> 
               		</tr>
               		
               		<tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                  </table>
                </div>
                <jsp:include page="/WEB-INF/common/support.jsp" />
        </div>



  
</body>
</html>