<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK BALANCE</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
	
	<%HttpSession customerSession=request.getSession();
	long currentAccount= (long)customerSession.getAttribute("currentAccount");
	 %>

     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


    <div class="content">

        <div class="balance">
            <p>
            Account Balance (INR)<br>
            <%=request.getAttribute("balance") %>
            </p>
           
        </div>
          <jsp:include page="/WEB-INF/common/support.jsp" />
    </div>
    
    
    
   


  
</body>
</html>