<%@page import="org.json.JSONObject"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="org.json.JSONArray"%>
<%@page import="org.json.JSONObject"%>
<!DOCTYPE html>
<html>
   <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK DEBIT</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>

    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
	<jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />
	
	<%JSONObject branch=(JSONObject) request.getAttribute("branch");
	  %>
	  
	
	
	
	
	 <div class="content">
        <div class="profile">
            <table>
                <tr>
                    <td>Branch Id</td>
                    <td><%=branch.getInt("BranchId")%></td>
                </tr>
                <tr>
                    <td>Ifsc Code</td>
                    <td><%=branch.getString("IfscCode")%></td>
                </tr>
                <tr>
                    <td>Branch Name</td>
                    <td><%=branch.getString("BranchName")%></td>
                </tr>
                <tr>
                    <td>Address</td>
                    <td><%=branch.getString("Address")%></td>
                </tr>
		<%if(request.getSession().getAttribute("auth").equals("admin")){
			%>
                <tr>
                    <td>
                        <select>
                            <option>1</option>
                            <option>2</option>
                        </select>
                    </td>
                    <td>
                        <a href="">switch</a>
                    </td>
                </tr>
                <%} %>

            </table>

          

        </div>
    </div>

</body>
</html>