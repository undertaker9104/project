<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="<%=request.getContextPath()%>/front-end/js/bootstrap.min.js"></script>
<style type="text/css">
body, td, th, input {
	font-size: 12px;
	text-align: center;
}
.table th, .table td {
	text-align: center;
	vertical-align: middle !important;
	font-size: 16px;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<div class="container">
		<div class="row">
			<jsp:include page="/back-end/toolBar.jsp" />
			<jsp:include page="/back-end/breadBar.jsp" />
			<div class="col-xs-12 col-sm-9">
				<div class="page-header text-left">
					<div class="h1">
						<small>管理介面</small>
					</div>
				</div>
				<img src="<%=request.getContextPath()%>/front-end/img/images13.jpg "
					 style="width: 1200px; height: 850px">
			</div>
		</div>
	</div>
	 <h3 id="statusOutput" class="statusOutput"></h3>
	<script src="https://code.jquery.com/jquery.js"></script>
	<script src="<%=request.getContextPath() %>/front-end/js/sweetalert2.all.js"></script>
	
<script>
var MyPoint = "/MyEchoServer";
var host = window.location.host;
var path = window.location.pathname;
var webCtx = path.substring(0, path.indexOf('/', 1));
var endPointURL = "ws://" + window.location.host + webCtx + MyPoint;
var statusOutput = document.getElementById("statusOutput");
var webSocket;

function connect() {
	// 建立 websocket 物件
	webSocket = new WebSocket(endPointURL);
	
	webSocket.onopen = function(event) {
	
	};

	webSocket.onmessage = function(event) {
		var jsonObj = JSON.parse(event.data);
	     var message = jsonObj.userName ;
		swal({	
			  imageUrl: "/CA101G2/front-end/pic/123.png",
			  title: "有一則新的 <small>點數提取</small>!",
			  showCancelButton: true,
			  confirmButtonText:"立即處理",
			  text: "來自於:會員"+message, 
			}).then(function (result) {
                window.location.href = "<%=request.getContextPath()%>/back-end/mem/listAllModerated.jsp";
            });
		
	};

	webSocket.onclose = function(event) {
		updateStatus("WebSocket 已離線");
	};
}

function sendMessage() {

}

function disconnect () {
	webSocket.close();
}

</script>
	
</body>
</html>