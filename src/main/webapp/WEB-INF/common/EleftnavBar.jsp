<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%if(request.getSession().getAttribute("auth").equals("employee")){ %>


<div class="leftNavBar" id="2">
    <a href="<%=request.getContextPath()%>/page/addCustomer">AddCustomer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/addAccount">Add Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/activateAccount">Activate Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deactivateAccount">Deactivate Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deleteAccount">Delete Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/activateCustomer">Activate Customer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deactivateCustomer">Deactivate Customer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/initialDetail">Profile</a>
    <!-- Eprofile -->
    <br>
    <a href="<%=request.getContextPath()%>/page/resetPassword">ResetPassword</a>
    <br>
</div>

<%} else if(request.getSession().getAttribute("auth").equals("admin")){ %>
<div class="leftNavBar" id="2">
    <a href="<%=request.getContextPath()%>/page/addCustomer">AddCustomer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/addAccount">Add Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/activateAccount">Activate Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deactivateAccount">Deactivate Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deleteAccount">Delete Account</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/activateCustomer">Activate Customer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/deactivateCustomer">Deactivate Customer</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/initialDetail">Profile</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/addAuthority">Add Authority</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/addBranch">Add Branch</a>
    <br>
    <a href="<%=request.getContextPath()%>/page/manageEmployee">Manage Employee</a>
    <br>
    
</div>

<%}%>