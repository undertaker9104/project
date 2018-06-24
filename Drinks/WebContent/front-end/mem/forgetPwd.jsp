<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>forgetPwd</title>
<style>
 

input[type="email"],input[type="phone"]{padding:5px 15px; border:2px black solid;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }

</style>


</head>

<body>
<jsp:include page="/front-end/header.jsp"/>
<form  method="post" action="/CA101G2/MemberServlet">
<table style="margin-top:7%"; width="648"  align="center" cellpadding="2" cellspacing="2">
    <tr>
      <td height="150" colspan="4" align="center"><h2  style="font-family:微軟正黑體;">找回密碼頁面</h2></td>
    </tr>
    <tr>
				<td colspan="5" align="center"><c:if
						test="${not empty errorMsgs}">
						<font color='red'>請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<ul><li style="color: red; width: 200px">${message}</li></ul>
						</c:forEach>
					</c:if></td>
	</tr>
    <tr>
      <td  width="140" height="75" align="center"><font size="4">請輸入您的Email : </font></td>
      <td colspan="3">
      <input name="email" type="email" id="email" size="50" value="${param.email}" /></td>
    </tr>
    <tr>
      <td  height="75" align="center"><font size="4">請輸入您的電話 : </font></td>
      <td colspan="3">
      <input name="phone" type="phone" id="phone" size="50" value="${param.phone}"/></td>
    </tr>
    <tr>
      <td height="65" align="right">&nbsp;</td>
      <td width="182" height="65" align="right"><input class="btn btn-primary" type="submit" name="button" id="button" value="確認送出" /></td>
      <td width="98" height="65" align="center"><a href="<%=request.getContextPath()%>/front-end/index.jsp">
        <input class="btn btn-primary" type="button" value="取消" />
      </a></td>
      <td width="80" height="65" align="right">&nbsp;</td>
 	<input type="hidden" name="action" value="forgetPwd" />   
    </tr>
  </table>
</form>
<jsp:include page="/front-end/footer.jsp"/>
</body>
</html>
