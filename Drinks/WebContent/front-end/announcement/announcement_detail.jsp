<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementVO announcementVO = (AnnouncementVO) request.getAttribute("announcementVO"); //EmpServlet.java (Concroller), 存入req的empVO物件 (包括幫忙取出的empVO, 也包括輸入資料錯誤時的empVO物件)
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公告詳情 </title></head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/front.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css"">
<body>

		<jsp:include page="/front-end/header.jsp" />

	<br><br>
	<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-6 col-sm-offset-3">
					<div class="page-header">
					  <h1 align="center" class="title">${announcementVO.ann_title}</h1>
					  <h6 align="right">${announcementVO.ann_date}</h6>
					</div>				
					<hr/>
					<c:if test="${empty announcementVO.ann_img}">
						<img src="<%=request.getContextPath()%>/front-end/img/null.png">					
					</c:if> 
					<c:if test="${! empty announcementVO.ann_img}">
						<img src="data:image/jpg;base64,${announcementVO.getBase64Image()}" width="100%" height="100%">
					</c:if>
						<p style="font-size:20px">${announcementVO.ann_des}</p>
				</div>
			</div>
	</div>

</body>
</html>
