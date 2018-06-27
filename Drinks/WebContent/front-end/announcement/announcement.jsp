<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>

<%
	AnnouncementService announcementSvc = new AnnouncementService();
	List<AnnouncementVO> list = announcementSvc.getAllDesc();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>公告</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/announcement.css" media="all">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/front.css" type="text/css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
</head>
<body>
		<jsp:include page="/front-end/header.jsp" />

	<br>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-6 col-sm-offset-3">
				<p>
				<h3 class="title" align="center">
					| 公告 <small>Announcement</small>
				</h3>
				<section id="info">
				<h2 class="heading">
					Information
				</h2>
				<c:forEach var="announcementVO" items="${list}">
					<dl class="info-list">
						<dt>${announcementVO.ann_date}</dt>
						<dd>${announcementVO.ann_title}</dd>
						<dd>
							<c:if test="${empty announcementVO.ann_img}">
								<img style="width: 170px; height: 170px;"src="<%=request.getContextPath()%>/front-end/img/null.png">
							</c:if> 
							<c:if test="${! empty announcementVO.ann_img}">
								<img src="data:image/png;base64,${announcementVO.getBase64Image()}" width="50%" height="50%"/>
							</c:if>
						</dd>
						<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/announcementFront.do">
						<input type="submit" value="詳情" class="btn btn-danger" >
						    <input type="hidden" name="ann_id" value="${announcementVO.ann_id}">
						    <input type="hidden" name="action" value="getOne_For_Detail">
						</FORM>
					</dl>
				</c:forEach> 
				</section>
				</p>
			</div>
		</div>
	</div>
<br>
<br>
<jsp:include page="/front-end/footer.jsp" />

</body>
</html>