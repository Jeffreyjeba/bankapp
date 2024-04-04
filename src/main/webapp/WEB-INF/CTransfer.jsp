<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK MONEY TRANSFER</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
     
     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


    <div class="content">

        <div class="moneyTransfer">
            <form action="transfer" method="post">
                <div class="transaction" id="moneyTransfer">
                <table>
                   <tr><td> <input name="account" type="number" min="1" step="1" placeholder="Receiving Account" required></td></tr>
                   <tr><td> <input name="ifsc" type="text" placeholder="Ifsc Code" required></td></tr>
                   <tr><td><input name="amount" type="number" min="1" step="1" placeholder="Amount" required></td></tr>
                   <tr><td><input name="description" type="text" title="No arrow characters" pattern="[^<>]*" placeholder="Description" required></td></tr>
                   <tr><td> <input name="password" type="password" placeholder="Password" required></td></tr>
                   <tr><td> <button type="submit">transfer</button></td></tr>
                </table>
                </div>
            </form>
        </div>
        <jsp:include page="/WEB-INF/common/support.jsp" />
    </div>


  
</body>
</html>