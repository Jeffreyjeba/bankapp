<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK DEBIT</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    
     <%HttpSession customerSession=request.getSession();
	long currentAccount= (long)customerSession.getAttribute("currentAccount");
	
	 %>


     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />


    <div class="content">
        <div class="inputBox">
            <form action="debit" method="post">
	                   <table>
	                    <tr><th><h3>DEBIT</h3></th></tr>
	                    <tr><td>Account  :<select name="account">
	                  	<option><%=currentAccount%></option>
	                  	<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				            	  if(acc!=currentAccount){
				        	%>
							<option><%=acc%></option>
							<%}%>
	               			<%}%>
	                    </select></td></tr>
	               		<tr><td><input name="amount" min="1" step="1"max="999999999" type="number"placeholder="Amount" required><br></td></tr>
	               		<tr><td><input name="description" maxlength="25" title="No arrow characters" pattern="[^<>]*" type="text"placeholder="Description" required><br></td></tr>
	               		<tr><td><input name="password" maxlength="20" type="password" placeholder="Password" required><br></td></tr>
	               		<tr><td><button type="submit">Debit</button></td></tr>
	               		<tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
	                  </table>
            </form>
        </div>
    </div>


  
</body>
</html>