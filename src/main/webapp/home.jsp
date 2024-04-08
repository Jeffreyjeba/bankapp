<!-- <%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> -->
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>HOME</title>
    <link rel="stylesheet" href="css/home.css">

</head>
<body>
    <div class="title">
        <h1>REY BANK</h1>
    </div>

    <div class="buttons">
        <div class="buttons1">
           <a href="<%=request.getContextPath()%>/page/login"><button>Login</button></a><br>
        </div>
    </div>

    <div class="image">
        <img src="images/bank-1071.svg">
    </div>

    <div class="body">  
        <p>This is a bank over trust which prioritizes customer service at all cost</p>
        <p>24/7 customer support</p>
        <p>100% transparent </p>
    </div>
</body>
</html>