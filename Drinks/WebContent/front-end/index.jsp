<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.mem.model.*"%>
<%@ page import="java.util.*"%>
<%@ page import="com.product.model.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="container-fluid">
	<div class="row">
		
			<div class="col-xs-12 col-sm-12">
				<jsp:include page="/front-end/header.jsp" />
				<jsp:include page="/front-end/SlideBar.jsp" />
				
				<div class="col-xs-12 col-sm-2">
					<jsp:include page="/front-end/announcement/announcement_siderbar.jsp" />
				</div>
				
				<div class="col-xs-12 col-sm-10 ">
				<jsp:include page="/front-end/product/listHotProduct.jsp" />
				<jsp:include
					page="/front-end/productclass/listAllProductByClass.jsp" />
			
				</div>
			</div>
			<jsp:include page="/front-end/footer.jsp" />
	</div>
</div>
