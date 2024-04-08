<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK CREDIT</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />

     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />
     

    <div class="content">
        <div class="inputBox">
            <form action="credit" method="post">
                   <table>
                    <tr><th><h3>CREDIT</h3></th></tr>
               		<tr><td><input name="amount" min="1" step="1" type="number"placeholder="Amount" required><br></td></tr>
               		<tr><td><input name="description" title="No arrow characters" pattern="[^<>]*" type="text"placeholder="Description" required><br></td></tr>
               		<tr><td><input name="password" type="password" placeholder="Password" required><br></td></tr>
               		<tr><td><button type="submit">credit</button></td></tr>
               		<tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                  </table>
            </form>
        </div>
        <jsp:include page="/WEB-INF/common/support.jsp" />
    </div>


  
</body>
</html>