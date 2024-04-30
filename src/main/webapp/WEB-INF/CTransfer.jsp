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
	<%HttpSession customerSession=request.getSession();
	long currentAccount= (long)customerSession.getAttribute("currentAccount");
	
	 %>

    <div class="content">

        <div class="moneyTransfer">
            <form action="transfer" method="post">
                <div class="transaction" id="moneyTransfer">
                <table>
                 <tr><td>Account  :<select name="account">
	                  	<option><%=currentAccount%></option>
	                  	<%long[] account=(long[])customerSession.getAttribute("accounts");
				              for(long acc:account){
				            	  if(acc!=currentAccount){
				        	%>
							<option><%=acc%></option>
							<%}%>
	               			<%}%>
	               			</select>
                   <tr><td> <input name="recAccount" type="number" min="1" step="1" max="999999" placeholder="Receiving Account" required></td></tr>
                   <tr><td> <input name="ifsc" type="text" placeholder="Ifsc Code" maxlength="8" required></td></tr>
                   <tr><td><input name="amount" type="number" min="1" step="1" max="999999999" placeholder="Amount" required></td></tr>
                   <tr><td><input name="description" maxlength="25" type="text" title="No arrow characters" pattern="[^<>]*" placeholder="Description" required></td></tr>
                   <tr><td> <input name="password" maxlength="20" type="password" placeholder="Password" required></td></tr>
                   <tr><td> <button type="submit">transfer</button></td></tr>
                   <tr><td><jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
                </div>
            </form>
        </div>
    </div>


  
</body>
</html>