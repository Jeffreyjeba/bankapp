<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>REYBANK ADD BRANCH</title>
    <link rel="stylesheet" href="<%=request.getContextPath()%>/css/commoncss.css">
</head>
<body>


    <jsp:include page="/WEB-INF/common/commonHeader.jsp" />
    <jsp:include page="/WEB-INF/common/EleftnavBar.jsp" />

   <div class="content">
        <div class="status">
            <form action="addBranch" method="post">
            	<table>
                     <tr><th><h3>CREATE BRANCH</h3></th></tr>
                     <!-- <tr><td><input name="branchId"min="1" step="1" max="99999"  type="number" placeholder="Branch Id" required></td></tr> -->
                     <tr><td><input maxlength="25" name="branchName" type="text" title="no angle brackets" pattern="[^<>]*" placeholder="BranchName" required></td></tr>
                     <tr><td><input maxlength="40" name="address" type="text" title="no angle brackets" pattern="[^<>]*" placeholder="Address" required></td></tr>
                     <tr><td><button type="submit">create</button></td></tr>
                      <tr><td> <jsp:include page="/WEB-INF/common/error.jsp" /></td></tr>
                </table>
            </form>
        </div>
    </div>
</body>
</html>