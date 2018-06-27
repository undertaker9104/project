<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="BIG5"%>
<%@ page import="java.util.*"%>
<%@ page import="com.group_order_detail.model.*"%>
<%@ page import="com.ice.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="com.group_art.model.*"%>
<%@ page import="com.mem.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="/front-end/header.jsp" />
<%
	Group_ArtVO groupVO = (Group_ArtVO) session.getAttribute("groupVO");
	

	String grouid = groupVO.getGrou_id();
	String memid = groupVO.getMem_id();	

	ProductService productSvc = new ProductService();
	List<ProductVo> productVo = productSvc.getAll();
	pageContext.setAttribute("productVo", productVo);

	GroupService grouSvc = new GroupService();
	Group_ArtVO list = grouSvc.getOneArt(grouid);
	request.setAttribute("list", list);	
		
	Group_order_detailService godSvc = new Group_order_detailService();
	List<Group_order_detailVO> group_order_detailList = godSvc.getGrou_id(grouid);
	request.setAttribute("group_order_detailList", group_order_detailList);
		
	Group_order_detailVO group_order_detailgrou = godSvc.getByGrou_id(grouid);
	request.setAttribute("group_order_detailgrou", group_order_detailgrou);
		
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
<title>揪團列表</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="front-image/mycss.css">


<style>


.h1 {
	margin-top:1%;
	font-size: 60px;
	color: black;
	margin-bottom: -1%;
}

.table {
margin-top:0%;
	color: black;
	font-size: 20px;
	margin-bottom: 0%;
}

thead {
	color: #0066FF;
	font-size: 30px;
}

.table1 {
	color: black;
	font-size: 20px;
}

.detail {
	font-size: 35px;
}

.send-locat {

margin-left:0%;
	font-size: 30px;
	color : red;
}
.ship-locat {
margin-left:5%;
	font-size: 30px;
	color : red;
	
}

.exp-date {
margin-right:-20%;

	font-size: 25px;
	color : black;	
}
.total {
	font-size: 40px;
	color: red;
	margin-top: 2%;
	margin-bottom: 15%;
}

.total1 {
	font-size: 30px;
	color: red;
	margin-right: -26%;
	margin-top: 2%;
}
form{
display:inline;
}
.date{
margin-bottom: -1%;
}
.cancelgroup1{
margin-left: 48%;
}

.addgroup1{
margin-left: 47%;
}
.leavegroup1{
margin-left: 48%;
}

.back{
margin-left: 45%;
font-size:40px;
}
.detail{
colar:red;
}
b{
font-size:30px;
}
</style>
</head>

<body>
	<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
	<jsp:useBean id="sweetSvc" scope="page"	class="com.sweet.model.SweetService" />
	<jsp:useBean id="proSvc" scope="page"	class="com.product.model.ProductService" />
    <jsp:useBean id="mSvc" scope="page"	class="com.mem.model.MemberService" />
    <jsp:useBean id="addSvc" scope="page"	class="com.addgroup.model.AddGroupService" />
	
	<% MemberVO memVO = (MemberVO)session.getAttribute("memVO"); %>

       
	<div class="page-header text-center h1">${list.art_name}</div>
	<br>
	<div class="container-fluid send-locat">
		<div class="row">
       
		    <div class="col-xs-12 col-sm-3 "></div>
   
			<div class="col-xs-12 col-sm-3 send-locat" style="margin-top:0.5%;">
			   
			<b>主揪者:<img src="data:image/jpg;base64,${mSvc.getOneMem_id(list.mem_id).getBase64Image()}"
			    class="img-circle" style="width: 100px; height:100px;">${mSvc.getOneMem_id(list.mem_id).mem_name}</b><br>
		    </div>
			<br>
			<div class="col-xs-12 col-sm-3 ship-locat"><b>外送地點:${list.send_locat}</b></div>	
				
			<div class="col-xs-12 col-sm-3 "></div>								
								
		</div>
	</div>
	<div class="container-fluid date">
		<div class="row">
		    <div class="col-xs-12 col-sm-3 "></div>
	
			<div class="col-xs-12 col-sm-3 send-locat" style="margin-top:1.5%;"><b>取貨地點:${list.ship_locat}</b></div>
			<br>
			<div class="col-xs-12 col-sm-3 ship-locat" style="margin-top:0.5%;"><b>截止日期:${list.exp_date}</b></div>	
			<div class="col-xs-12 col-sm-3 "></div>																	
		</div>
	</div>
	<br>
	<br>
	<br>
	<br>
	<div class="container-fluid table">
		<div class="row">
			<div class="col-xs-12 col-sm-2">
			</div>
			<div class="col-xs-12 col-sm-8 text-center">
				<div class="pre-scrollable">					
			    	<table class="table-hover text-center  table " >							
						<tbody>
							<c:forEach var="productVo" items="${productVo}">
                                  <FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
									<tr>
										<td><image src="data:image/jpg;base64,${productVo.getBase64Image()}" style="width:70px;height:70px"></td>
										<td style="height:70px text-center">${productVo.product_name}</td>
										<td>${productVo.product_price}</td>
										<td><select size="1" name="sweet_id">
												<c:forEach var="sweetVo" items="${sweetSvc.all}">
													<option value="${sweetVo.sweet_id}">${sweetVo.sweet_des}
												</c:forEach>
										</select></td>
										<td><select size="1" name="ice_id">
												<c:forEach var="iceVo" items="${iceSvc.all}">
													<option value="${iceVo.ice_id}">${iceVo.ice_des}
												</c:forEach>
										</select></td>
										<td><select size="1" name="group_tol_cup">
												<option value="1">1</option>
												<option value="2">2</option>
												<option value="3">3</option>
												<option value="4">4</option>
												<option value="5">5</option>
												<option value="6">6</option>
												<option value="7">7</option>
												<option value="8">8</option>
												<option value="9">9</option>
										</select>
										</td>
										<c:if test="${groupVO.mem_id == addSvc.getMem_id(groupVO.grou_id , groupVO.mem_id).mem_id || groupVO.mem_id}">										
										<td>
                                        <input type="hidden" name="grou_id" value="${list.grou_id}">
						                <input type="hidden" name="mem_id" value="${groupVO.mem_id}">
						                <input type="hidden" name="ord_id" value="${list.ord_id}">
					                    <input type="hidden" name="product_id" value="${productVo.product_id}">
						                <input type="hidden" name="product_price" value="${productVo.product_price}">
                                        <input type="hidden" name="action" value="add1">                                        
                                        <button type="submit" class="btn btn-success">加入飲品</button>                                        
                                        </td>
                                        </c:if>
									</tr>
									</FORM>
						 	</c:forEach>
						 </tbody>
					</table>				
				</div>
			</div>
			<div class="col-xs-12 col-sm-2"></div>
		</div>
	</div>
<%if(group_order_detailList.size() != 0){%>
	<div class="container-fluid detail">
		<div class="row">
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-8 text-center">			
			   <h1 align="center" ><font size="10px" color="red">目前訂購</font></h1>   
				  <div class="pre-scrollable">
					<table class="table-hover  table">
						<c:forEach var="group_order_detail" items="${group_order_detailList}">					
							<tr>
							    <td><image src="data:image/jpg;base64,${proSvc.getOneProduct(group_order_detail.product_id).getBase64Image()}" style="width:70px;height:70px"></td>
								<td>${mSvc.getImg(group_order_detail.mem_id).mem_name}</td>
								<td>${proSvc.getOneProduct(group_order_detail.product_id).product_name}</td>
								<td>${sweetSvc.getOneSweet(group_order_detail.sweet_id).sweet_des}</td>
								<td>${iceSvc.getOneIce(group_order_detail.ice_id).ice_des}</td>
								<td>${group_order_detail.group_tol_cup}</td>
								<td>${group_order_detail.group_ord_price*group_order_detail.group_tol_cup}</td>													
							    <td>
						<form METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
								<input type="hidden" id="grou_id" name="grou_id" value="${group_order_detail.grou_id}">
								<input type="hidden" id="mem_id"  name="mem_id" value="${group_order_detail.mem_id}">
								<input type="hidden" id="product_id" name="product_id" value="${group_order_detail.product_id}"> 
								<input type="hidden" id="sweet_id" name="sweet_id" value="${group_order_detail.sweet_id}">
								<input type="hidden" id="ice_id" name="ice_id" value="${group_order_detail.ice_id}">
								<input type="hidden" id="ord_id" name="ord_id" value="${group_order_detail.ord_id}">
								<c:if test="${groupVO.mem_id == group_order_detail.mem_id}">
								<button type="submit" class="btn btn-danger">取消飲品</button>
								<input type="hidden" id="delete" name="action" value="delete">
								</c:if>
								</td>
						</form>								
							</tr>
						</c:forEach>						
						</tr>
					</table>					
				</div>
				</div>        
			<div class="col-xs-12 col-sm-2"></div>
		</div>
	</div>
    <div>
    <div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-8 text-center total1">		             
	<c:if test="${groupVO.mem_id != addSvc.getMem_id(groupVO.grou_id , groupVO.mem_id).mem_id && groupVO.mem_id != list.mem_id}">
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="mem_id" value="${groupVO.mem_id}">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="addgroup">
	<button type="submit" class="btn-lg btn-primary active addgroup">加入揪團</button>
	</FORM>		
	</c:if>

	<c:if test="${groupVO.mem_id == addSvc.getMem_id(groupVO.grou_id , groupVO.mem_id).mem_id && groupVO.mem_id != list.mem_id}">		
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="mem_id" value="${groupVO.mem_id}">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="leavegroup"> 
	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
	<button type="submit" class="btn-lg btn-primary active leavegroup">退出揪團</button>
	</FORM>	
	</c:if>
	
	<c:if test="${groupVO.mem_id == list.mem_id}">
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="cancelgroup"> 
	<button type="submit" class="btn-lg btn-primary active cancelgroup">取消揪團</button>
	</FORM>	
	
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="detail"> 
	<button type="submit" class="btn-lg btn-primary active checkout">結帳</button>
	</FORM>
	
	</c:if>		
			
			</div>
			<div class="col-xs-12 col-sm-2 text-center total1">
			<b>總計:
			<% 
			Integer total = 0;
			Integer cup = 0;
			for (int i = 0; i <group_order_detailList.size(); i++) {
				Group_order_detailVO gg = group_order_detailList.get(i);
				Integer price = gg.getGroup_ord_price();
				Integer quantity = gg.getGroup_tol_cup();
				cup += quantity;
				total += (price * quantity);
			}
			%>
			<%=cup+"杯"%>
			<%=total+"元"%>		
			</b>
			</div>
		</div>
    </div>	
    <% }%>	
    </div>
    <%if(group_order_detailList.size() == 0){%>
    
    <c:if test="${groupVO.mem_id == addSvc.getMem_id(groupVO.grou_id , groupVO.mem_id).mem_id && groupVO.mem_id != list.mem_id}">		
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="mem_id" value="${groupVO.mem_id}">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="leavegroup"> 
	<input type="hidden" name="requestURL"	value="<%=request.getServletPath()%>"><!--送出本網頁的路徑給Controller-->
	<button type="submit" class="btn-lg btn-primary active leavegroup1">退出揪團</button>
	</FORM>	
	</c:if>
                  
    <c:if test="${groupVO.mem_id != addSvc.getMem_id(groupVO.grou_id , groupVO.mem_id).mem_id && groupVO.mem_id != list.mem_id}">
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="mem_id" value="${groupVO.mem_id}">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="addgroup">
	<button type="submit" class="btn-lg btn-primary active addgroup1">加入揪團</button>
	</FORM>		
	</c:if>
     
    <c:if test="${groupVO.mem_id == list.mem_id}">
	<FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="cancelgroup"> 
	<button type="submit" class="btn-lg btn-primary active cancelgroup1">取消揪團</button>
	</FORM>		
	
	</c:if>	
    <% }%>
    <br>
    <a href="<%=request.getContextPath()%>/front-end/groupart/grouplist.jsp" class="back">返回揪團列表</a>

	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
		
 
		
	$(".leavegroup").click(function(e) {
		$(".add1").hide();
		});


	
	<jsp:include page="/front-end/footer.jsp" />
	</script>
	<jsp:include page="/front-end/msg/listAllMsgFront.jsp" />
</body>


</html>
