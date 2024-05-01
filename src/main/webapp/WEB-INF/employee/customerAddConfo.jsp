<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK USER CONFO</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>
    
    <%
	JSONObject profile=(JSONObject) request.getAttribute("profile");	
	%>

	<jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />


    <div class="content">
        <div class="profile">
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
    </div>


  
</body>
</html>