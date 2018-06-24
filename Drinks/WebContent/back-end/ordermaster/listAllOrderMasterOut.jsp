<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%@ page import="com.manager_account.model.*"%>
<%@ page import="com.manager_account_authority.model.*"%>
<%@ page import="com.authority_class.model.*"%>
<%@ page import="java.util.*"%>
<%
	OrderMasterService omOutSvr = new OrderMasterService();
	List<OrderMasterVo> list = omOutSvr.getAllByOut();
	pageContext.setAttribute("list", list);
	
%>
<%
	Manager_accountService manager_accountSvc = new Manager_accountService();
	List<Manager_accountVO> listman = manager_accountSvc.getAll();
	pageContext.setAttribute("listman",listman);
%>
<jsp:useBean id="manaaSvc" class="com.manager_account_authority.model.Manager_account_authorityService"/>
<jsp:useBean id="authority_classSvc" class="com.authority_class.model.Authority_classService"/>
<jsp:useBean id="manSvc" class="com.manager_account.model.Manager_accountService"/>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>OrderMaster</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/base.css">
<style type="text/css">
img {
	height: 100px;
	width: 100px;
}
</style>
</head>
<body>
	<form method="post" action="<%=request.getContextPath()%>/ordermaster.do">
		<div class="col-xs-12 col-sm-9">
			<div class="container">
				<div class="page-header text-left">
					<div class="h1">
						<small>外送訂單</small>
					</div>
				</div>
				<!-- -->
				<div class="text-left"><%@ include file="/back-end/page1.file"%></div>
				<table 	class="table table-hover ">
					<thead>
						<tr>
						    <th style="text-align: center; font-size: 20px">選取</th>
							<th style="text-align: center; font-size: 20px">訂單</th>
							<th style="text-align: center; font-size: 20px">會員</th>
							<th style="text-align: center; font-size: 20px">外送員</th>
							<th style="text-align: center; font-size: 20px">揪團</th>
							<th style="text-align: center; font-size: 20px">地址</th>
							<th style="text-align: center; font-size: 20px">選擇</th>
							<th style="text-align: center; font-size: 20px">狀態</th>
							<th style="text-align: center; font-size: 20px">操作</th>
							<th style="text-align: center; font-size: 20px">明細</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="orderMaster" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
							<tr bgcolor="#FFFFFF">
							    <td><input type="checkbox" name="ord_id" value="${ orderMaster.ord_id }"></td>
								<td style="text-align: center; font-size: 20px">${ orderMaster.ord_id }</td>
								<td style="text-align: center; font-size: 20px">${ orderMaster.mem_id }</td>
								<td style="text-align: center; font-size: 20px">${ manSvc.getOneEmp(orderMaster.man_acc_id).emp_name }</td>
								<td>
								     <c:if test="${ orderMaster.grou == 0}">無 </c:if> 
								     <c:if 	test="${ orderMaster.grou == 1}">
										<img src="<%=request.getContextPath()%>/front-end/img/group.jpg">
									</c:if>
								</td>
								<td style="text-align: center; font-size: 20px">${orderMaster.oute_add }</td>
								<td>
								    <c:if test="${ orderMaster.ship_option == 0}">
										<img src="<%=request.getContextPath()%>/front-end/img/take.jpg">
									</c:if> 
									<c:if test="${ orderMaster.ship_option == 1}">
										<img src="<%=request.getContextPath()%>/front-end/img/out.jpg">
									</c:if>
								</td>
								<td style="text-align: center; font-size: 20px">
								    <c:choose>
										<c:when test="${orderMaster.ord_status == 0}">準備中 </c:when>
										<c:when test="${orderMaster.ord_status == 1}">出貨中</c:when>
										<c:when test="${orderMaster.ord_status == 2}">可取貨</c:when>
										<c:when test="${orderMaster.ord_status == 3}">交易完成</c:when>
										<c:otherwise>移除</c:otherwise>
									</c:choose>
								</td>
								<td style="text-align: center; font-size: 20px">
								   <a href="<%=request.getContextPath()%>/ordermaster.do?action=updeteOneOrderMaster&ord_id_update=${orderMaster.ord_id}">修改</a>
								</td>
								<td style="text-align: center; font-size: 20px">
								   <a href="<%=request.getContextPath()%>/ordermaster.do?action=getOneOrderDetail&ord_id=${orderMaster.ord_id}">明細</a>
								</td>
								
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<div>
					<button class="btn btn-info" type="button"
						onclick="var array=document.getElementsByName('ord_id');for(var i=0; i<array.length;
						i++){array[i].checked=true;}">
						<H3>全選</H3>
					</button>

					<button class="btn btn-info" type="button"
						onclick="var array=document.getElementsByName('ord_id');for(var i=0; i<array.length;
						i++){array[i].checked=false;}">
						<H3>取消全選</H3>
					</button>

					<button class="btn btn-info" type="submit" onclick="ship()">
						<H3>更改運送中</H3>
					</button>

					<button class="btn btn-info" type="submit" onclick="done()">
						<H3>更改交易完成</H3>
					</button>
				</div>

				<div class="text-right"><%@ include file="/back-end/page2.file"%></div>
			</div>
			<div class="page-header text-left">
				<div class="h1">
					<small>外送訂單分配</small>
				</div>
			</div>

			<div class="col-xs-12 col-sm-9">
				<div class="container">
					<c:forEach items="${listman}" var="manager_accountVO" varStatus="s">
				         <c:if test="${manaaSvc.getOutManager_account(manager_accountVO.man_acc_id).authority_id == 'AC000002' }">
						<div class="col-sm-4 test">
							<a href="#" class="card">
								<div class="card-img">
									<img src="data:image/jpg;base64,${manager_accountVO.getImg(manager_accountVO.emp_img)}"
										class="img-circle" style="width: 200px; height: 200px">
									<H3>${(manager_accountVO.man_acc_status==0)?'等待中':'外出送貨'}</H3>
								</div>
								
								<div class="card-title">
									<input type="radio" name="man_acc_id" value="${manager_accountVO.man_acc_id}">
									<H3>${manager_accountVO.emp_name}</H3>
									<input type="hidden" name="action" id="option" value="disOrderMaster">
								</div>
							</a>
						</div>
						 </c:if>
					</c:forEach>
					 <input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>">
			         <input type="hidden" name="whichPage"	value="<%=whichPage%>">
					<input type="submit" class="btn btn-warning btn-lg" onclick="dis()" value="分配確認">
				</div>
			</div>
		</div>
	</form>
	<!--  -->
	<c:if test="${openModal!=null}">
		<div class="modal fade" id="basicModal" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<div class="modal-title" id="myModalLabel">
							<div id="table-1" class="myTable text-left"></div>
						</div>
						<div class="modal-body">
							<jsp:include page="/back-end/orderdetail/listOneOrderDetail.jsp" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>

	<c:if test="${openModal_update!=null}">
		<div class="modal fade" id="basicModal_update" tabindex="-1" role="dialog" aria-labelledby="basicModal" aria-hidden="true">
			<div class="modal-dialog modal-lg">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
						<div class="modal-title" id="myModalLabel_update">
							<div id="table-1" class="myTable text-left"></div>
						</div>
						<div class="modal-body">
							<jsp:include page="updateOneOrderMaster.jsp" />
						</div>
						<div class="modal-footer">
							<button type="button" class="btn btn-primary" data-dismiss="modal">Close</button>
						</div>
					</div>
				</div>
			</div>
		</div>
	</c:if>
<script src="https://code.jquery.com/jquery.js"></script>
<script src="<%=request.getContextPath()%>/back-end/js/bootstrap.min.js"></script>
<script>$("#basicModal").modal({show: true});</script>
<script>$("#basicModal_update").modal({show: true});</script>
	
<Script>
function ship() {
document.getElementById("option").value ="changeAllShip";
document.form.submit();
}
function done(){
document.getElementById("option").value ="changeAllDone";
document.form.submit();
}
function dis(){
document.getElementById("option").value ="disOrderMaster";
document.form.submit();
}
</Script>
<script>
	$(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
    </script>
</body>
</html>
