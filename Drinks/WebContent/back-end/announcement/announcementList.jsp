<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.announcement.model.*"%>

<%
	AnnouncementService announcementSvc = new AnnouncementService();
    List<AnnouncementVO> list = announcementSvc.getAll();
    pageContext.setAttribute("list",list);
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>公告列表</title>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.css">
</head>
<body>
	<div class="container">
		<div class="row">
		<jsp:include page="/back-end/toolBar.jsp" />
		<jsp:include page="/back-end/breadBar.jsp" />
		<div class="col-xs-12 col-sm-10">
				<div class="page-header text-left">
					<div class="h1">
						管理介面<small>最新消息管理</small>
					</div>
				</div>
		<div>	
			<div class="text-right"><a class="btn btn-success" href="<%=request.getContextPath()%>/back-end/announcement/announcementAdd.jsp">新增</a></div>
		</div>
		<table class="table table-bordered table-hover">
		<tr>
		<th width="1%">ID</th>
		<th width="5%">公告標題</th>
		<th width="10%">公告內容</th>
		<th width="2%">公告時間</th>
		<th width="3%">圖片</th>
		<th width="3%">廣告狀態</th>
		<th width="1%">修改</th>
		<th width="1%">刪除</th>
		</tr>
	
		<c:forEach var="announcementVO" items="${list}">
		<tr>
			<td>${announcementVO.ann_id}</td>
			<td>${announcementVO.ann_title}</td>
			<td>${announcementVO.ann_des}</td>
			<td>${announcementVO.ann_date}</td>
			<td>
			<c:if test="${empty announcementVO.ann_img}">
					<img style="width: 100px; height: 100px;"src="<%=request.getContextPath()%>/front-end/img/null.png">
				</c:if> 
				<c:if test="${! empty announcementVO.ann_img}">
					<img src="data:image/png;base64,${announcementVO.getBase64Image()}" width="100" height="100"/>
				</c:if>
			</td>
			<td>
				<c:if test="${announcementVO.ann_status==0}">           
							  下架
				</c:if> 
				    <c:if test="${announcementVO.ann_status==1}">
				                                上架
				</c:if>
			</td>
			<td>
			  	 <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/announcement.do">
			     <input type="submit" value="修改" class="btn btn-primary">
			     <input type="hidden" name="ann_id" value="${announcementVO.ann_id}">
			     <input type="hidden" name="action"	value="getOne_For_Update"></FORM>
			</td>
			<td>
			  <FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/announcement.do">
			    <input type="submit" value="刪除" class="btn btn-danger">
			    <input type="hidden" name="ann_id" value="${announcementVO.ann_id}">
			    <input type="hidden" name="action"value="delete"></FORM>
			</td>
		</tr>
	</c:forEach>
     
	</table>
	</div>
</body>
</html>