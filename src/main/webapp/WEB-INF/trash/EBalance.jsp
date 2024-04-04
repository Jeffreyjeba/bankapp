<%@page import="javax.swing.text.AbstractDocument.Content"%>
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
	  if(customerSession.getAttribute("currentAccount")==null){%>
		  <jsp:include page="/WEB-INF/employee/initialDetail.jsp" />
	 
			<% } else{long currentAccount= (long)customerSession.getAttribute("currentAccount");%>
     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


    <div class="content" id="1">

        <div class="balance">
            <p>
            Account Balance (INR)<br>
            <%=request.getAttribute("balance") %>
            </p>
        </div>
        <div class="support">
            <div class="currentAccount">
                Current Account<br>
                <%=currentAccount %>
            </div>

            <div class="switchAccount">
			      <form action="switchAccountBalance" method="post">
						<label>choose your account number :</label>
						 <select name="account">
							<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				        	%>
							<option><%=acc%></option>
							<%}%>
						</select><br>
						<button type="submit">switch</button>
						<jsp:include page="/WEB-INF/common/error.jsp" />
				</form>
            </div>
        </div>
    </div>
<%}%>

  
</body>
</html>