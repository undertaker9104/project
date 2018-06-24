<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<%
	OrderMasterService omtakeSvr = new OrderMasterService();
	List<OrderMasterVo> list = omtakeSvr.getAllByTake();
	pageContext.setAttribute("list", list);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/ordermaster.do">
		<div class="col-xs-12 col-sm-9">
				<div class="container">
					<div class="page-header text-left">
						<div class="h1">
					    <small>外帶訂單</small>
						</div>
					</div>
					<div class="text-left"><%@ include file="/back-end/page1.file"%></div>
					<table class="table table-hover ">
						<thead>
							<tr>
							    <th style="text-align: center; font-size: 20px">選取</th>
								<th style="text-align: center;font-size: 20px">訂單</th>
								<th style="text-align: center;font-size: 20px">會員</th>
								<th style="text-align: center;font-size: 20px">揪團</th>
								<th style="text-align: center;font-size: 20px">選擇</th>
								<th style="text-align: center;font-size: 20px">狀態</th>
								<th style="text-align: center;font-size: 20px">操作</th>
								<th style="text-align: center;font-size: 20px">明細</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach items="${list}" var="orderMaster" begin="<%=pageIndex%>" end="<%=pageIndex+rowsPerPage-1%>">
								<tr bgcolor="#FFFFFF">
								    <td><input type="checkbox" name="take_ord_id" value="${ orderMaster.ord_id }"></td>
									<td style="text-align: center;font-size: 20px">${ orderMaster.ord_id }</td>
									<td style="text-align: center;font-size: 20px">${ orderMaster.mem_id }</td>
									<td style="text-align: center;font-size: 20px"><c:if test="${ orderMaster.grou == 0}">無 </c:if> 
									    <c:if test="${ orderMaster.grou == 1}">
											<img src="<%=request.getContextPath()%>/front-end/img/group.jpg" style="width: 100px; height: 100px">
										</c:if></td>
									<td><c:if test="${ orderMaster.ship_option == 0}">
											<img src="<%=request.getContextPath()%>/front-end/img/take.jpg" style="width: 100px; height: 100px">
										</c:if> 
										<c:if test="${ orderMaster.ship_option == 1}">
											<img src="<%=request.getContextPath()%>/front-end/img/out.jpg" style="width: 100px; height: 100px">
										</c:if></td>
									<td style="text-align: center;font-size: 20px">
									    <c:choose>
											<c:when test="${orderMaster.ord_status == 0}">準備中 </c:when>
											<c:when test="${orderMaster.ord_status == 1}">出貨中</c:when>
											<c:when test="${orderMaster.ord_status == 2}">可取貨</c:when>
											<c:when test="${orderMaster.ord_status == 3}">交易完成</c:when>
											<c:otherwise>移除</c:otherwise>
										</c:choose>
									</td>
									<td style="text-align: center; font-size: 20px">
									<a href="<%=request.getContextPath()%>/ordermaster.do?action=updeteOneOrderMaster&ord_id_update=${orderMaster.ord_id}">狀態修改	</a>
									<input type="hidden" name="requestURL" value="<%=request.getParameter("requestURL")%>"> <!--接收原送出修改的來源網頁路徑後,再送給Controller準備轉交之用-->
                                    <input type="hidden" name="whichPage"  value="<%=request.getParameter("whichPage")%>"> 
									</td>
									<td style="text-align: center; font-size: 20px">
									 <a href="<%=request.getContextPath()%>/ordermaster.do?action=getOneOrderDetail&ord_id=${orderMaster.ord_id}">訂單明細</a>	
									</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
					<input type="hidden" name="action" id="take_option" value="changeAllOk">
					<div>
					<button class="btn btn-info" type="button"
						onclick="var array=document.getElementsByName('take_ord_id');for(var i=0; i<array.length;
						i++){array[i].checked=true;}">
						<H3>全選</H3>
					</button>

					<button class="btn btn-info" type="button"
						onclick="var array=document.getElementsByName('take_ord_id');for(var i=0; i<array.length;
						i++){array[i].checked=false;}">
						<H3>取消全選</H3>
					</button>

					<button class="btn btn-info" type="submit" onclick="takeok()">
						<H3>更改可取貨</H3>
					</button>

					<button class="btn btn-info" type="submit" onclick="takedone()">
						<H3>更改交易完成</H3>
					</button>
				</div>
					<div class="text-right"><%@ include file="/back-end/page2.file"%></div>
				</div>
    		</div>
    		</form>	
	    	<c:if test="${openModal!=null}">
			<div class="modal fade" id="basicModal" tabindex="-1" role="dialog"
				aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								    aria-hidden="true">&times;</button>
							<div class="modal-title" id="myModalLabel">
								<div id="table-1" class="myTable text-left"></div>
							</div>
							<div class="modal-body">
								<jsp:include page="/back-end/orderdetail/listOneOrderDetail.jsp"/>
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
			<div class="modal fade" id="basicModal_update" tabindex="-1"
				 role="dialog" aria-labelledby="basicModal" aria-hidden="true">
				<div class="modal-dialog modal-lg">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								   aria-hidden="true">&times;</button>
							<div class="modal-title" id="myModalLabel_update">
								<div id="table-1" class="myTable text-left"></div>
							</div>
							<div class="modal-body">
								<jsp:include page="/back-end/ordermaster/updateOneOrderMaster.jsp" />
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
		<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
		<script>$("#basicModal").modal({show: true});</script>
		<script>$("#basicModal_update").modal({show: true});</script>
		<Script Language="JavaScript">
           function takeok() {
            document.getElementById("take_option").value ="changeAllOk";
            document.form.submit();
           }
          function takedone(){
            document.getElementById("take_option").value ="changeAllTakeDone";
            document.form.submit();
          }
       </Script>
       <script>
	   $(document).ready(function() {
		$("tr:odd").css("background-color", "#f0f2f4");});
       </script>
</body>
</html>
