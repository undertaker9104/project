<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%
Manager_accountVO manager_accountVO = (Manager_accountVO) session.getAttribute("manager_accountVO");
System.out.print(manager_accountVO.getMan_acc_id());
Manager_account_authorityService manager_account_authoritySvc = new Manager_account_authorityService();
List<String> list1 = manager_account_authoritySvc.getOneManager_account_authority(manager_accountVO.getMan_acc_id());
if(!list1.contains("AC000001")){
	   out.println("<script language=\"javascript\">");
	   out.println("alert(\"您尚未有管理權限\");");
	   out.println("window.history.back(-1);");  
	   out.println("</script>");
}
%>
<%
	MemberVO memberVO = (MemberVO) request.getAttribute("memberVO");
%>
<%
	MemberService memberSvc = new MemberService();
	List<MemberVO> list = memberSvc.getAll();
	pageContext.setAttribute("list", list);
%>

<html>
<head>

<title>所有會員資料</title>

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
	table-layout: fixed;
	word-break: break-all;
	word-warp: break-word;
	border: 1px solid #CCCCFF;
}

th, td {
	width: 300px;
	padding: 5px;
	text-align: center;
}

.button-error, .button-secondary {
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

.button-error {
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

tr:nth-child(even) {background-color:#D3D3D3;}	
</style>

</head>
<body bgcolor='white'>
	<div class="container-fluid">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-10">
				<div class="page-header text-left">
					<div class="h1">
						<small>管理介面</small>
					</div>
				</div>
			
				
							<h3><font style="font-family:微軟正黑體;font-weight:bold; font-weight:1.5em; font-size:2em" >所有會員資料</font></h3>
			

				<%-- 錯誤表列 --%>
				<c:if test="${not empty errorMsgs}">
					<font style="color: red">請修正以下錯誤:</font>
					<ul>
						<c:forEach var="message" items="${errorMsgs}">
							<li style="color: red">${message}</li>
						</c:forEach>
					</ul>
				</c:if>

				<table>
					<tr style="
    height: 51.979166px;
    width: 31.979166px;
">
						<th>會員編號</th>
						<th>會員姓名</th>
						<th>會員信箱</th>
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
					<%@ include file="pages/page1.file"%>
					<c:forEach var="memberVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr ${(memberVO.mem_id==param.mem_id) ? 'bgcolor=#CCCCFF':''}>
							<!--將修改的那一筆加入對比色而已-->
							<td>${memberVO.mem_id}</td>
							<td>${memberVO.mem_name}</td>
							<td>${memberVO.mem_email}</td>
							<td>${memberVO.mem_pwd}</td>
							<td>${memberVO.mem_sex}</td>
							<td>${memberVO.mem_birth}</td>
							<td>${memberVO.mem_phone}</td>
							<td>${memberVO.mem_ads}</td>
							<td>${memberVO.mem_point}</td>
							<td>${memberVO.mem_int}</td>
							<td>${memberVO.mem_acc_status}</td>
							<td><img src="data:image/png;base64,${memberVO.getImg(memberVO.mem_pic)}" /></td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/MemberServlet"
									style="margin-bottom: 0px;">
									<input class="button-secondary pure-button " type="submit"
										value="修改"> <input type="hidden" name="mem_id"
										value="${memberVO.mem_id}"> <input type="hidden"
										name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/MemberServlet"
									style="margin-bottom: 0px;">
									<input class="button-error pure-button " type="submit"
										value="刪除"> <input type="hidden" name="mem_id"
										value="${memberVO.mem_id}"> <input type="hidden"
										name="requestURL" value="<%=request.getServletPath()%>">
									<!--送出本網頁的路徑給Controller-->
									<input type="hidden" name="whichPage" value="<%=whichPage%>">
									<!--送出當前是第幾頁給Controller-->
									<input type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
				<%@ include file="pages/page2.file"%>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>