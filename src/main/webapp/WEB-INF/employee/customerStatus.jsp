<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK CUSTOMER </title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />

	<% String function =(String) request.getAttribute("function");
	if(function.equals("activate")){%>

      <div class="content">
        <div class="status">
            <form action="activateCustomer" method="post">
            	<table>
                    <tr><th><h3>ACTIVATE CUSTOMER</h3></th></tr>
                    <tr><td><input min="1" step="1" max="999999" name="id" type="number" placeholder="Id" required></td></tr>
                    <tr><td><input maxlength="25" name="password" type="password" placeholder="Password" required></td></tr>
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
            <form action="deactivateCustomer" method="post">
                  <table>
                    <tr><th><h3>DEACTIVATE CUSTOMER</h3></th></tr>
                    <tr><td><input  min="1" step="1" max="999999" name="id" type="number" placeholder="Id" required><tr><td>
                    <tr><td><input maxlength="25" name="password" type="password" placeholder="Password" required><tr><td>
                    <tr><td><input name="function" value="inactivate" type="hidden"></td></tr>
                    <tr><td><button type="submit">Deactive</button><tr><td>
                     <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                 </table>
            </form>
        </div>
    </div>
     <%} %>

</body>
</html>