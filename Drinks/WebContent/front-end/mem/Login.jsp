<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% 
MemberVO memVO = (MemberVO) request.getAttribute("memVO");
%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>



<style>
input {
padding:5px 15px; background:#ccc; border:0 none;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px;
}

</style>

</head>

<body>
<jsp:include page="/front-end/header.jsp"/>
	<form action="<%=request.getContextPath()%>/MemberServlet"
		method="post">
		<table style="margin-top: 120px"; width="538" align="center" cellpadding="2" cellspacing="2">
			<tr>
				<td height="117" colspan="2" align="center"><h2>會員登入頁面</h2></td>
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
				<td width="163" height="48" align="center" valign="middle">電子信箱
					:</td>
				<td width="353"><input name="email" type="email" size="50" value="${param.email}" id="mail" /></td>
			</tr>
			<tr>
				<td height="48" align="center" valign="middle">密碼 :</td>
				<td><input name="password" type="password" size="50" valuse="${param.password}" id="password"  /></td>
			</tr>
			<tr>
				<td height="48" align="center" valign="middle"><input
					type="checkbox" name="checkbox" /> 記住我 ?</td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td height="48" colspan="2" align="center"><input class="btn btn-primary" type="submit" name="Login" value="登入" />
				    <input type="hidden" name="action" value="login">
					<a href="<%=request.getContextPath()%>/front-end/mem/register.jsp"><input class="btn btn-primary" type="button"
						name="Register" value="註冊" /></a></td>
			</tr>
			<tr>
				<td height="51" colspan="2">----------------------------------------------------------------------------------------------------</td>
			</tr>
			<tr>
				<td height="37" colspan="2" align="center"><a href="<%=request.getContextPath()%>/front-end/mem/forgetPwd.jsp">忘記密碼 ?</a ></td>
			</tr>
		</table>
	</form>
	<jsp:include page="/front-end/footer.jsp"/>
	<button type="submit" class="btn" onclick="mem1()">趙六</button>
<button type="submit" class="btn"onclick="mem2()">王五</button>
<jsp:include page="/front-end/footer.jsp"/>
</body>
<Script>
function mem1() {
document.getElementById("mail").value ="twm0983527254@gmail.com";
document.getElementById("password").value ="1";
}
function mem2(){
document.getElementById("mail").value ="ca101g2db@gmail.com";
document.getElementById("password").value ="1";
}
</Script>
</body>
</html>