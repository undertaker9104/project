<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
<style>
input[type="email"], input[type="text"], input[type="phone"], input[type="password"]
	{
	padding: 5px 15px;
	background: #ccc;
	border: 0 none;
	cursor: pointer;
	-webkit-border-radius: 5px;
	border-radius: 5px;
}
</style>
<script>
	var loadFile = function(event) {
		var output = document.getElementById('output');
		output.src = URL.createObjectURL(event.target.files[0]);
	};
</script>
</head>

<body>
	<jsp:include page="/front-end/header.jsp" />
	<form id="uploadForm" enctype="multipart/form-data"
		style="height: 150%">
		<div class="container">
			<div class="row">
				<div class="col-xs-12 col-sm-12" style="padding-top: 2%">


					<div class="panel panel-info">
						<div class="panel-heading">
							<h3 class="panel-title">
								<img
									src="<%=request.getContextPath()%>/front-end/pic/register.png"
									style="height: 180px; width: 300px;">
							</h3>
						</div>
						<div class="panel-body" align="center">
							<table>
								<tr>
									<td style="width: 500px;">
										<div class="form-group">
											<label align="right" class="col-xs-3  control-label"
												for="username" style="padding-top: 5px;">使用者名稱 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-user"></i></span> <input id="username"
														name="username" placeholder="username"
														class="form-control" type="text" value="${param.username}" />
												</div>
											</div>
										</div>
									</td>
								</tr>
								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3  control-label"
												for="email" style="padding-top: 5px;">E-mail :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-envelope"></i></span> <input
														id="email" name="email" placeholder="E-mail"
														class="form-control" type="email" value="${param.email}" />
												</div>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3  control-label"
												for="password" style="padding-top: 5px;">密碼 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-edit"></i></span> <input id="password"
														name="password" placeholder="Password"
														class="form-control" type="password"
														value="${param.password}" />
												</div>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3  control-label"
												for="phone" style="padding-top: 5px;">手機號碼 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-phone"></i></span> <input id="phone"
														name="phone" placeholder="09xxxxxxxx" class="form-control"
														type="phone" value="${param.phone}" />
												</div>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3  control-label"
												for="address" style="padding-top: 5px;">地址 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"> <i
														class="glyphicon glyphicon-home"></i></span> <input id="address"
														name="address" placeholder="居住地址" class="form-control"
														type="text" value="${param.address}" />
												</div>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3 control-label"
												style="padding-top: 5px;">生日 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-calendar"></i></span> <input
														id="birthday" name="birthday" placeholder="xxxx-xx-xx"
														class="form-control" type="date" value="${param.birthday}" />
												</div>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3 control-label"
												style="padding-top: 5px;">性別 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<label for="male">男</label> <input type="radio" name="sex"
													id="male" value="男"> <label for="female">女</label>
												<input type="radio" name="sex" id="female" value="女"
													checked="true"><br>
											</div>
										</div>

									</td>
								</tr>

								<tr>
									<td style="padding-top: 2%;">
										<div class="form-group">
											<label align="right" class="col-xs-3 control-label"
												style="padding-top: 5px;">上傳大頭照 :</label>
											<div class="col-xs-9 inputGroupContainer">
												<div class="input-group">
													<span class="input-group-addon"><i
														class="glyphicon glyphicon-picture"></i></span> <input id="file"
														name="mem_pic" accept="image/*" onchange="loadFile(event)"
														class="form-control" type="file" value="${param.mem_pic}" /><img
														id="output" />
												</div>
											</div>
										</div>

									</td>
								</tr>


								<tr>
									<td style="padding-top: 4%;" align="center"><input type="hidden"
										name="action" value="Register"> <input
										class="btn btn-primary" type="button" name="Register"
										id="button33" value="確定註冊" /> <input class="btn btn-primary"
										type="reset" name="reset" id="reset" value="重新填寫" /> <a
										href="<%=request.getContextPath()%>/front-end/index.jsp">
											<input class="btn btn-primary" type="button" value="取消註冊" />
									</a></td>
								</tr>
							</table>
						</div>
					</div>
				</div>
			</div>
		</div>

	</form>
	<jsp:include page="/front-end/footer.jsp" />

	<script
		src="<%=request.getContextPath()%>/front-end/js/sweetalert2.all.js"></script>
	<script>
$("#button33").on('click',function(){
	swal({ title: "資料確認無誤", 
		  text: "Confirm registration", 
		  type: "info", 
		  showCancelButton: true, 
		  closeOnConfirm: false, 
		  showLoaderOnConfirm: true, 
	}).then((result) =>{
			if(result.value){
				var formData = new FormData();
				formData.append('username',$("#username").val());
				formData.append('email',$("#email").val());
				formData.append('password',$("#password").val());
				formData.append('phone',$("#phone").val());
				formData.append('address',$("#address").val());
				formData.append('birthday',$("#birthday").val());
				formData.append('sex',$('input[name=sex]:checked').val());
				formData.append('file',$('#file')[0].files[0]);
				formData.append('action',"Register");	
				$.ajax({
					  cache: false,
					  processData: false,
					  contentType: false,
					  url: "<%=request.getContextPath()%>/MemberServlet",
											data : formData,
											dataType : "json",
											type : "post",
											success : function(data) {
												console.log(1);
												if (data.isDone == false) {
													swal({
														type : 'error',
														title : '輸入資料有誤',
														text : data.errorMsgs,
													})
												} else {
													setTimeout(function(){ 
														swal("註冊成功！", "","success").then(function (result) {
														  window.location="<%=request.getContextPath()%>/front-end/mem/Login.jsp";
											            });		
													  }, 500);
																
						}
				  },
				  error:function(response,textStatus,errorThrown){
					  debugger;
					  console.log(textStatus);
				  }
			  })
			}
		
		})
});
	

	   
	</script>
</body>
</html>
