<%@ page contentType="text/html; charset=UTF-8" pageEncoding="Big5"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@page import="com.group_art.model.*"%>
<%@page import="com.group_track.model.*"%>
<%@page import="com.mem.model.*"%>
<%@page import="java.util.*"%>
<%@page import="java.io.*"%>
<%!int i = 1;%>
<jsp:include page="/front-end/header.jsp" />
<jsp:include page="/front-end/SlideBar.jsp" />

<%
	GroupService groupSvc = new GroupService();
	List<Group_ArtVO> list = null;
	//取得當前會員的追蹤文章
	Group_trackService group_trackSvc = new Group_trackService();
	MemberVO memVO1 = (MemberVO) session.getAttribute("memVO");
	String mem_id = memVO1.getMem_id();
	List<Group_trackVO> trackList = group_trackSvc.getMem_id(mem_id);
	pageContext.setAttribute("tracklist", trackList);
	pageContext.setAttribute("mem_id2", mem_id);
	//判斷是不是搜尋
	if (request.getAttribute("search") == null) {
		list = groupSvc.getAll();
		pageContext.setAttribute("list", list);
	} else {
		list = (List<Group_ArtVO>) request.getAttribute("search");
		pageContext.setAttribute("list", list);
	}
	
	
%>
<jsp:useBean id="memSvc" scope="page"
	class="com.mem.model.MemberService" />
<jsp:useBean id="grou_trackSvc" scope="page"
	class="com.group_track.model.Group_trackService" />
<jsp:useBean id="grou_orderSvc" scope="page"
	class="com.group_order_detail.model.Group_order_detailService" />
<div class="col-xs-12 col-sm-12 a-right">
	<!--搜尋-->
	<div class="row">
		<div class="col-sm-3 col-md-offset-9">
			<form action="<%=request.getContextPath()%>/grouplist/grouplist.do"
				class="search-form  text-right" method="post">
				<div class="form-group has-feedback"
					style="margin-bottom: 0px; margin-top: 25px">
					<label for="search" class="sr-only">search</label> <input
						type="text" class="form-control " name="search" id="search"
						placeholder="搜尋"> <input type="hidden" name="action"
						value="getAllBySr"> <span
						class="glyphicon glyphicon-search form-control-feedback"></span>
				</div>
			</form>
		</div>
	</div>
	<!--end搜尋-->
	<div class="page-header">
		<h1 class="text-center">
			<i>揪團</i>
		</h1>
	</div>
	<div class="cut"></div>
	<%@ include file="/front-end/page1.file"%>
	<!-- 揪團文章 -->
	<c:if test="${empty list}">
		<h1 class="text-center">沒有該資料</h1>
	</c:if>
	<div class="container first">
		<div class="row">
			<c:if test="${!empty list}">
				<c:forEach var="group_ArtVO" items="${list}" begin="<%=pageIndex%>"
					end="<%=pageIndex+rowsPerPage-1%>">
					<c:forEach var="memVO" items="${memSvc.all}">
						<c:if test="${memVO.mem_id == group_ArtVO.mem_id}">
							<div class="col-xs-12 col-sm-4">
								<div class="well well-lg col-xs-12  grouplist"
									id="grouplist<%=i%>">
									<script>
										<%MemberVO memVO2 = (MemberVO) session.getAttribute("memVO");
							pageContext.setAttribute("memVO2", memVO2);%>
										$('#grouplist<%=i++%>').click(function(event){window.location.href = "<%=request.getContextPath()%>/front-end/group_order_detail.do?grou_id=${group_ArtVO.grou_id}&mem_id=${memVO2.mem_id}&action=enter";});
									</script>
									<div class="row">
										<div class='col-sm-2 col-xs-3'>
											<!-- 											<div class='inner'> -->
											<c:if test="${not empty memVO.mem_pic}">
												<img class="text-center" style="width: 50px; height: 100%; border-radius: 50%;"
													src="data:image/jpg;base64,${memVO.getBase64Image()}">
											</c:if>
											<c:if test="${empty memVO.mem_pic}">
												<img class="text-center" style="width: 100%; height: 100%;"
													src="<%=request.getContextPath()%>/front-end/img/dddd.png">
											</c:if>
											<!-- 											</div> -->
										</div>
										<div class="col-sm-6 col-xs-7"
											style="padding-left: 0px; padding-top: 10px">
											<p style="margin-bottom: 6px;margin-left:20px;">${memVO.mem_name}
												<c:set var="pointAsString">${memVO.mem_int}</c:set>
												(
												<c:out value="${pointAsString}" />
												)
										</div>
										<c:if test="${empty tracklist}">
											<div class="col-xs-1 col-sm-1 item col-sm-offset-1">
												<button class="btn btn-danger trackButton" type="submit">
													<span class="glyphicon glyphicon-heart-empty track"
														title="加入收藏"></span>
												</button>

												<input type="hidden" id="grou_id" name="grou_id"
													value="${group_ArtVO.grou_id}"> <input
													type="hidden" id="mem_id" name="mem_id" value="${mem_id2}">
												<input type="hidden" id="action" name="action" value="0">
											</div>
										</c:if>
										<c:set var="isDone" value="0" scope="page"></c:set>
										<c:forEach var="grou_trackVO" items="${tracklist}"
											varStatus="varStatus">
											<c:if test="${isDone == '0'}">
												<c:if test="${grou_trackVO.grou_id eq group_ArtVO.grou_id}">
													<div class="col-xs-1 col-sm-1 item col-sm-offset-1">
														<button class="btn btn-danger trackButton" type="submit">
															<span class="glyphicon glyphicon-heart track"
																title="取消收藏"></span>
														</button>

														<input type="hidden" id="grou_id" name="grou_id"
															value="${group_ArtVO.grou_id}"> <input
															type="hidden" id="mem_id" name="mem_id"
															value="${mem_id2}"> <input type="hidden"
															id="action" name="action" value="1">
													</div>
													<c:set var="isDone" value="1" scope="page"></c:set>
												</c:if>
												<c:if test="${varStatus.last}">
													<c:if test="${isDone == '0'}">
														<div class="col-xs-1 col-sm-1 item col-sm-offset-1">
															<button class="btn btn-danger trackButton" type="submit">
																<span class="glyphicon glyphicon-heart-empty track"
																	title="加入收藏"></span>
															</button>

															<input type="hidden" id="grou_id" name="grou_id"
																value="${group_ArtVO.grou_id}"> <input
																type="hidden" id="mem_id" name="mem_id"
																value="${mem_id2}"> <input type="hidden"
																id="action" name="action" value="0">
														</div>
													</c:if>
												</c:if>
											</c:if>
										</c:forEach>

										<div class="col-sm-12 col-xs-12 grouplist-a">
											<a href="#">${group_ArtVO.art_name}</a>
										</div>
									</div>
									<c:if test="${empty group_ArtVO.art_img}">
										<img
											src="<%=request.getContextPath()%>/front-end/img/dddd.png"
											class="grouplist-img text-left">
									</c:if>
									<c:if test="${! empty group_ArtVO.art_img}">
										<img
											src="data:image/jpg;base64,${group_ArtVO.getBase64Image()}"
											class="grouplist-img text-left">
									</c:if>

									<div>
										<p class="text-left">${group_ArtVO.send_locat}</p>
										<div class="progress">
										<c:if test="${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).grou_id != null}">
													<div class="progress-bar progress-bar-info"
														role="progressbar" aria-valuenow="60" aria-valuemin="0"
														aria-valuemax="100"
														style="width:${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).group_ord_price/group_ArtVO.grou_price *100}%">
														<span class="sr-only">30% Complete</span>
													</div>
										</c:if>
										</div>
										<div class="row">
											<div class="text-left col-sm-8 col-xs-8">
											
													<c:if test="${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).grou_id != null}">
															<p class="text-left"
																style="color: grey; margin-bottom: 0px;">
																<span>累積${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).mem_id }人</span>
																<span>
																	${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).group_tol_cup }杯 </span> <span>
																	<fmt:formatNumber
																		value="${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).group_ord_price }"
																		maxFractionDigits="0" />元
																</span>
															</p>
													</c:if>
													<c:if test="${grou_orderSvc.getThreeInfo(group_ArtVO.grou_id).grou_id == null}">
																<span style="color: grey; margin-bottom: 0px;">主揪邀請你來參加</span>
													</c:if>
											</div>
											<div class="col-xs-4 col-sm-4 text-right"
												style="padding: 0px">
												<span class="glyphicon glyphicon-flag text-right"
													style="color: #57c2e9;">${group_ArtVO.grou_price }</span>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</c:forEach>
			</c:if>
		</div>
	</div>
	<div class="text-center">
		<%@ include file="/front-end/page2.file"%>
	</div>

	<!-- end 揪團文章 -->
	<div class="cut"></div>

</div>


<script>
	$('.grouplist').hover(function() {
		$(this).css('cursor', 'pointer');
	});
	function switchFavorite(e) {
		var heart = e.target;
		if (heart.title == "加入收藏") {
			heart.className = "glyphicon glyphicon-heart track";
			heart.title = "取消收藏";
			$(this).parent().next().next().next().val('0');
		} else {
			heart.className = "glyphicon glyphicon-heart-empty track";
			heart.title = "加入收藏";
			$(this).parent().next().next().next().val('1');
		}
	}
	function init() {
		var hearts = document.getElementsByClassName("track");
		for (var i = 0; i < hearts.length; i++) {
			hearts[i].onclick = switchFavorite;
		}
	}

	window.onload = init;
	$(document).ready(function() {
		$(".trackButton").on("click", function(event) {
			event.stopPropagation();
			$.ajax({
				url : "/CA101G2/front-end/group.do",
				data : {
					grou_id : $(this).next().val(),
					mem_id : $(this).next().next().val(),
					action : $(this).next().next().next().val()
				},
				cache : false,
				method : "POST",
				success : function(response) {
					$("#myDiv").html(response);
				},
				error : function(msg, status, thrown) {
					console.log(msg);
					console.log(status);
				}
			});
		});
	})
	</script>
<jsp:include page="/front-end/footer.jsp" />
