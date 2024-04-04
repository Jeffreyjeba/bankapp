<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK ADD</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>


    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />

  <div class="content">
        <div class="addCustomer">
            <form action="addAuthority" method="post">
                <div class="transaction" id="moneyTransfer">
                 <table>	
                 	<tr><th><h3>ADD AUTHORITY</h3></th></tr>
                    <tr><td><input name="id" type="number" placeholder="Id" required></td></tr>
                    <tr><td><input name="name" type="text" placeholder="Name" required></td></tr>
                    <tr><td><input name="emailId" type="email"placeholder="EmailId" required></td></tr>
                    <tr><td><input name="phoneNumber" type="number"placeholder="Phone Number" pattern="^[7-9]{1}[0-9]{9}$" required></td></tr>
                    <tr><td><input name="branchId" type="number" placeholder="BranchId" required></td></tr>
                    <tr><td><label > Type :</label>
                    	<select name="type">
	                     <option>employee</option>
	                     <option>admin</option>
                     </select></td></tr>
                    <tr><td><button type="submit">Add</button></td></tr>
                     <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
             	</table>
                </div>
            </form>
        </div>
    </div>


  
</body>
</html>