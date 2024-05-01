<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK MANAGE AUTHORITY</title>
   <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />
	<div class="content">
        <div class="status">
            <form action="employeeStatus" method="post">
            	<table>
                     <tr><th><h3>MANAGE EMPLOYEE</h3></th></tr>
                     <tr><td><input min="1" max="999999" step="1" name="id" type="number" placeholder="Employee Id" required></td></tr>
                     <tr><td>Status :<Select name="status" ><option>Active</option><option>Inactive</option></Select></td></tr>
                     <tr><td><input maxlength="25" name="password" type="password" placeholder="Password" required><br></td></tr>
                    <tr><td><button type="submit">Submit</button></td></tr>
                    <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
               
            </form>
             
        </div>  
    </div>
		
 

</body>
</html>