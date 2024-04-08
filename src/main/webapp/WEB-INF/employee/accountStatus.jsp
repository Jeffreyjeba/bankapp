<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK ACTIVATE ACCOUNT</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />

	<% String function =(String) request.getAttribute("function");
	if(function.equals("activate")){%>

    <div class="content">
        <div class="status">
            <form action="activateAccount" method="post">
            	<table>
            	<tr><th><h3>ACTIVATE ACCOUNT</h3></th></tr>
                 <tr><td><input name="accountNumber" min="1" step="1" type="number" placeholder="Account Number" required></td></tr>
                 <tr><td><input name="password" type="password" placeholder="Password" required></td></tr>
                 <tr><td><input name="function" value="activate" type="hidden"></td></tr>
                 <tr><td><button type="submit">Activate</button></td></tr>
                 <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
                   
            </form>
        </div>
    </div>
    
    <%} else if(function.equals("inactivate")){%>
    
       <div class="content">
        <div class="status">
            <form action="deactivateAccount" method="post">
            	<table>
            	<tr><th><h3>DEACTIVATE ACCOUNT</h3></th></tr>
                 <tr><td><input name="accountNumber" min="1" step="1" type="number" placeholder="Account Number" required></td></tr>
                 <tr><td><input name="password" type="password" placeholder="Password" required></td></tr>
                 <tr><td><input name="function" value="inactivate" type="hidden"></td></tr>
                 <tr><td><button type="submit">Deactive</button></td></tr>
                  <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
            </form>
        </div>
    </div>
    
     <%} else if(function.equals("delete")){%>
     
     <div class="content">
        <div class="status">
            <form action="deleteAccount" method="post">
            
            	<table>
            	<tr><th><h3>DELETE ACCOUNT</h3></th></tr>
                 <tr><td><input name="accountNumber" min="1" step="1" type="number" placeholder="Account Number" required></td></tr>
                 <tr><td><input name="password" type="password" placeholder="Password" required></td></tr>
                 <tr><td><input name="function" value="delete" type="hidden"></td></tr>
                 <tr><td><button type="submit">Delete</button></td></tr>
                 <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
                   
            </form>
        </div>
    </div>
    
     <%} %>

</body>
</html>