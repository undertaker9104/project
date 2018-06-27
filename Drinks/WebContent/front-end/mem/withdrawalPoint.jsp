
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.mem.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	MemberVO memVO = (MemberVO) session.getAttribute("memVO");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=BIG5">
<title>提取點數</title>
<style>
input {
	padding: 5px 15px;
	border: 2px black solid;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}
</style>
</head>
<body onload="connect();" onunload="disconnect();">
	<jsp:include page="/front-end/header.jsp" />
	<%-- <form method="post" action="<%=request.getContextPath()%>/Point_exc_cashServlet"> --%>
	<table style="margin-top: 1%" ; width="624" align="center"
		cellpadding="2" cellspacing="2">
		<tr>
			<td height="141" colspan="5" align="center" valign="middle"><img
				src="<%=request.getContextPath()%>/front-end/pic/111.png"
				style="width: 50px; height: 50px"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-size: 2.5em">提取點數</font></td>
		</tr>
		<tr>
			<td colspan="5" align="center"><c:if
					test="${not empty errorMsgs}">
					<font color='red'>請修正以下錯誤:</font>
					<c:forEach var="message" items="${errorMsgs}">
						<ul>
							<li style="color: red; width: 200px">${message}</li>
						</ul>
					</c:forEach>
				</c:if></td>
		</tr>
		<tr>
			<td width="177" height="70" align="right"><font size="4">可提款餘額
					:</font></td>
			<td width="186" align="center"><font color="red"><%=memVO.getMem_point()%></font></td>
			<td width="231" colspan="3">點</td>
		</tr>
		<tr>
			<td height="70" align="right"><font size="4">提款餘額 :</font></td>
			<td colspan="4" align="left" style="padding-left: 10px;"><input
				name="Balance" type="text" id="Balance" size="50"
				onblur="ShowaBalance()" /></td>
		</tr>
		<tr>
			<td height="70" align="right"><font size="4">實際到帳 :</font></td>
			<td align="center" id="money"></td>
			<td colspan="3">元</td>
		</tr>
		<tr>
			<td height="70" align="right" valign="middle"><font size="4">帳號戶名
					:</font></td>
			<td align="center"><%=memVO.getMem_name()%></td>
		</tr>
		<tr>
			<td height="70" align="right"><font size="4">銀行帳號 :</font></td>
			<td colspan="4" style="padding-left: 10px;"><input
				name="BankAccount" type="text" id="BankAccount" size="50" /></td>
		</tr>
		<tr>
			<td height="70" align="right"><font size="4">支付密碼：</td>
			<td colspan="4" style="padding-left: 10px;"><input
				name="mem_pwd" type="password" id="mem_pwd" size="50" /></td>
		</tr>
		<tr>
			<td height="48" colspan="4" align="right"><a
				href="<%=request.getContextPath()%>/front-end/mem/storedPoint.jsp"><input
					class="btn btn-primary" type="button" value="取消 回儲值頁面" /></a> <input
				class="btn btn-primary" type="submit" id="sendMessage" onclick="sendMessage();" value="確定提取" />
				<input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
				<input type="hidden" name="action" value="Withdrawal"> 
		<!--        </form> -->
			</td>
			<td>
				<form method="post"
					action="<%=request.getContextPath()%>/Point_exc_cashServlet">
					<input style="margin-left: 3px;" class="btn btn-primary"
						type="submit" value="提取紀錄" /> <input type="hidden" name="mem_id"
						value="<%=memVO.getMem_id()%>"> <input type="hidden"
						name="action" value="Records">
				</form>
			</td>
		</tr>
	</table>
	<jsp:include page="/front-end/footer.jsp" />


	<script type="text/javascript">
		
		function ShowaBalance() {
			var Balance = document.getElementById("Balance").value;
			
				var Balance = Balance*0.95;
			
		    document.getElementById("money").innerHTML = Balance;
		};
</script>

	<script src="<%=request.getContextPath() %>/front-end/js/sweetalert2.all.js"></script>
	<script>
$("#sendMessage").on('click',function(){
	var BankAccount = $("#BankAccount").val();
	var mem_pwd =$("#mem_pwd").val();
	var Balance=$("#Balance").val();
	if(Balance.trim()==''){
		$('#Balance').focus();
		swal({
			  title: '請輸入提取金額!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else if(BankAccount.trim()==''){
		$('#BankAccount').focus();
		swal({
			  title: '請輸入銀行帳戶!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else if(mem_pwd.trim()==''){
		$('#mem_pwd').focus();
		swal({
			  title: '請輸入會員密碼!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else{
			var cardNumber = $("#Balance").val();
			var cardPassword =$("#BankAccount").val();
			var amount=$("#mem_pwd").val();
			var mem_id = "${memVO.mem_id}";
		
			  $.ajax({
				  url: "<%=request.getContextPath()%>/Point_exc_cashServlet",
											data : {
												"action" : "Withdrawal",
												"mem_id" : mem_id,
												"BankAccount" : BankAccount,
												"mem_pwd" : mem_pwd,
												"Balance" : Balance
											},
											dataType : "json",
											cache : false,
											type : "post",
											success : function(data) {
												if (data.isDone == false) {
													swal({
														type : 'error',
														title : '輸入資料有誤',
														text : data.errorMsgs,
													})
												} else {
													$("#BankAccount").val("");
													$("#mem_pwd").val("");
													$("#Balance").val("");
													document
															.getElementById("money").innerHTML = "";
													swal(
															"提取成功！",
															"已將資料送往審核，請耐心等待，會盡快為您處理 ",
															"success")
												}
											},
											error : function(response,
													textStatus, errorThrown) {
												debugger;
												console.log(textStatus);
											}
										})
							}
						})
	</script>
	
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
		
		};

		webSocket.onclose = function(event) {
			updateStatus("WebSocket 已離線");
		};
	}
	
	function sendMessage() {
		var id = "<%=memVO.getMem_id()%>";
		console.log(id);
		var jsonObj = {"userName" : id }
        webSocket.send(JSON.stringify(jsonObj));
	}

	function disconnect () {
		webSocket.close();
	}


    
</script>

</body>
</html>
