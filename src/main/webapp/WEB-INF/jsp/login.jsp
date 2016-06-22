<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<c:if test="${loginType  eq 'admin' }">
	<%@ include file="admin/nav/adminNav.jsp"%>
	</c:if>
	<c:if test="${loginType  eq 'user' }">
	<%@ include file="user/nav/userNav.jsp"%>
	</c:if>
	<script type="text/javascript"
	src="myjs/login.js"></script>
		<div class="panel" style="margin-left:150px;margin-right:400px;">

			<legend>
				<h3>登录</h3>
			</legend>
			<div class="form-horizontal vote-question">

				<div class="form-group">
					<label for="inputEmail3" class="control-label">Email</label>
					<div class="controls">
						<input name="email" type="email" class="form-control" id="inputEmail3"
							style="width:300px" placeholder="请输入邮箱地址">
					</div>
				</div>

				<div class="form-group">
					<label for="inputPassword3" class="control-label">密码</label>
					<div class="controls">
						<input name="password" type="password" class="form-control" id="inputPassword3"
							style="width:300px" placeholder="请输入密码">
					</div>
				</div>


				<div class="form-group">
					<div class="controls">
						<div class="checkbox">
							<label> <input type="checkbox"> 记住状态 </label>
						</div>
					</div>
				</div>

				<div class="form-group">
					<div class="controls">
						<button class="btn btn-default" onclick="submitLogin();">登录</button>
						&nbsp;<a href="register.action">注册</a> &nbsp;<a href="#">忘记密码?</a>
					</div>
				</div>
			</div>
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
					<li><a href="#" target="_blank">单选投票样例</a>
					</li>
					<li><a href="#" target="_blank">多选投票样例</a>
					</li>
				</ul>
			</div>
		</div>
</body>
</html>