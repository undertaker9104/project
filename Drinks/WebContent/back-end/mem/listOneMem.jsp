<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>

<%MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");%>

<html>
<head>
<title>會員資料</title>

<style>
  table#table-1 {

	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }
</style>

<style>
  table {

	background-color: white;
	margin-top: 5px;
	margin-bottom: 5px;
  }
  table, th, td {
  table-layout:fixed;
  word-break:break-all;
  word-warp:break-word;
    border: 1px solid #CCCCFF;
  }
  th, td {
	width:300px;
    padding: 5px;
    text-align: center;
  }
  
  .button-secondary {
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
<body bgcolor='white'>

<table id="table-1">
	<tr><td>
		 <h3>會員資料 </h3>
	</td></tr>
</table>

<table>
	<tr>
		<th>會員編號</th>
		<th>會員姓名</th>
		<th>會員E-mail</th>
		<th>會員密碼</th>
		<th>會員性別</th>
		<th>會員生日</th>
		<th>會員電話</th>
		<th>會員地址</th>
		<th>會員點數</th>
		<th>會員積分</th>
		<th>會員狀態</th>
		<th>會員圖片</th>
	</tr>
	<tr>
		<td>${memberVO.mem_id}</td>
		<td><%=memberVO.getMem_name()%></td>
		<td><%=memberVO.getMem_email()%></td>
		<td><%=memberVO.getMem_pwd()%></td>
		<td><%=memberVO.getMem_sex()%></td>
		<td><%=memberVO.getMem_birth()%></td>
		<td><%=memberVO.getMem_phone()%></td>
		<td><%=memberVO.getMem_ads()%></td>
		<td><%=memberVO.getMem_point()%></td>
		<td><%=memberVO.getMem_int()%></td>
		<td><%=memberVO.getMem_acc_status()%></td>
	    <td><img id="output" src="<% out.print("data:image/png;base64, "+ new String(Base64.getEncoder().encode(memberVO.getMem_pic())));%>"/></td>
		<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/MemberServlet" style="margin-bottom: 0px;">
			     <input class="button-secondary" type="submit" value="修改"> 
			     <input type="hidden" name="mem_id" value="${memberVO.mem_id}">
			     <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
			     <input type="hidden" name="action"	    value="getOne_For_Update"></FORM>
			</td>
	</tr>

</table>

</body>
</html>