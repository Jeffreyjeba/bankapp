


<div class="leftNavBar">
  <%--   <a href="<%=request.getContextPath()%>/page/viewBalance">Balance</a>
    <br> --%>
    <a href="<%=request.getContextPath()%>/page/accountInfo">Accounts</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/debit">Debit</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/credit">Credit</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/moneyTransfer">Money Transfer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/transactionHistory">Transaction History</a>
    <br>
   <%--  <a href="<%=request.getContextPath()%>/page/changeAccount">Change Account</a>
    <br> --%>
    <a href="<%=request.getContextPath()%>/page/primaryAccount">Primary Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/profile">Profile</a>
    <br>
    <%if(request.getSession().getAttribute("auth").equals("customer")){ %>
    <a href="<%=request.getContextPath()%>/page/resetPassword">Reset Password</a>
    <br>
    <%} %>
</div>