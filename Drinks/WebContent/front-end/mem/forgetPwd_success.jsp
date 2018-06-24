<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<br>
	<table border='1' cellpadding='5' cellspacing='0' width='400' align="center">
		<tr bgcolor='orange' align='center' valign='middle' height='20'>
			 <td>   
			       <h3>成功 </h3> 
				   <h3>請至您的信箱收取信件取得密碼</h3>
				   <h3>不想等請點擊<a href="<%=request.getContextPath()%>/front-end/mem/Login.jsp">登入</a></h3>
				  <% response.setHeader("Refresh","100;url= "+request.getContextPath()+"/front-end/mem/Login.jsp") ;%> 
			 </td>
		</tr>
	</table>
	
	

</body>
</html>