<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.group_art.model.*"%>
<%@page import="com.mem.model.*"%>>
<%@page import="com.group_track.model.*"%>
<%@page import="java.util.List"%>
<%@page import="java.io.*"%>
<jsp:include page="/front-end/header.jsp" />
<jsp:useBean id="list" scope="session" type="java.util.List<Group_trackVO>"/>
<%
	MemberVO memVO1 = (MemberVO) session.getAttribute("memVO");
	String memid = memVO1.getMem_id();	
	pageContext.setAttribute("mem_id2", memid);
	GroupService grouSvc = new GroupService();
	List<Group_ArtVO> list2 = grouSvc.getAllForTrack();
	pageContext.setAttribute("list2", list2);
%>
<%@ include file="/front-end/page1.file"%>
<div class="container" style="margin-top:5%;">
	<div class="row">
		<div class="col-xs-12 col-sm-12 text-center">
			<h1><i>�l�ܪ�����</i></h1>
		</div>
		<c:if test="${empty list2}">
			<h1 class="text-center">�S���Ӹ��</h1>
		</c:if>
		<div class="col-xs-12 col-sm-12">
			<table class="table table-hover" style="border: 1px solid black">
				<thead>
					<tr>
						<th>���ΦW��</th>
						<th>���Ψ�����</th>
						<th>���Ϊ��B</th>
						<th>�峹�Ϥ�</th>
						<th>�o�e���~�a�}</th>
						<th>���Ϊ��A</th>
						<th>�ѥ[����</th>
						<th>�����l��</th>
					</tr>
				</thead>
				<tbody>
					<c:if test="${list2 ne null}">
						<c:forEach var="group_ArtVO" items="${list2}"  varStatus="varStatus1">
							<c:forEach var="group_trackVO" items="${list}" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>" varStatus="varStatus">
								<c:if test="${group_ArtVO.grou_id eq group_trackVO.grou_id}">
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
										<td>${group_ArtVO.send_locat}</td>
										<c:if test="${group_ArtVO.grou_status eq 0}">
											<td>�۶Ҥ�</td>
<%-- 											<td><FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do" --%>
<!-- 											style="margin-bottom: 0px;"> -->
<!-- 											<input type="submit" value="�ѥ[����"> <input type="hidden" -->
<%-- 												name="grou_id" value="${group_trackVO.grou_id}"> --%>
<%-- 												<input type="hidden" name="mem_id" value="${group_trackVO.mem_id}"> --%>
<!-- 												<input type="hidden" name="action" value="addgroup">  -->
<!-- 										</FORM></td> -->
											<td>
											<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do"
											        style="margin-bottom: 0px;">
											    <input class="btn btn-primary" type="submit" value="�d��">
											    <input type="hidden" name="grou_id" value="${group_ArtVO.grou_id}">
											    <input type="hidden" name="ord_id" value="${group_ArtVO.ord_id}">
											    <input type="hidden" name="mem_id" value="${mem_id2}">
											    <input type="hidden" name="action" value="enter">
										    </FORM>
										   </td>
										</c:if>
										<c:if test="${group_ArtVO.grou_status eq 1}">
											<td>�w����</td>
											<td></td>
										</c:if>
										<c:if test="${group_ArtVO.grou_status eq 2}">
											<td>�w����</td>
											<td></td>
										</c:if>
									<td><FORM METHOD="post"
											ACTION="<%=request.getContextPath()%>/front-end/group.do"
											style="margin-bottom: 0px;">
											<input class="btn btn-danger" type="submit" value="�����l��">
											<input type="hidden" name="grou_id" value="${group_trackVO.grou_id}"> 
											<input type="hidden" name="action" value="1">
											<input type="hidden" name="mem_id" value="${group_trackVO.mem_id}">  
										</FORM></td>
										</tr>
									</c:if>
								</c:forEach>
						</c:forEach>
					</c:if>
				</tbody>
			</table>
			<%@ include file="/front-end/page2.file"%>
		</div>
	</div>
</div>

<jsp:include page="/front-end/footer.jsp" />