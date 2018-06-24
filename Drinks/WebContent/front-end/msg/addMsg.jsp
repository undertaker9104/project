<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="<%=request.getContextPath()%>/front-end/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-xs-12 col-sm-8 col-sm-offset-2">
				<button type="submit" class="btn btn-warning" data-toggle="collapse"
					href="#cc1" aria-expanded="false" aria-controls="#cc1">
					留言發表<span class="glyphicon glyphicon-send"></span>
				</button>
				<div class="collapse" id="cc1">
					<form>
						<div class="form-group">
							<label for="userName"></label>
							<textarea class="form-control" rows="10"></textarea>
						</div>
						<div class="text-right">
							<a href="#" class="btn btn-info btn-lg btn-block">送出</a>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<script src="https://code.jquery.com/jquery.js"></script>
	
</body>
</html>