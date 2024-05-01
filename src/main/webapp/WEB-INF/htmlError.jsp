<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK ERROR</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
     <%if(request.getSession().getAttribute("auth").equals("customer")){ %>
     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />
     <%}else{ %>
     <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />
     <%} %>


    <div class="content">
        <div class="inputBox">
      
	                   <table>
	                    <tr><th><h3> ERROR </h3></th></tr>
	               		<tr><td>Error due to inadequate information<br></td></tr>
	               		<tr><td>Fill every fields before submitting<br></td></tr>
	               		<tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
	                  </table>
        </div>
    </div>
</body>
</html>