<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.product.model.*"%>
<%@ page import="com.shoppingcart.model.*"%>
<%@ page import="com.discount.model.*"%>
<%@ page import="com.ice.model.*"%>
<%@ page import="com.sweet.model.*"%>
<%@ page import="com.product.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="java.io.*"%>
<%@ page import="com.mem.model.*"%>
<%
    Integer amount = (Integer) request.getAttribute("amount");
    Integer distotal = (Integer) request.getAttribute("distotal");
    request.setAttribute("amount", amount);
    request.setAttribute("distotal", distotal);
%>
<%    
	DiscountService disSvc = new DiscountService();
	List<DiscountVO> disVo = disSvc.getAll();
	int disCup = disVo.get(0).getDis_cup();
	Double disCupRate = disVo.get(0).getDis_cup_rate();
	int disPrice = disVo.get(0).getDis_price();
	Double disPriceRate = disVo.get(0).getDis_price_rate();
    request.setAttribute("disCup", disCup);
    request.setAttribute("disPrice", disPrice);
%>
<% 
    MemberVO memVO = (MemberVO) session.getAttribute("memVO");
    int point =memVO.getMem_point();
    request.setAttribute("point",point);
    request.setAttribute("memVo",memVO);
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script type="text/javascript"src="<%=request.getContextPath()%>/front-end/js/sweetalert.min.js"></script>
<link rel="stylesheet" 	href="<%=request.getContextPath()%>/front-end/css/sweetalert.css" />
<link rel="stylesheet" 	href="https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.css" />

<style type="text/css">
body, td, th, input {
	font-size: 12px;
	text-align: center;
}

img {
	height: 150px;
	width: 150px;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>

</head>
<body>
	<jsp:include page="/front-end/header.jsp" />
	<jsp:useBean id="sweetSvc" scope="page" class="com.sweet.model.SweetService" />
	<jsp:useBean id="iceSvc" scope="page" class="com.ice.model.IceService" />
	<div class="container-fulled" style="margin-top: 2%">
		<div class="row">
			<div class="page-header text-left col-xs-12 col-sm-10 col-sm-offset-1">
				<label><img src="<%=request.getContextPath()%>/front-end/img/shopping-cart.png"></label>
			</div>
			<div class="col-xs-12 col-sm-10 col-sm-offset-1 well form-horizontal">
				<% List<ShoppingCartVO> list = (List<ShoppingCartVO>) session.getAttribute("list");%>
				<% if (list != null && (list.size() > 0)) { %>
				<% 
				    int tol = 0;
	                int tolCups = 0;
	                for (int i = 0; i < list.size(); i++) {
	                ShoppingCartVO order = (ShoppingCartVO) list.get(i);
		            Integer price = order.getProduct_price();
		            Integer quantity = order.getQuantity();
		             tol += price;
		             tolCups += quantity;
	             }
	 	          pageContext.setAttribute("tol",tol); 
	 	          pageContext.setAttribute("tolCups",tolCups); 

	             %>
				<% session.setAttribute("count",list.size()); %>


				<table class="table table-hover">
					<thead>
					</thead>
					<tbody>
						<c:forEach items="${list}" var="order" varStatus="s">
							<tr bgcolor="#FFFFFF">
								<td style="text-align: center; font-size: 24px">${order.product_name}</td>
								<td style="text-align: center; font-size: 24px">${order.product_price}</td>
								<td style="text-align: left; font-size: 24px; width: 20%">${order.product_des}</td>
								<td style="text-align: center; font-size: 24px"><img
									src="data:image/jpg;base64,${order.getBase64Image()}"></td>
								<td style="text-align: center; font-size: 24px">
									${sweetSvc.getOneSweet(order.sweet_id).sweet_des}</td>
								<td style="text-align: center; font-size: 24px">
									${iceSvc.getOneIce(order.ice_id).ice_des}</td>
								<td style="text-align: center; font-size: 24px">
								<label 	style="text-align: center; font-size: 24px">
										<form method="get" action="<%=request.getContextPath()%>/cart.do">
											<input type="hidden" id="minusIndex" name="minusIndex" value="${s.count}">
										    <input type="hidden" id="action"  name="action" value="minus">
										    <input type="submit" class="btn btn-info btn-lg" value="-">
										</form>
								</label> 
								   <label style="text-align: center; font-size: 24px">${order.quantity}</label>
									<label style="text-align: center; font-size: 20px">
										<form method="get" action="<%=request.getContextPath()%>/cart.do">
											<input type="hidden" id="pluslIndex" name="plusIndex" value="${s.count}"> 
											<input type="hidden" id="action" 	name="action" value="plus">
										    <input type="submit" class="btn btn-info btn-lg" value="+">
										</form>
								    </label>
								    </td>
								<td style="text-align: center; font-size: 24px">
									<form method="get" id="form1"action="<%=request.getContextPath()%>/cart.do">
										<input type="hidden" name="calcelIndex" value="${s.count}">
										<input type="hidden" name="action" value="edit"> 
										<input type="hidden" name="mem_id" value="${memVo.mem_id}">
										<button type="submit" name="submit_Btn" id="submit_Btn"
											   onClick="document.form1.submit()"
											   class="btn btn-lg glyphicon glyphicon-pencil">
									    </button>
									</form>
								</td>
								<td style="text-align: center; font-size: 18px">
									<form method="post" id="form2"
										action="<%=request.getContextPath()%>/cart.do">
										<input type="hidden" name="calcelIndex" value="${s.count}">
										<input type="hidden" name="action" value="cancel">
										<button type="submit" name="submit_Btn" id="submit_Btn"
											onClick="document.form2.submit()"
											class="btn btn-lg glyphicon glyphicon-trash">
										</button>
									</form>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>


				<div class="form-group">
					<form method="post" action="<%=request.getContextPath()%>/cart.do"
						onsubmit="checkoutconfirm()">
						<input type="hidden" name="action" value="checkout"> <input
							type="hidden" name="mem_id" value="${memVo.mem_id}"> <input
							type="hidden" name="grou" value="0"> <input type="hidden"
							name="ord_starus" value="0"> <label
							class="col-md-2 control-label"><font size="5">選擇訂購:</font></label>
						<label class="col-md-1 control-label"><font size="5">外帶</font></label>
						<div class="col-md-1 inputGroupContainer">
							<div class="input-group">
								<input type="radio" name="ship_option" value="0" checked>
							</div>
						</div>
						<label class="col-md-1 control-label"><font size="5">外送</font></label>
						<div class="col-md-1 inputGroupContainer">
							<div class="input-group">
								<input type="radio" name="ship_option" value="1">
							</div>
						</div>
						<div style='display: none' id="out">
							<label class="col-md-1 control-label"><font size="5">地址</font></label>
							<label class="col-md-1 control-label">
							 <input type="text"	name="oute_add" id="address" size="15">
							</label>
						</div>
						<label class="col-md-4 control-label"> 
						<spin 	class="col-sm-12" id="distance" style="padding-left:0px;"></spin>
						</label>
				        </div>
				          	<h2 class="text-left col-xs-12 col-sm-10 col-sm-offset-4">
					        <a class="btn btn-lg btn-warning" href="<%=request.getContextPath()%>/front-end/index.jsp">
					        <font size="+3">繼 續 購 物</font>
				            </a>
				            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				             <input type="submit" class="btn btn-info btn-lg"  
				                style="text-align: center; font-size: 36px"
				                  value="checkout" id="checkout">
			              	</h2>
				             
				   </form>

			</div>

			<div class="col-xs-12 col-sm-10 col-sm-offset-1">
				<h2 class="text-left">
					<%if(tol>=disPrice || tolCups>=disCup) {%>
					<img src="<%=request.getContextPath()%>/front-end/img/total.png"
						style="width: 80px; height: 80px" />: <s>${tol}</s>&nbsp;&nbsp;&nbsp;
					<img src="<%=request.getContextPath()%>/front-end/img/dis.png"
						style="width: 80px; height: 80px" /> <span
						class="glyphicon glyphicon-usd"></span>:${distotal}
					<%}else{%>
					<img src="<%=request.getContextPath()%>/front-end/img/total.png"
						style="width: 80px; height: 80px" />:${tol}
					<%}%>
					&nbsp;&nbsp;&nbsp;<img
						src="<%=request.getContextPath()%>/front-end/img/list.png"
						style="width: 80px; height: 80px" />:${tolCups}
				</h2>
			</div>
		</div>
	</div>
	<%}else{%>
	<br>
	<br>
	<br>
	<div>
		<H1>購物車沒有商品喔!!!!!!!!</H1>
	</div>
	<% }%>
	<div>
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
								<jsp:include page="updateOneCart.jsp" />
							</div>
							<div class="modal-footer">
								<button type="button" class="btn btn-primary"
									data-dismiss="modal">Close</button>
							</div>
						</div>
					</div>
				</div>
			</div>
		</c:if>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script
		src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
	<script>$("#basicModal").modal({show: true});</script>
	<script type="text/javascript">
   		function out(){	
			document.getElementById("out").style.display='';
		}
   		function take(){	
			document.getElementById("out").style.display='none';
		}
		function init(){
			var pays = document.getElementsByName("ship_option");
  			    pays[0].addEventListener("click",take,false);
  			    pays[1].addEventListener("click",out,false);
		}
    		window.addEventListener("load", init, false);
	</script>

	<script src='https://cdnjs.cloudflare.com/ajax/libs/limonte-sweetalert2/6.10.3/sweetalert2.js' type='text/javascript'></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/core-js/2.4.1/core.js'></script>
	<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCOcxUQe-85L7pEFGVR-BJXxHoHI0Doc8s">
</script>
	<script type="text/javascript">
   		function checkoutconfirm(){	
   			if( ${point< tol} ){
   				swal({
   				  title:"點數不足",
   				  text: "是否儲值！",
   				  type: "warning",
   				  showCancelButton: true,
   				  confirmButtonClass:"btn-danger",
   				  confirmButtonText: "儲值去",
   				  cancelButtonText: "取消",
   			      closeOnConfirm: true, 
   			      closeOnCancel: false  
   			      
   			},
   			    function(isConfirm){ 
   			    window.location.assign("http://localhost:8081/CA101G2/front-end/mem/storedPoint.jsp");
   				});
   			}else{
   				swal("訂單成立!", "感謝你的購買!","success")
   			}
   		
 	    }
</script>
	<script>
  //修改時直接判斷位址 
    if($('#address').val() != ""){
  	//地址轉成經緯度   
        var geocoder = new google.maps.Geocoder();
        addr = $("#address").val();
        if(addr != ""){
        geocoder.geocode({
                'address': addr
            }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    var lat = results[0].geometry.location.lat();
                    var lng = results[0].geometry.location.lng();
                  //GOOGLE MAP DISTANCE
                	var origin1 = new google.maps.LatLng(24.968009, 121.191711);
                	var destinationA = new google.maps.LatLng(lat, lng);
                	var service = new google.maps.DistanceMatrixService();
                	service.getDistanceMatrix(
                	  {
                	    origins: [origin1],
                	    destinations: [destinationA],
                	    travelMode: 'DRIVING',
                	  }, callback);

                	function callback(response, status) {
                		if (status == 'OK'){
                		    var origins = response.originAddresses;
                		    for (var i = 0; i < origins.length; i++) {
                		      var results = response.rows[i].elements;
                		        var element = results[i];
                		        var distance = element.distance;
                		        if(distance.value > 10000){
                		        	$('#distance').html("距離為"+distance.text+",超出外送範圍請重新輸入地址");
                		        	$('#distance').css("color","red");
                		        	$('#insert').attr('disabled',true);
                		        	document.getElementById("checkout").style.display='none';
                		        }else{
                		        	$('#distance').html("距離為"+distance.text+",可接受外送");
                		        	$('#distance').css("color","green");
                		        	$('#insert').attr('disabled',false);
                		        	document.getElementById("checkout").style.display='inline';
                		        }
                		        
                		    }
                		 }
                	}
            }
        }); 
        }else{
        	$('#distance').html(" ");
        	$('#insert').attr('disabled',false);
        }
    } 
//GOOGLE MAP DISTANCE 輸入後判斷
$("#address").blur(function(){
	//地址轉成經緯度   
    var geocoder = new google.maps.Geocoder();
    addr = $("#address").val();
    if(addr != ""){
    geocoder.geocode({
            'address': addr
        }, function (results, status) {
            if (status == google.maps.GeocoderStatus.OK) {
                var lat = results[0].geometry.location.lat();
                var lng = results[0].geometry.location.lng();
              //GOOGLE MAP DISTANCE origin1 為資策會的經緯度
            	var origin1 = new google.maps.LatLng(24.968009, 121.191711);
            	var destinationA = new google.maps.LatLng(lat, lng);
            	var service = new google.maps.DistanceMatrixService();
            	service.getDistanceMatrix(
            	  {
            	    origins: [origin1],
            	    destinations: [destinationA],
            	    travelMode: 'DRIVING',
            	  }, callback);

            	function callback(response, status) {
            		if (status == 'OK') {
            		    var origins = response.originAddresses;
            		    for (var i = 0; i < origins.length; i++) {
            		      var results = response.rows[i].elements;
            		        var element = results[i];
            		        var distance = element.distance;
            		        if(distance.value > 10000){
            		        	$('#distance').html("距離為"+distance.text+",超出外送範圍請重新輸入地址");
            		        	$('#distance').css("color","red");
            		        	$('#insert').attr('disabled',true);
            		        	document.getElementById("checkout").style.display='none';
            		        }else{
            		        	$('#distance').html("距離為"+distance.text+",可接受外送");
            		        	$('#distance').css("color","green");
            		        	$('#insert').attr('disabled',false);
            		        	document.getElementById("checkout").style.display='inline';
            		        }
            		        
            		    }
            		 }
            	}
        }
    }); 
    }else{
    	$('#distance').html(" ");
    	$('#insert').attr('disabled',false);
    }
 	
})
</script>
</body>
</html>
