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
td, th  {
white-space: nowrap;
    font-size: 1.5em;

}

 

input{padding:5px 15px; border:2px black solid;
cursor:pointer;
-webkit-border-radius: 5px;
border-radius: 5px; }



</style>
</head>

<body>
<jsp:include page="/front-end/header.jsp"/>

<form action="<%=request.getContextPath()%>/Deposit_recordsServlet" method="post">
  <table style="margin-top:8%;"; width="809" height="500" border="0" align="center" cellpadding="2" cellspacing="2">
    <tr>
      <td colspan="4" align="center"><h2>點數儲值頁面</h2></td>
    </tr>
               <td colspan="5" align="center">
				<c:if test="${not empty errorMsgs}">
						<font color='red' size='3'>請修正以下錯誤:</font>
						<c:forEach var="message" items="${errorMsgs}">
							<ul><li  style="font-size:3px; color: red; width: 200px">${message}</li></ul>
						</c:forEach>
				</c:if>
				</td>
    <tr>
      <td width="224" align="right">您剩餘的點數 :</td>
      <td colspan="3" style="padding-left: 20px;">${memVO.mem_point}</td>
    </tr>
    <tr>
      <td valign="middle" width="224" align="right">請輸入儲值金額 :</td>
      <td width="201" style="padding-left: 20px;"><input name="amount" type="text" id="amount" size="10" onBlur="Showamount()"/></td>
      <td width="205" align="center">儲值獲得點數 : </td>
      <td width="153" align="left" id="Storedamount" style="padding-left:10px;"></td>
    </tr>
 
    <tr>
      <td align="right">信用卡卡號 : </td>
      <td colspan="3" style="padding-left: 15px;">
      <input name="cardNumber" type="text" id="cardNumber" size="40" /></td>
    </tr>
    <tr>
      <td align="right">信用卡密碼 : </td>
      <td colspan="3" style="padding-left: 15px;"><input name="cardPassword" type="password" id="cardPassword" size="40" /></td>
    </tr>
    <td height="45" colspan="3" align="right">
      <a href="<%=request.getContextPath()%>/front-end/index.jsp"><input class="btn btn-primary" type="button" value="回首頁"/></a>
      <input class="btn btn-primary" type="submit" name="button" id="button33" value="確認儲值" />
      <input type="hidden" name="action" value="stored">
      </form>
      </td>
      <td>
     <form action="<%=request.getContextPath()%>/Deposit_recordsServlet" method="post">
      <input style="margin-left:3px;" class="btn btn-primary" type="submit" value="查看儲值紀錄" />
	   <input type="hidden" name="mem_id" value="<%=memVO.getMem_id()%>">
	   <input type="hidden" name="action" value="Record_value">
  </form>
  </td>
   
  </table> 
  <jsp:include page="/front-end/footer.jsp"/>



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

<!-- 
<script src="https://cdn.jsdelivr.net/npm/sweetalert2"></script>
<script>
$("#button33").on('click',function(){
	swal({
	
		  title: '確認要儲存點數?',
		  type: 'warning',
		  showCancelButton: true,
		  confirmButtonColor: '#3085d6',
		  cancelButtonColor: '#d33',
		  confirmButtonText: 'Yes, do it!'
		}).then((result) => {
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
// 				  dataType: "json",
				  cache:false,
				  type:"post",
				  
				  success:function(response){
					  swal(
						      '儲值完成',
						    )  
				  },
				  error:function(response,textStatus,errorThrown){
					  debugger;
					  console.log(textStatus);
				  }
			  })
		
		})
});

</script>
-->



</body>
</html>

