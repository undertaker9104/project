<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>
<%
	AnnouncementService announcementSvc = new AnnouncementService();
	List<AnnouncementVO> list = announcementSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>最新消息</title>
<link rel="stylesheet" href="css/bootstrap.css">
<link href="css/front.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>


<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-2">
				<div class="list-group">
			<%@include file="announcement_siderbar.jsp"%>
			
						
				</div>
			</div>
			<div class="col-xs-12 col-sm-9">
				<div class="amos">
					<div class="container">
						<div class="row">
							<div class="col-xs-12 col-sm-6 col-sm-offset-2">
								<p>
									<h3 class="title" align="center">
										| 公告 <small>Announcement</small>
									</h3>
									<section id="info">
										<h2 class="heading">
											Information <a href="#" class="more">more</a>
										</h2>
										<c:forEach var="announcementVO" items="${list}">
										<dl class="info-list">
											<dt>2015-07-28</dt>
											<dd>${announcementVO.ann_des}</dd>
											<dd>
												<img
												src="<%=request.getContextPath()%>/back-end/img4/${announcementVO.ann_id}"
												width="170" height="170">
											</dd>
										</dl>
									</c:forEach> 
								</section>
							</p>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
</div>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script>
		$(function() {
			$('[data-toggle="tooltip"]').tooltip();
		})
	</script>



	<script>
		$(function() {
			$('[data-toggle="popover"]').popover();
		})
	</script>
	<script type="text/javascript">
		$(function() {
			$('.coverflow').css('max-width', $('.coverflow img').width());
		});
	</script>


</body>
</html>