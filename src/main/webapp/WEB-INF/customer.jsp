<!DOCTYPE html>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/customer.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>
<body>

    <div class="navbar">
        <div>
        <button onclick="history.back()">back</button>
        <a href="<%=request.getContextPath()%>/page/logout"><button>logout</button></a>
        </div>
    </div>
    <div class="title">
        <h1> CUSTOMER </h1>
    </div>
	    <div class="leftNavBar">
	        <div class="dropContainer">
	        <a class="accountDrop">Account <i class="fa fa-caret-down"></i></a><br>
	        <div class="dropDown">
	            <button onclick="openbalance()">view balance</button>
	            <button onclick="openprimary()">switch primary</button>
	            <button onclick="openswitch()">switch account</button>
	        </div>
	        </div>
	        <a href="../account/debit.html">Debit</a><br>
	        <a href="../account/credit.html">Credit</a><br>
	        <a href="../account/moneyTransfer.html">Money Transfer</a><br>
	        <a href="../account/transactionHistory.html">TransactionHistory</a><br>
	        <a href="../user/profile.html">View Profile</a><br>
	        <a href="../user/resetPassword.html">ResetPassword</a><br>
	    </div>

    <div class="viewBalance" id="viewBalance">
        <label>Your Balance is :</label>
        <p>1000</p>
    </div>
	<%JSONObject profile=(JSONObject)request.getAttribute("profile");%>
    <div class="switchPrimary" id="switchPrimary">
        <label>choose your primary account number :</label>
       
        
        <select>
        <%long[] account=(long[])request.getAttribute("accounts");
           for(long acc:account){
        %>
        	 <option><%= acc %></option>
        	 <%}%>
        </select><br>
        <button>switch</button>
    </div >

    <div class="switchAccount" id="switchAccount">
        <label>choose your account number :</label>
        <select>
            <option>1</option>
            <option>2</option>
            <option>3</option>
        </select><br>
        <button>select</button>
    </div>
    
    <div class="profile" id="profile">
    
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
    
    
    
    

</body>
<script>
function openbalance(){
    document.getElementById("viewBalance").style.display="block";
    document.getElementById("switchPrimary").style.display="none";
    document.getElementById("switchAccount").style.display="none";
    document.getElementById("profile").style.display="none";
  }
  function openprimary(){
    document.getElementById("viewBalance").style.display="none";
    document.getElementById("switchPrimary").style.display="block";
    document.getElementById("switchAccount").style.display="none";
    document.getElementById("profile").style.display="none";
  }
  function openswitch(){
    document.getElementById("viewBalance").style.display="none";
    document.getElementById("switchPrimary").style.display="none";
    document.getElementById("switchAccount").style.display="block";
    document.getElementById("profile").style.display="none";
  }
  function closeForm(){
    document.getElementById("myForm").style.display="none";
  }
  </script>
</html>