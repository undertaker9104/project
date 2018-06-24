<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
<style>

input[type="password"],input[type="text"]{padding:5px 15px; border:2px black solid;
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
		method="post">
		<table width="538" align="center" cellpadding="2" cellspacing="2">
			<tr>
				<td height="117" colspan="2" align="center"><h2>員工登入頁面</h2></td>
			</tr>
			<tr>
				<td colspan="2" align="center"><c:if
						test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<ul><li style="color: red; width: 200px">${message}</li></ul>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td width="163" height="48" align="center" valign="middle">員工姓名
					:</td>
				<td width="353"><input name="emp_name" type="text" size="50" value="${param.emp_name}"/></td>
			</tr>
			<tr>
				<td height="48" align="center" valign="middle">密碼 :</td>
				<td><input name="accpw" type="password" size="50" value="${param.accpw}"/></td>
			</tr>
			<tr>
				<td height="48" align="center" valign="middle"><input
					type="checkbox" name="checkbox" /> 記住我 ?</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td height="48" align="center"><a href="<%=request.getContextPath()%>/back-end/index_back.jsp"><input class="button-error	" type="button"
					name="back" value="回首頁" /></a>	</td>
				<td><input class="button-secondary" type="submit" name="Login" value="登入" />
				    <input type="hidden" name="action" value="login">
				</td>
			</tr>
			
		</table>
	</form>
</body>
</html>