<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<body>
    <div class="topBar">
        <img src="../images/title bar.svg">
        <h1>
              REY BANK
        </h1>

        <div class="mode" id="emp">
            <button >Employee</button>
            <button >Customer</button>
        </div>

        <div class="logout">
            <button>Logout</button> 
        </div>
    </div>
    
    <script>
    	<% String authString= (String) request.getAttribute("auth");
    		if(authString==null){%>
    		document.getElemetById("emp").style.display="none";
    		<%}
    		else{%>
    		document.getElemetById("emp").style.display="flex";
    		<%}%>
    </script>
    
    
    
    
    
    
</body>
</html>
