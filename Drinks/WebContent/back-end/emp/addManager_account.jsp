<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.manager_account.model.*"%>

<%
	Manager_accountVO manager_accountVO = (Manager_accountVO) request.getAttribute("manager_accountVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
<title>員工資料新增</title>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
	
</script>
<style>
input[type="email"],input[type="text"]{padding:5px 15px; border:2px black solid;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }


 .button-error,.button-secondary {
  background: #3498db;
  background-image: -webkit-linear-gradient(top, #3498db, #2980b9);
  background-image: -moz-linear-gradient(top, #3498db, #2980b9);
  background-image: -ms-linear-gradient(top, #3498db, #2980b9);
  background-image: -o-linear-gradient(top, #3498db, #2980b9);
  background-image: linear-gradient(to bottom, #3498db, #2980b9);
  -webkit-border-radius: 28;
  -moz-border-radius: 28;
  border-radius: 28px;
  font-family: Arial;
  color: #ffffff;
  font-size: 5px;
  padding: 10px 20px 10px 20px;
  text-decoration: none;
}

 .button-error{
 background: red;
 }

 .button-secondary:hover {
  background: #3cb0fd;
  background-image: -webkit-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -moz-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -ms-linear-gradient(top, #3cb0fd, #3498db);
  background-image: -o-linear-gradient(top, #3cb0fd, #3498db);
  background-image: linear-gradient(to bottom, #3cb0fd, #3498db);
  text-decoration: none;
}



</style>
</head>

<body>
	<form action="<%=request.getContextPath()%>/Manager_accountServlet"
		method="post" enctype="multipart/form-data">
		<table width="648" height="512" border="0" align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				<td height="135" colspan="2" align="center"><h2>員 工 資 料 新 增</h2></td>

			</tr>
			<tr>
				<td colspan="5" align="center"><c:if
						test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<ul>
								<li style="color: red; width: 200px">${message}</li>
							</ul>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td width="196" align="center">員 工 姓 名 :</td>
				<td align="center"><label for="emp_name"></label> <input
					name="emp_name" type="text" id="emp_name" size="50"
					value="${param.emp_name}" /></td>
			</tr>
			<tr>
				<td align="center">員 工 E-mail :</td>
				<td align="center"><input name="emp_email" type="email" id="emp_email"
					size="50" value="${param.emp_email}" /></td>
			</tr>

			<tr>
				<td width="195" height="167" align="center">員 工 照 片 :</td>
				<td colspan="2" style="padding-top: 15px;"><input type="file" name="emp_img"
					accept="image/*" onchange="loadFile(event)"
					value="${param.emp_img}" /> <img id="output" /></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="hidden" name="action" value="add">
				<input class="button-secondary" type="submit" id="button" value="新增員工">
				<a href="<%=request.getContextPath()%>/back-end/backIndex.jsp"><input class="button-error" type="button" id="button" value="回上頁"></a> 
				
				</td>
			</tr>
		</table>
	</form>
</body>
</html>