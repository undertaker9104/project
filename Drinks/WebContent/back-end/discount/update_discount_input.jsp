<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.discount.model.*"%>
<%
	DiscountVO discountVO = (DiscountVO) request.getAttribute("discountVO"); 
%>
<html>
<head>
<title>優惠資料修改 </title></head>
<br><br>
<body>
<div class="container">
	<div class="row"><br><br><br><br>
<jsp:include page="/back-end/toolBar.jsp" />
		    <div class="col-xs-12 col-sm-9">
			<div class="page-header text-left">
				<div class="h1">
					管理介面<small>好康優惠更改</small>
				</div>
			</div>
			<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/back-end/discount.do" name="form1">
						<div class="form-group">
							<H1><label>好康優惠敘述:</label></H1>	
							<H1><input type="TEXT" class="form-control" name="dis_des" size="45" value="${discountVO.dis_des}"></H1>	
						</div>
						<div class="form-group">
							<H1><label>條件杯數:</label></H1>
							<H1>							
								<select size="1" name="dis_cup">
									<option value=0 ${(0 == discountVO.dis_cup) ?'selected':''} >0  
									<option value=5 ${(5 == discountVO.dis_cup) ?'selected':''} >5  
									<option value=10 ${(10 == discountVO.dis_cup) ?'selected':''}>10
									<option value=20 ${(20 == discountVO.dis_cup) ?'selected':''}>15
									<option value=20 ${(20 == discountVO.dis_cup) ?'selected':''}>20
								</select>
						   </H1>
						</div>
						<div class="form-group">
							<H1><label>杯數折扣:</label></H1>
							<H1>					
								<select size="1" name="dis_cup_rate">
								    <option value=1 ${(1 == discountVO.dis_cup_rate) ?'selected':''}>1
									<option value=0.9 ${(0.9 == discountVO.dis_cup_rate) ?'selected':''}>0.9
									<option value=0.8 ${(0.8 == discountVO.dis_cup_rate) ?'selected':''}>0.8
									<option value=0.7 ${(0.7 == discountVO.dis_cup_rate) ?'selected':''}>0.7					   			    
								</select>
							</H1>	
						</div>
						<div class="form-group">
							<H1><label>條件金額:</label></H1>
							<H1>
						  	<select size="1" name="dis_price">
									<option value=0 ${(0 == discountVO.dis_price) ?'selected':''}>0
									<option value=500 ${(500 == discountVO.dis_price) ?'selected':''}>500
									<option value=1000 ${(1000 == discountVO.dis_price) ?'selected':''}>1000
									<option value=1000 ${(1000 == discountVO.dis_price) ?'selected':''}>1500
								</select>
							</H1>
						</div>
						<div class="form-group">
							<H1><label>金額折扣:</label></H1>
							<H1>
							<select size="1" name="dis_price_rate">
									<option value=1   ${(1 == discountVO.dis_price_rate) ?'selected':''}>1 
									<option value=0.9 ${(0.9 == discountVO.dis_price_rate) ?'selected':''}>0.9		
									<option value=0.8 ${(0.8 == discountVO.dis_price_rate) ?'selected':''}>0.8
									<option value=0.8 ${(0.7 == discountVO.dis_price_rate) ?'selected':''}>0.8				        
								</select>
						   </H1>
						</div>
		     			<input type="hidden" name="action" value="update">
						<input type="hidden" name="dis_id" value="<%=discountVO.getDis_id()%>">
						<input type="submit" class="btn btn-success" value="送出修改">								
					</FORM>
		</div>
	</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font color='red'>請修正以下錯誤:
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li>${message}</li>
		</c:forEach>
	</ul>
	</font>
</c:if>
</body>
</html>
