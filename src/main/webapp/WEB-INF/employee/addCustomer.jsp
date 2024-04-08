<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK ADD CUSTOMER</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>


    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />

    <div class="content">

        <div class="addCustomer">
            <form action="addCustomer" method="post">
                <div class="transaction" id="moneyTransfer">
		                <table>
		                	<tr><th><h3>ADD CUSTOMER</h3></th></tr>
		                    <tr><td><input name="id" type="number" min="1" step="1" placeholder="Id" required></td></tr>
		                    <tr><td><input name="name" title="no special characters" type="text" pattern="[a-zA-Z]*" placeholder="Name" required></td></tr>
		                    <tr><td><input name="emailId" type="email"placeholder="EmailId" required></td></tr>
		                    <tr><td><input name="phoneNumber" title="enter a valid phone Number" type="text"placeholder="Phone Number" pattern="^[7-9]{1}[0-9]{9}$" required></td></tr>
		                    <tr><td><input name="aadhar" min="100000000000" max="999999999999" step="1" type="number" placeholder="Aadhar Number" required></td></tr>
		                    <tr><td><input name="pan" title="Enter a valid pan number" type="text" placeholder="Pan Number" pattern="^[A-Za-z]{5}[0-9]{4}[A-Za-z]{1}$" required></td></tr>
		                    <tr><td><input name="address" title="NO arrow Brackets" type="text" placeholder="Address" pattern="[^<>]*" required></td></tr>
		                    <tr><td><button type="submit">Add</button></td></tr>
		                    <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
		                </table>
                </div>
            </form>
        </div>
    </div>


  
</body>
</html>