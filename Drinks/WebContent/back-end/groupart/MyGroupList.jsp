<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.group_art.model.*"%>
<%@page import="com.mem.model.*"%>>
<%@page import="java.util.List"%>
<%@page import="java.io.*"%>
<jsp:include page="/front-end/header.jsp" />
<%
	GroupService grouSvc = new GroupService();
	List<Group_ArtVO> list = grouSvc.getAll();
	pageContext.setAttribute("list", list);
%>
<html>
<header></header>
<br><br><br><br>
<body>
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">�޲z����<small>���Τ峹�޲z</small></div>
				</div>
				<table class="table table-hover">
				<thead>
					<tr>
						<th>�峹�W��</th>
						<th>���Ψ�����</th>
						<th>���Ϊ��B</th>
						<th>�峹�Ϥ�</th>
						<th>�~�e�a�}</th>
						<th>�o�e���~�a�}</th>
						<th>�峹���A</th>
						<th>�d��</th>
						<th>�ק�</th>
						<th>�R��</th>
					</tr>
				</thead>
				<tbody>
					<%@ include file="/front-end/page1.file"%>
					<c:if test="${list ne null}">
						<c:forEach var="group_ArtVO" items="${list}"
							begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr>
									<td>${group_ArtVO.art_name}</td>
									<td>${group_ArtVO.exp_date}</td>
									<td>${group_ArtVO.grou_price}</td>
									<td><c:if test="${empty group_ArtVO.art_img}">
											<img style="width: 100px; height: 100px;"
												src="<%=request.getContextPath()%>/front-end/img/dddd.png">
										</c:if> 
										<c:if test="${! empty group_ArtVO.art_img}">
											<img style="width: 100px; height: 100px;"
												src="data:image/jpg;base64,${group_ArtVO.getBase64Image()}">
										</c:if></td>
										<c:if test="${group_ArtVO.ship_locat ne null}">
											<td>${group_ArtVO.ship_locat}</td>
										</c:if>
										<c:if test="${group_ArtVO.ship_locat eq null}">
											<td>�~�a</td>
										</c:if>
									<td>${group_ArtVO.send_locat}</td>
									<c:if test="${group_ArtVO.grou_status eq 0}">
											<td>�۶Ҥ�</td>
										</c:if>
										<c:if test="${group_ArtVO.grou_status eq 1}">
											<td>�w����</td>
										</c:if>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do"
											style="margin-bottom: 0px;">
											<input class="btn btn-primary" type="submit" value="�d��"> <input type="hidden"
												name="grou_id" value="${group_ArtVO.grou_id}">
											<input type="hidden" name="ord_id" value="${group_ArtVO.ord_id}">
											<input type="hidden" name="mem_id" value="${group_ArtVO.mem_id}">
											<input type="hidden" name="action" value="enter">
										</FORM></td>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/grouplist/grouplist.do"
											style="margin-bottom: 0px;">
											<input class="btn btn-warning" type="submit" value="�ק�"> <input type="hidden"
												name="grou_id" value="${group_ArtVO.grou_id}"> <input
												type="hidden" name="action" value="getOneForUpdateForBackend">
												<input type="hidden" name="ord_id" value="${group_ArtVO.ord_id}">
												<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			     								<input type="hidden" name="whichPage"	value="<%=whichPage%>">  
										</FORM></td>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/grouplist/grouplist.do"
											style="margin-bottom: 0px;">
											<input class="btn btn-danger" type="submit" value="�R��"> <input type="hidden"
												name="grou_id" value="${group_ArtVO.grou_id}"> <input
												type="hidden" name="action" value="delete">
												<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--�e�X�����������|��Controller-->
			    							    <input type="hidden" name="whichPage"	value="<%=whichPage%>">  
										</FORM></td>
								</tr>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<%@ include file="/front-end/page2.file"%>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
</body>
<jsp:include page="/front-end/footer.jsp" />
</html>