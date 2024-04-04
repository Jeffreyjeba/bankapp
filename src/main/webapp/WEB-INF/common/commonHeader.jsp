
<%String auth=(String)request.getSession().getAttribute("auth"); 
if(auth.equals("customer")){
%>


	<div class="topBar">
        <img src="<%=request.getContextPath()%>/images/title bar.svg">
        <h1>
              REY BANK
        </h1>
        <div class="logout">
            <a href="<%=request.getContextPath()%>/page/logout">Logout</a> 
        </div>
    </div>
    
    <%} else{ %>
    
    
    
     <div class="topBar">
        <img src="../images/title bar.svg">
        <h1>
              REY BANK
        </h1>

        <div class="mode">
           	<a href="<%=request.getContextPath()%>/page/bridge">Customer</a>
        </div>
        <div class="logout">
           <a href="<%=request.getContextPath()%>/page/logout">Logout</a>
           <a href="<%=request.getContextPath()%>/page/initialDetail">Home</a>
        </div>
    </div>
    <%}%>