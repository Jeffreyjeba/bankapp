<!DOCTYPE html>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Document</title>
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/commoncss.css">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/css/customer.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 <link rel="stylesheet" href="<%=request.getContextPath()%>/css/login.css">
</head>
<body>

	<div class="navbar">
		<div>
			<a href="<%=request.getContextPath()%>/page/logout"><button>logout</button></a>
		</div>
	</div>
	<div class="title">
		<h1>CUSTOMER</h1>
	</div>
	<div class="leftNavBar">
		<!-- <div class="dropContainer">
			<button class="accountDrop">
				Account <i class="fa fa-caret-down"></i>
			</button>
			<br>
			<div class="dropDown">
				<button onclick="balance()">view balance</button>
				<button onclick="primary()">switch primary</button>
				<button onclick="accountSwitch()">switch account</button>
			</div>
		</div> -->
		<button onclick="balance()">view balance</button>
		<br>
		<button onclick="primary()">switch primary</button>
		<br>
		<button onclick="accountSwitch()">switch account</button>
		<br>
		<button onclick="debit()">Debit</button>
		<br>
		<button onclick="credit()">Credit</button>
		<br>
		<button onclick="moneyTransfer()">Money Transfer</button>
		<br>
		<button>TransactionHistory</button>
		<br>
		<button onclick="profile()">View Profile</button>
		<br>
		<button onclick="resetPassword()">ResetPassword</button>
		<br>
	</div>

	<div class="viewBalance" id="viewBalance">
		<label>Your Balance is :</label>
		<p>1000</p>
	</div>



	<%HttpSession customerSession=request.getSession();
	JSONObject profile=(JSONObject)customerSession.getAttribute("profile");	
	String popup=(String) customerSession.getAttribute("popup");%>

	<form action="switchPrimary" method="post">
		<div class="switchPrimary" id="switchPrimary">
			<label>choose your primary account number :</label> <select
				name="primAccount">
				<%long[] primAccount=(long[])customerSession.getAttribute("accounts");	
	              for(long acc:primAccount){
	       		%>
				<option><%= acc %></option>
				<%}%>
			</select><br>
			<button type="submit">switch</button>
			<jsp:include page="/WEB-INF/common/error.jsp" />
			<%-- <% 
			request.setAttribute("errorMessage",null);
			request.setAttribute("successMessage",null);
			%> --%>
		</div>
	</form>


	<form action="switchAccount" method="post">
		<div class="switchAccount" id="switchAccount">
			<label>choose your account number :</label>
			 <select name="account">
				<%long[] account=(long[])customerSession.getAttribute("accounts");
	              for(long acc:account){
	        	%>
				<option><%=acc%></option>
				<%}%>
			</select><br>
			<button type="submit">select</button>
			<jsp:include page="/WEB-INF/common/error.jsp" />
			<%-- <% 
			request.setAttribute("errorMessage",null);
			request.setAttribute("successMessage",null);
			%> --%>
		</div>
	</form>

	<div class="popup" id="profile">
		<table>
			<tr>
				<td>Name</td>
				<td><%=profile.getString("Name")%></td>
			</tr>
			<tr>
				<td>Id</td>
				<td><%=profile.getLong("Id")%></td>
			</tr>
			<tr>
				<td>Phone Number</td>
				<td><%=profile.getLong("PhoneNumber")%></td>
			</tr>
			<tr>
				<td>EmailId</td>
				<td><%=profile.getString("EmailId")%></td>
			</tr>
			<tr>
				<td>Address</td>
				<td><%=profile.getString("Address")%></td>
			</tr>
		</table>
	</div>


	<form action="credit" method="post">
		<div class="transaction" id="credit">
			<label>amount</label><br>
			 <input name="amount" type="number"placeholder="enter the amount" required><br> 
			<label>description</label><br>
			 <input name="description" type="text"placeholder="enter the description" required><br>
			<label>password</label><br>
			 <input name="password" type="password" placeholder="enter the password" required><br>
			<button type="submit">credit</button>
			<jsp:include page="/WEB-INF/common/error.jsp" />
			<%-- <% 
			request.setAttribute("errorMessage",null);
			request.setAttribute("successMessage",null);
			%> --%>
		</div>
	</form>

	<form action="debit" method="post">
		<div class="transaction" id="debit">
			<label>amount</label><br>
			 <input name="amount" type="number"placeholder="enter the amount" required><br> 
			<label>description</label><br>
			 <input name="description" type="text"placeholder="enter the description" required><br>
			<label>password</label><br>
			 <input name="password" type="password" placeholder="enter the password" required><br>
			<button type="submit">debit</button>
			<jsp:include page="/WEB-INF/common/error.jsp" />
		</div>
	</form>

	<form action="transfer" method="post">
		<div class="transaction" id="moneyTransfer">
			<label>amount</label><br>
			 <input name="amount" type="number"placeholder="enter the amount" required><br> 
			<label>description</label><br>
			 <input name="description" type="text"placeholder="enter the description" required><br>
			<label>password</label><br>
			 <input name="password" type="password" placeholder="enter the password" required><br>
			 <label>receiving Account</label><br>
			 <input name="account" type="password" placeholder="enter the receiving account" required><br>
			 <label>ifsc code</label><br>
			 <input name="ifsc" type="text" placeholder="enter the receiving account" required><br>
			<button type="submit">transfer</button>
		</div>
	</form>

	<form action="changePassword" method="post">
		<div class="transaction" id="resetPassword">
			<label>old password</label><br> <input type="password"
				placeholder="enter the old password"><br> <label>new
				password</label><br> <input type="password"
				placeholder="enter the new password"><br> <label>confirm
				password</label><br> <input type="password"
				placeholder="reenter new password"><br>
			<button type="submit">change</button>
		</div>
	</form>




</body>
<script>
	<%=popup.toString()%>();
	
	function balance() {
		document.getElementById("viewBalance").style.display = "block";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function primary() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "block";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function accountSwitch() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "block";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function allBlank() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
	}
	function credit() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "block";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function debit() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "block";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function moneyTransfer() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "block";
		document.getElementById("resetPassword").style.display = "none";
	}
	function profile() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "flex";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "none";
	}
	function resetPassword() {
		document.getElementById("viewBalance").style.display = "none";
		document.getElementById("switchPrimary").style.display = "none";
		document.getElementById("switchAccount").style.display = "none";
		document.getElementById("profile").style.display = "none";
		document.getElementById("credit").style.display = "none";
		document.getElementById("debit").style.display = "none";
		document.getElementById("moneyTransfer").style.display = "none";
		document.getElementById("resetPassword").style.display = "block";
	}

	function closeForm() {
		document.getElementById("myForm").style.display = "none";
	}
</script>
</html>