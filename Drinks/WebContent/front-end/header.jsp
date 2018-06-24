<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="com.manager_account.model.*"%>
<!DOCTYPE html>
<html lang="">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" 	href="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.css" />
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/front-end/datetimepicker/jquery.datetimepicker.full.js"></script>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/mycss.css">
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/front-end/css/grouplist.css">
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>

<style>
.xdsoft_datetimepicker .xdsoft_datepicker {
	width: 300px; /* width:  300px; */
}

.xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
	height: 151px; /* height:  151px; */
}

#map {
	height: 100%;
}

/* Optional: Makes the sample page fill the window. */
html, body {
	height: 100%;
	margin: 0;
	padding: 0;
}
</style>
</head>
<body>
	<nav id="mainNav" class="navbar navbar-default navbar-fixed-top">
		<div class="container-fluid">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target="#myNavbar">
					<span class="icon-bar"></span> <span class="icon-bar"></span> <span
						class="icon-bar"></span>
				</button>
				<div class="logo-wrapper">
					<a class="navbar-brand imglogo"
						href="<%=request.getContextPath()%>/front-end/index.jsp"><img
						src="<%=request.getContextPath()%>/front-end/img/logo-01.png"
						id="logo" style="width: 60px; height: 60px"></a>

				</div>
			</div>
	
			<div class="collapse navbar-collapse" id="myNavbar">
				<div class="nav-center-left">
					<ul class="nav navbar-nav navbar-left">
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">最新消息<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/front-end/announcement/announcement.jsp">最新消息</a></li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">關於我們<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/front-end/aboutus/aboutUs.jsp">關於我們</a></li>
								<li class="divider"></li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#">揪團專區<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a
									href="<%=request.getContextPath()%>/front-end/groupart/grouplist.jsp">揪團首頁</a></li>
								<li class="divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/front-end/groupart/addGroupListForm.jsp">新增揪團</a></li>
								<li class="divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/front-end/groupart/MyGroupList.jsp">我的揪團</a></li>
								<li class="divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/front-end/group.do?action=mem_idlist">我追蹤的揪團</a></li>
								<li class="divider"></li>
								<li><a
									href="<%=request.getContextPath()%>/front-end/groupart/MyAddGroupList.jsp">我參加的揪團</a></li>
							</ul></li>
						<li class="dropdown"><a class="dropdown-toggle drto" data-toggle="dropdown" href="#">會員專區<span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="<%=request.getContextPath()%>/front-end/mem/modify.jsp">修改會員資料</a></li>
								<li class="divider"></li>
								<li><a href="<%=request.getContextPath()%>/front-end/mem/qrcode.jsp">顯示個人QRcode</a></li>
								<li class="divider"></li>
								<li><a href="<%=request.getContextPath()%>/front-end/mem/storedPoint.jsp">點數儲值</a></li>
								<li class="divider"></li>
								<li><a href="<%=request.getContextPath()%>/front-end/mem/withdrawalPoint.jsp">提取點數</a></li>
							</ul>
					   </li>
					</ul>
				</div>

				<ul class="nav navbar-nav navbar-right">
				    <li><a href="<%=request.getContextPath()%>/front-end/ordermaster/listMyOrderMaster.jsp"><span class="glyphicon glyphicon-folder-close"  title="我的訂單"></span></a></li>
					<li><a href="<%=request.getContextPath()%>/front-end/producttract/listMyFaver.jsp"><span class="glyphicon glyphicon-heart"  title="我的最愛"></span></a></li>
					<li><a href="<%=request.getContextPath()%>/front-end/shoppingcart/shoppingcart.jsp">
					    <img class="abc" src="<%=request.getContextPath()%>/front-end/img/shopping-cart.png" style="width: 30px; height: 30px" />
					   </a>
					</li>
					<li><a href="<%=request.getContextPath()%>/front-end/mem/Login.jsp"><span class="glyphicon glyphicon-user"></span></a></li>
		
					<li><a><%=(memVO == null) ? "" : "歡迎:<font color=block>" + memVO.getMem_name() + "</font>您好"%></a>
					<li>
		
			      <ul class="nav navbar-nav navbar-right">
					<li>
					    <form action="<%=request.getContextPath()%>/MemberServlet" method="post">
							<input style="background-color: transparent; border: 0px; width: 106px; height: 52px;"
								   type="submit" value="<%=(memVO == null) ? "登入" : "登出"%>">
							<input type="hidden" name="action" value="InOut">
						</form>
					</li>
					
				</ul>
	
			</div>
		</div>
	</nav>
	<div style="height: 7%;"></div>