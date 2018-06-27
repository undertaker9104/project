<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<style>
input[type="email"],input[type="text"],input[type="phone"],input[type="password"]{
padding:5px 15px; background:#ccc; border:0 none;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px;
}
</style>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</head>

<body>
<jsp:include page="/front-end/header.jsp"/>
	<form  action="<%=request.getContextPath()%>/MemberServlet" method="post" enctype="multipart/form-data" style="height:150%">
		<table style="margin-top:2% ; width:589 " align="center" cellpadding="2" cellspacing="2">
			<tr>
				<td colspan="3" align="center"><img src="<%=request.getContextPath()%>/front-end/pic/register.png" style="
    height: 180px; width: 300px;"></td>
			</tr>
			<tr>	
				<td colspan="3" align="center"><c:if
						test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<ul><li style="color: red; width: 200px">${message}</li></ul>
						</c:forEach>
					</c:if></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right"  style="font-family:微軟正黑體;    font-size: 1.5em;">使用者名稱 :</td>
				<td colspan="2"><label for="username"></label> <input
					name="username" type="text" id="username" size="50" value="${param.username}"/></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">E-mail :</td>
				<td colspan="2"><label for="email"></label> <input name="email"
					type="email" id="email" size="50" value="${param.email}"/></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">密碼 :</td>
				<td colspan="2"><label for="password"></label> <input
					name="password" type="password" id="password" size="50" value="${param.password}"/></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">性別 :</td>
				<td colspan="2" style="
    padding-left: 20px;"><input type="radio" name="sex"
					id="male" value="男" value="${param.sex}"/><font size="4" style="
    padding-left: 10px;">男</font> <input type="radio" name="sex"
					id="female" value="女" value="${param.sex}" /><font size="4" style="
    padding-left: 10px;">女</font></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">生日 :</td>
				<td colspan="2"><label for="birthday"></label> <input
					name="birthday" type="date" id="birthday" size="50" value="${param.birthday}"/></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">手機號碼 :</td>
				<td colspan="2"><label for="phone"></label> <input name="phone"
					type="phone" id="phone" size="50" value="${param.phone}"/></td>
			</tr>
			<tr>
				<td width="195" height="45" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">地址 :</td>
				<td colspan="2"><label for="address"></label> <input
					name="address" type="text" id="address" size="50" value="${param.address}"/></td>
			</tr>
			<tr>
				<td width="195" height="167" align="right" style="font-family:微軟正黑體;    font-size: 1.5em;">上傳大頭貼 :</td>
				<td colspan="2"><input type="file" name="mem_pic" accept="image/*"
					onchange="loadFile(event)" value="${param.mem_pic}" style="
    padding-left: 10px;" /> <img id="output" /></td>
			</tr>
			<tr>
				<td  colspan="2" align="center">
				      <input class="btn btn-primary" type="submit"  name="Register" value="確定註冊" />
				      <input type="hidden" name="action" value="Register">
				
				
				<input class="btn btn-primary" type="reset"
					name="reset" id="reset" value="重新填寫" />
				<a href="<%=request.getContextPath()%>/front-end/index.jsp"><input class="btn btn-primary"
						type="button" value="取消註冊" /></a></td>
			</tr>
		</table>
	</form>
		<jsp:include page="/front-end/footer.jsp"/>	
</body>
</html>
