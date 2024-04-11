<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK BRIDGE</title>
     <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
 	<jsp:include page="/WEB-INF/common/commonHeader.jsp" />
 	<jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />
 	
	<div class="content">
        <div class="status">
            <form action="connectCustomer" method="post">
            	<table>
                     <tr><th><h3>Customer</h3></th></tr>
                     <tr><td><input name="id" min="1" step="1" type="number" placeholder="Customer Id" required></td></tr>
                     <tr><td><button type="submit">submit</button></td></tr>
                     <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>