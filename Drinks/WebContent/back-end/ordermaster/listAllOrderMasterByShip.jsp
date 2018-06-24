<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="com.ordermaster.model.*"%>
<%@ page import="com.orderdetail.model.*"%>
<%@ page import="java.util.List"%>
<%@ page import="java.io.*"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<title>OrderMaster</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link rel="stylesheet"
	href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">

<style type="text/css">
.search {
	font-size: x-large;
}

body, td, th, input {
	font-size: 12px;
	text-align: center;
}

img {
	height: 100px;
	width: 100px;
}

.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>
</head>
<body>
	<div class="container">
		<div class="row">
				<jsp:include page="/back-end/toolBar.jsp" />
				<jsp:include page="/back-end/breadBar.jsp" />
				<jsp:include page="/back-end/ordermaster/listAllOrderMasterOut.jsp" />
				<jsp:include page="/back-end/ordermaster/listAllOrderMasterTake.jsp" />
			</div>
		</div>
</body>
</html>
