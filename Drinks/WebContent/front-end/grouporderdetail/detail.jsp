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
	
	Integer cup =(Integer)request.getAttribute("totalcup"); 
	Integer total =(Integer)request.getAttribute("totalprice"); 

	
		
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


h1{
	margin-top: 30%;
	font-size: 50px;
	color: red;
	margin-bottom: 1%;
}

.table {
margin-top: 7%;
margin-bottom: -5%;
	color: black;
	font-size: 20px;
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

margin-left:5%;
	font-size: 25px;
}
.ship-locat {

	font-size: 25px;
}
.exp-date {
margin-right:-20%;

	font-size: 25px;
	
}

.total {
	font-size: 40px;
	color: red;
	margin-top: 2%;
	margin-bottom: 5%;
}

.total1 {
	font-size: 40px;
	color: red;
	margin-right: -26%;
	margin-top: -3%;
	margin-bottom: -5%;
}
.sum{
color : black;
}
.dis{
color : black;
}
.checkout{
margin-left: 48%;
}
.back{
margin-left:44%;
font-size:50px;
}



</style>
</head>

<body>
	<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
	<jsp:useBean id="sweetSvc" scope="page"	class="com.sweet.model.SweetService" />
	<jsp:useBean id="proSvc" scope="page"	class="com.product.model.ProductService" />
	<% MemberVO memVO = (MemberVO)session.getAttribute("memVO"); %>

	<div class="container-fluid">
		<div class="row">
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-8 text-center">			
			   <h1 align="center" ><font size="50px" color="red">您的訂單</font></h1>			  
					<table class="table-hover  table">
					    <thead>
                            <th class="text-center">圖片</th>
                            <th class="text-center">會員</th>
                            <th class="text-center">產品名稱</th>
                            <th class="text-center">甜度</th>
                            <th class="text-center">冰度</th>
                            <th class="text-center">杯數</th>
                            <th class="text-center">金額</th>
                            </thead>
                            
						<c:forEach var="group_order_detail" items="${group_order_detailList}" >	                           			
							<tr>
							    <td><image src="data:image/jpg;base64,${proSvc.getOneProduct(group_order_detail.product_id).getBase64Image()}" style="width:70px;height:70px"></td>
								<td>${group_order_detail.mem_id}</td>
								<td>${proSvc.getOneProduct(group_order_detail.product_id).product_name}</td>
								<td>${sweetSvc.getOneSweet(group_order_detail.sweet_id).sweet_des}</td>
								<td>${iceSvc.getOneIce(group_order_detail.ice_id).ice_des}</td>
								<td>${group_order_detail.group_tol_cup}</td>
								<td>${group_order_detail.group_ord_price*group_order_detail.group_tol_cup}</td>																			    
						</form>								
							</tr>
						</c:forEach>						
						</tr>
					</table>
					
				</div>
				</div>
        
			<div class="col-xs-12 col-sm-2"></div>
		</div>
<br>
<br>
<br>
    <div class="container-fluid total">
		<div class="row">
			<div class="col-xs-12 col-sm-2 col-sm-offset-1"></div>
			<div class="col-xs-12 col-sm-2"></div>
			<div class="col-xs-12 col-sm-2">
					
			</div>
			<div class="col-xs-12 col-sm-2 text-center total1">
			
			<span class="sum">&nbsp&nbsp&nbsp&nbsp&nbsp總計&nbsp:</span> &nbsp${totalcup}杯
			<br>
			<span class="dis">折扣後為&nbsp:&nbsp</span> ${totalprice}元
			
			</div>
			<div class="col-xs-12 col-sm-2">
						
			</div>
		</div>
    </div>	
    <FORM METHOD="post"	ACTION="<%=request.getContextPath()%>/front-end/group_order_detail.do">
	<input type="hidden" name="grou_id" value="${groupVO.grou_id}">
	<input type="hidden" name="action" value="sendEvaluation"> 
	<button type="submit" class="btn-lg btn-primary active checkout">發出評價通知</button>
	</FORM>
<a href="<%=request.getContextPath()%>/front-end/groupart/grouplist.jsp" class="back">返回揪團列表</a>	


	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script type="text/javascript">
	

	
	
	</script>
</body>
<jsp:include page="/front-end/footer.jsp" />


</html>