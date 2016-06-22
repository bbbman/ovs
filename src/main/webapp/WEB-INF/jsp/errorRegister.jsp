<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%@ include file="user/nav/userNav.jsp"%>
	<div class="panel" style="margin-left:150px;margin-right:400px;">

		<legend>
			<h3>注册</h3>
		</legend>
		<form class="form-horizontal vote-question">

			<div class="form-group">
				<label for="inputEmail3" class="control-label">Email</label>
				<div class="controls">
					<input name = "email" type="email" class="form-control" style="width:300px"
						placeholder="请输入邮箱地址">
				</div>
			</div>

			<div class="form-group">
				<label for="inputEmail3" class="control-label">昵称</label>
				<div class="controls">
					<input name ="username" type="username" class="form-control" style="width:300px"
						placeholder="请输入昵称">
				</div>
			</div>




			<div class="form-group">
				<label for="inputPassword3" class="control-label">密码</label>
				<div class="controls">
					<input name="password" type="password" class="form-control" style="width:300px"
						placeholder="请输入密码">
				</div>
			</div>

			<div class="form-group">
				<label for="inputPassword3" class="control-label">确认密码</label>
				<div class="controls">
					<input type="password" class="form-control" style="width:300px"
						placeholder="请重复输入密码">
				</div>
			</div>



			<div class="form-group">
				<div class="controls">
					<button type="submit" class="btn btn-default">注册</button>
					&nbsp;<a href="#">登录</a>
				</div>
			</div>

		</form>

	</div>

	<div style="position:absolute;left:980px;top:80px;">
		<div>
			<h3>帮助</h3>
			<ul>
				<li><a href="#" target="_blank">怎样创建图片投票</a>
				</li>
				<li><a href="#" target="_blank">怎样给投票开启报名功能？？</a>
				</li>
			</ul>
		</div>

		<div>
			<h3>Demo</h3>
			<ul>
				<li><a href="/html/" target="_blank">单选投票样例</a>
				</li>
				<li><a href="/css/" target="_blank">多选投票样例</a>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>