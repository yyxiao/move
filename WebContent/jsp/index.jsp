<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<title>mp账号迁移</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link href="res/css/bootstrap/bootstrap.css" rel="stylesheet" type="text/css" />
		
		<script src="res/js/jquery/jquery.js"></script>
		<script src="res/js/bootstrap/bootstrap.js"></script>
		
	</head>
	<body>
	   <div class="panel-body">
	      	<form class="form-horizontal" action="move/go" method="post" style="margin-top: 50px;">
			  <div class="form-group">
			    <label for="wechatId" class="col-sm-2 control-label">wechatId</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="wechatId" name="wechatId" placeholder="账号ID">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="mobile" class="col-sm-2 control-label">mobile</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="mobile" name="mobile" placeholder="手机号">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="email" class="col-sm-2 control-label">email</label>
			    <div class="col-sm-10">
			      <input type="email" class="form-control" id="email" name="email" placeholder="电子邮箱">
			    </div>
			  </div>
			  <div class="form-group">
			    <label for="password" class="col-sm-2 control-label">password</label>
			    <div class="col-sm-10">
			      <input type="text" class="form-control" id="password" name="password" placeholder="密码">
			    </div>
			  </div>
			  <div class="form-group">
			    <div class="col-sm-offset-2 col-sm-10">
			      <button type="submit" class="btn btn-default">Submit</button>
			    </div>
			  </div>
			</form>
	   </div>
	</body>
</html>