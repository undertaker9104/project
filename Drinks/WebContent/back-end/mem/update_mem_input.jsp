<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>

<%
     MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>會員資料修改</title>

<style>

</style>

<style>
  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    table-layout:fixed;
  word-break:break-all;
  word-warp:break-word;
    border: 0px solid #CCCCFF;
  }
  th{
   text-align:right;
   width:100px;
    padding: 1px;
  }
  
  td{
  padding: 5px;
   width:320px;
  }
  
  .button {
  display: inline-block;
  padding: 10px 20px;
  font-size: 10px;
  cursor: pointer;
  text-align: center;   
  text-decoration: none;
  outline: none;
  color: #fff;
  background-color: blue;
  border: none;
  border-radius: 15px;
  box-shadow: 0 9px #999;
}

.button:hover {background-color: #3e8e41}

.button:active {
  background-color: blue;
  box-shadow: 0 5px #666;
  transform: translateY(4px);
}

</style>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</head>
<body bgcolor='white'>

<table id="table-1" align="center">
	<tr>
		<td height="141" colspan="5" align="center" valign="middle"><img src="<%=request.getContextPath()%>/front-end/pic/333.png" style="width: 30px; height: 30px"><font style="font-family:微軟正黑體;font-weight:bold;font-size:2em">會員資料修改</font>
	</td></tr>
</table>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" enctype="multipart/form-data">
<table  align="center">
	<tr>
		<th>會員編號:</th>
		<td><%=memberVO.getMem_id()%></td>
	</tr>
	<tr>
		<th>會員姓名:</th>
		<td><%=memberVO.getMem_name()%></td>
	</tr>
	<tr>
		<th>會員E-mail:</th>
		<td><%=memberVO.getMem_email()%></td>
	</tr>
	<tr>
		<th>會員密碼:</th>
		<td><%=memberVO.getMem_pwd()%></td>
	</tr>
	<tr>
		<th>會員性別:</th>
		<td><%=memberVO.getMem_sex()%></td>
	</tr>
	<tr>
		<th>會員生日:</th>
		<td><%=memberVO.getMem_birth()%></td>
	</tr>
	<tr>
		<th>會員密碼:</th>
		<td><%=memberVO.getMem_pwd()%></td>
	</tr>
	<tr>
		<th>會員電話:</th>
		<td><%=memberVO.getMem_phone()%></td>
	</tr>
	<tr>
		<th>會員地址:</th>
		<td><%=memberVO.getMem_ads()%></td>
	</tr>
	<tr>
		<th>會員點數:</th>
		<td><input type="TEXT" name="mem_point" size="45"	value="<%=memberVO.getMem_point()%>" /></td>
	</tr>
	<tr>
		<th>會員積分:</th>
		<td><input type="TEXT" name="mem_int" size="45"	value="<%=memberVO.getMem_int() %>" /></td>
	</tr>
	<tr>
		<th>會員狀態</th>
		<td><input type="TEXT" name="mem_acc_status" size="25"	value="<%=memberVO.getMem_acc_status()%>" />(0:正常  1:停權)</td>
	</tr>
	<tr>
		<th style="
    height: 273.403;
">會員圖片 :</th>
		<td colspan="3" rowspan="4" align="center"><input type="file"
					name="mem_pic" accept="image/*" onchange="loadFile(event)"
					value="${param.mem_pic}" /> <img id="output" src="<% out.print("data:image/png;base64, "+ new String(Base64.getEncoder().encode(memberVO.getMem_pic())));%>"/></td>
	</tr>
	<tr>
<td colspan="2">

<input type="hidden" name="action" value="update">
<input type="hidden" name="mem_id"  value="<%=memberVO.getMem_id() %>">
<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
<input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>">  <!--只用於:istAllEmp.jsp-->
<input class="button" type="submit" value="送出修改"></td></tr></FORM>
 </table>
</body>

