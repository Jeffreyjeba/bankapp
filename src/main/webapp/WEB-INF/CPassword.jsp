<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK PASSWORD</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />


     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


    <div class="content">
        <div class="password">
            <form action="changePassword" method="post">
                <div class="transaction" id="resetPassword">
                <table>
                <tr><th><h3>CHANGE PASSWORD</h3></th></tr>
                 <tr><td><input name="oldPass" maxlength="20" type="password" placeholder="Old Password" required></td></tr>
                 <tr><td><input name="newPass"maxlength="20" type="password"placeholder="New Password" required></td></tr>
                 <tr><td><input name="confoPass" maxlength="20" type="password"placeholder="Reenter New Password" required></td></tr>
                 <tr><td><button type="submit">change</button></td></tr>
                 <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
                </div>
            </form>
        </div>
    </div>
</body>
</html>