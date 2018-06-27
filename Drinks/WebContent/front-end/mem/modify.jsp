<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>

<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Modify</title>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
<style>
input[type="text"],input[type="phone"]{	padding:5px 15px; border:2px black solid;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }
</style>
</head>

<body>
<jsp:include page="/front-end/header.jsp"/>

	<form action="<%=request.getContextPath()%>/MemberServlet" method="post" enctype="multipart/form-data">
		<table style="margin-top: 40px"; width="1088" height="603"  align="center"
			cellpadding="2" cellspacing="2">
			<tr>
				 <td height="141" colspan="5" align="center" valign="middle"><img src="<%=request.getContextPath()%>/front-end/pic/333.png" style="width: 30px; height: 30px"><font style="font-family:微軟正黑體;font-weight:bold;font-size:2em">修改會員資料</font>
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
				<td colspan="3" rowspan="4" align="center"><input type="file"
					name="mem_pic" accept="image/*" onchange="loadFile(event)"
					value="${param.mem_pic}" /> <img id="output" src="<% out.print("data:image/png;base64, "+ new String(Base64.getEncoder().encode(memVO.getMem_pic())));%>"/></td>
				
				<td width="188" height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">會員名稱
					:</font></td>
				<td width="466" align="left"><%=memVO.getMem_name()%></td>
			</tr>
			<tr>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">EMAIL :</font></td>
				<td align="left"><%=memVO.getMem_email()%></td>
			</tr>
			<tr>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">性別 :</font></td>
				<td align="left"><%=memVO.getMem_sex()%></td>
			</tr>
			<tr>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">密碼 :</font></td>
				<td align="left"><input name="password" type="text" value="<%=memVO.getMem_pwd()%>"
					id="password" size="65" /></td>
			</tr>
			<tr>
				<td width="119" height="66" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">會員ID</font>
					:</td>
				<td colspan="2" align="left"><%=memVO.getMem_id()%></td>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">生日 :</font></td>
				<td align="left"><%=memVO.getMem_birth()%></td>
			</tr>
			<tr>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">剩餘點數 :</font></td>
				<td width="201" height="70" align="left"><%=memVO.getMem_point()%></td>
				<td width="68" height="70"><a href="storedPoint.jsp"><input type="button" value="儲值點數" /></a></td>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">手機號碼 :</font></td>
				<td height="70" align="left"><input name="phone" type="phone"
					id="phone" size="65" value="<%=memVO.getMem_phone()%>"/></td>
			</tr>
			<tr>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">累積積分 :</font></td>
				<td height="70" colspan="2" align="left"><%=memVO.getMem_int()%></td>
				<td height="70" align="center" valign="middle"><font style="font-family:微軟正黑體;font-weight:bold;">地址 :</font></td>
				<td height="70" align="left"><input name="address" type="text"
					id="address" size="65" value="<%=memVO.getMem_ads()%>"/></td>
			</tr>
			<tr>
				<td height="60" colspan="4" align="center" valign="middle">&nbsp;</td>
				<td height="60" align="center">
				<input class="btn btn-primary" type="submit" name="button3" id="button3" value="確認修改" />
			    <a href="<%=request.getContextPath()%>/front-end/index.jsp"><input class="btn btn-primary" type="button" value="回首頁" /></a>
				<input type="hidden" name="action" value="Modify"></td>
			</tr>
		</table>
	</form>
		<jsp:include page="/front-end/footer.jsp"/>
		
<!-- 			<script src="https://cdn.jsdelivr.net/npm/sweetalert2"></script> -->
<!-- 	<script> 
	
// $("#button3").on('click',function(){
// 	var phoneReg =  new RegExp("^09[0-9]{8}$");
// 	var password = $("#password").val();
// 	var phone =$("#phone").val();
// 	var address=$("#address").val();
// 	if(password.trim()==''){
// 		$('#password').focus();
// 		swal({
// 			  title: '請輸入密碼!',
// 			  animation: false,
// 			  customClass: 'animated tada'
// 			});
// 	}else if(phone.trim()==''){
// 		$('#phone').focus();
// 		swal({
// 			  title: '請輸入手機號碼!',
// 			  animation: false,
// 			  customClass: 'animated tada'
// 			});
// 	}else if (!phoneReg.test(phone)){
// 		$('#phone').focus();
// 		swal({
// 			  title: '手機格式輸入錯誤!',
// 			  animation: false,
// 			  customClass: 'animated tada'
// 			});
// 	}else if(address.trim()==''){
// 		$('#address').focus();
// 		swal({
// 			  title: '地址不可為空!',
// 			  animation: false,
// 			  customClass: 'animated tada'
// 			});
// 	}else{
// 			  $.ajax({
<%-- 				  url: "<%=request.getContextPath()%>/MemberServlet", --%>
// 				  data: {
// 					  "action":"Modify",
// 				  	  "phone": phone,
// 				  	  "password":password,
// 				  	  "address":address,
// 				  },
// 				  dataType: "json",
// 				  cache:false,
// 				  type:"post",
				  
// 				  success:function(data){

				
// 				  },
// 				  error:function(response,textStatus,errorThrown){
// 					  debugger;
// 					  console.log(textStatus);
// 				  }
// 			  })
// 			}
		
// 		})
</script> -->
</body>
</html>