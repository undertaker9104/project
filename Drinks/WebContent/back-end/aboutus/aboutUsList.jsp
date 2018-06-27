<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.aboutus.model.*"%>

<%
	AboutUsService aboutUsSvc = new AboutUsService();
	List<AboutUsVO> list = aboutUsSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
</head>

<body>

	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
		 <div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						管理介面<small>關於我們管理</small>
					</div>
				</div>
				<br> <br>
				<table class="table table-bordered table-hover">
					<tr>
						<th width="5%">ID</th>
						<th width="30%">關於我們</th>
						<th width="5%">營業時間</th>
						<th width="5%">電話</th>
						<th width="10%">地址</th>
						<th width="1%">修改</th>
					</tr>
					<c:forEach var="aboutUsVO" items="${list}">
						<tr>
							<td>${aboutUsVO.about_id}</td>
							<td>${aboutUsVO.about_des}</td>
							<td>${aboutUsVO.about_time}</td>
							<td>${aboutUsVO.about_phone}</td>
							<td>${aboutUsVO.about_add}</td>
							<td>
								<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/aboutUs.do">
									<input type="submit" value="修改" class="btn btn-primary">
									<input type="hidden" name="about_id" value="${aboutUsVO.about_id}"> 
									<input type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
</html>