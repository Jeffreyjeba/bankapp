<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK PRIMARY ACCOUNT</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
     <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
     
     <jsp:include page="/WEB-INF/common/leftnavBar.jsp" />

	<%HttpSession customerSession=request.getSession();
	long primAccount= (long) request.getAttribute("primaryAccount");%>

	<div class="content">
		<div class="support">
			<div class="currentAccount">

				<table>
					<th>Current Primary Account</th>
					<tr>
						<td>Account Number :</td>
						<td><%=primAccount %></td>

					</tr>
				</table>
			</div>

			<div class="switchAccount">

				<table>
					<th>Switch Primary</th>
					<tr>
						<td>Account Number :</td>
						<td>
							<form id="01" action="switchPrimary" method="post">
								<select name="primAccount" onChange="this.form.submit()">
								<option><%=primAccount%></option>
									<%long[] account=(long[])customerSession.getAttribute("accounts");
				             		 for(long acc:account){
				             			 if(acc!=primAccount){
				        				%>
									<option><%=acc%></option>
									<%}}%>
								</select>
							</form>
						</td>
					</tr>
					<tr>
						<!-- <td><button form="01" type="submit">switch</button></td> -->
					</tr>
				</table>
			</div>
		</div>
	</div>



</body>
</html>