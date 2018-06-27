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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="js/jquery.min.js"></script>
<title>Stored</title>

<style>
td, th {
	white-space: nowrap;
	font-size: 1.5em;
}

input {
	padding: 5px 15px;
	border: 2px black solid;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}
</style>
</head>

<body>
	<jsp:include page="/front-end/header.jsp" />

	<!--<form action="<%=request.getContextPath()%>/Deposit_recordsServlet" method="post"> -->
	<table style="margin-top: 5%;" ; width="809" height="500" border="0"
		align="center" cellpadding="2" cellspacing="2">
		<tr>
			<td height="141" colspan="5" align="center" valign="middle"><img
				src="<%=request.getContextPath()%>/front-end/pic/222.png"
				style="width: 50px; height: 50px"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-size: 2em">點數儲值</font></td>
		</tr>
		<td colspan="5" align="center"><c:if
				test="${not empty errorMsgs}">
				<font color='red' size='3'>請修正以下錯誤:</font>
				<c:forEach var="message" items="${errorMsgs}">
					<ul>
						<li style="font-size: 3px; color: red; width: 200px">${message}</li>
					</ul>
				</c:forEach>
			</c:if></td>
		<tr>
			<td width="224" align="right"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-weight: 1.5em; font-size: 1em">您剩餘的點數
					:</font></td>
			<td colspan="3" style="padding-left: 20px;" id="point">${memVO.mem_point}</td>
		</tr>
		<tr>
			<td valign="middle" width="224" align="right"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-weight: 1.5em; font-size: 1em">請輸入儲值金額
					:</font></td>
			<td width="201" style="padding-left: 20px;"><input name="amount"
				type="text" id="amount" size="10" onBlur="Showamount()" /></td>
			<td width="205" align="center"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-weight: 1.5em; font-size: 1em">儲值獲得點數
					: </font></td>
			<td width="153" align="left" id="Storedamount"
				style="padding-left: 10px;"></td>
		</tr>

		<tr>
			<td align="right"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-weight: 1.5em; font-size: 1em">信用卡卡號
					:</font></td>
			<td colspan="3" style="padding-left: 15px;"><input
				name="cardNumber" type="text" id="cardNumber" size="40" /></td>
		</tr>
		<tr>
			<td align="right"><font
				style="font-family: 微軟正黑體; font-weight: bold; font-weight: 1.5em; font-size: 1em">信用卡密碼
					: </font></td>
			<td colspan="3" style="padding-left: 15px;"><input
				name="cardPassword" type="password" id="cardPassword" size="40" /></td>
		</tr>
		<td height="45" colspan="3" align="right"><a
			href="<%=request.getContextPath()%>/front-end/index.jsp"><input
				class="btn btn-primary" type="button" value="回首頁" /></a> <input
			class="btn btn-primary" type="submit" name="button" id="button33"
			value="確認儲值" /> <input type="hidden" name="action" value="stored">
			</form></td>
		<td>
			<form action="<%=request.getContextPath()%>/Deposit_recordsServlet"
				method="post">
				<input style="margin-left: 3px;" class="btn btn-primary"
					type="submit" value="查看儲值紀錄" /> <input type="hidden" name="mem_id"
					value="<%=memVO.getMem_id()%>"> <input type="hidden"
					name="action" value="Record_value">
				<!-- </form> -->
		</td>

	</table>
	<jsp:include page="/front-end/footer.jsp" />



	<script type="text/javascript">
		
		function Showamount() {
			var amount = document.getElementById("amount").value;
			if(amount>=1000){
				var n = amount/1000;
				amount = ((50*n)+amount/1);
			}
		    document.getElementById("Storedamount").innerHTML = amount;
		};
</script>


	<script src="<%=request.getContextPath() %>/front-end/js/sweetalert2.all.js"></script>
	<script>
$("#button33").on('click',function(){
	var cardNumber = $("#cardNumber").val();
	var cardPassword =$("#cardPassword").val();
	var amount=$("#amount").val();
	if(cardNumber.trim()==''){
		$('#cardNumber').focus();
		swal({
			  title: '請輸入信用卡卡號!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else if(cardPassword.trim()==''){
		$('#cardPassword').focus();
		swal({
			  title: '請輸入信用卡密碼!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else if(amount.trim()==''){
		$('#amount').focus();
		swal({
			  title: '請輸入儲值金額!',
			  animation: false,
			  customClass: 'animated tada'
			});
	}else{
	swal({
		 title: "確定要儲值 ?",
		  text: "You are sure to store value ?",
		  type: "warning",
			  type: 'warning',
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: '確定儲值',
			  showCancelButton: true,
		}).then((result) =>{
			if(result.value){
			var cardNumber = $("#cardNumber").val();
			var cardPassword =$("#cardPassword").val();
			var amount=$("#amount").val();
			var mem_id = "${memVO.mem_id}" ;
			  $.ajax({
				  url: "<%=request.getContextPath()%>/Deposit_recordsServlet",
				  data: {
					  "action":"stored",
				  	  "mem_id": mem_id,
				  	  "cardNumber":cardNumber,
				  	  "cardPassword":cardPassword,
				  	  "amount":amount
				  },
				  dataType: "json",
				  cache:false,
				  type:"post",
				  
				  success:function(data){
					  if (data.isDone == false) {
							swal({
								type : 'error',
								title : '輸入資料有誤',
								text : data.errorMsgs,
							})
					} else {
					  $("#amount").val("");
					  $("#cardNumber").val("");
					  $("#cardPassword").val("");
					  console.log(data.point)
					  document.getElementById("point").innerHTML = data.point;
					  document.getElementById("Storedamount").innerHTML = "";
					  swal("Good job!", "儲值成功!", "success")
						}
				  },
				  error:function(response,textStatus,errorThrown){
					  debugger;
					  console.log(textStatus);
				  }
			  })
			}
		
		})
	}
});

</script>




</body>
</html>

