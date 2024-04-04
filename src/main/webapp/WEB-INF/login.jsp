<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK LOGIN</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>
    <div class="name">
        <h1>
            REY BANK
        </h1>
    </div>

    <div class="login">
        <h3>LOGIN</h3>
        <form action="LoginAuthendicate" method="post">
        	<label>USER ID</label><br>
        	<input type="number" min="1" step="1" name="id" placeholder="user id" class="box" required><br>
        	<label>PASSWORD</label><br>
        	<input type="password" placeholder="password" name="password" class="box" required><br>
       		<button type="submit">LOGIN</button>
       		<div style="color:red">${errorMessage}</div>
       </form>
    </div>
    <div>
        <img src="<%=request.getContextPath()%>/images/Authentication-rafiki.svg" alt="bank image">
    </div>
</body>
</html>