<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.managerView {
	margin-top: 5px;
	margin-bottom: 5px;
}

.managerMenu {
	padding: 3px 15px;
	font-size: 15px;
	font-weight: bold;
	line-height: 20px;
	color: #999999;
}

.form-horizontal .control-label {
	margin-left: -100px;
}

.profileState {
	padding-top: 7px;
	color: #999999;
}

a:hover,a:active {
	color: red;
	text-decoration: none;
}
</style>
</head>
<body>
	<%@ include file="../nav/userNav.jsp"%>
	<script type="text/javascript" src="<%=basePath%>myjs/managerCenter/profile.js"></script>
	<div class="panel">
		<div style="padding-bottom:20px;">
			<span style="color:red;">首页</span>&nbsp;<span>/&nbsp;管理中心首页</span>
		</div>

		<div class="row">
			<div class="col-md-2">

				<ul class="nav nav-pills nav-stacked">
					<li role="presentation"><a href="<%=basePath%>user/manager-center.action">管理中心首页</a>
					</li>
					<li role="presentation" class="managerMenu">账号配置</li>
					<li role="presentation" class="active">
					<a href="<%=basePath%>user/managercenter/profile.action"><span
							class="glyphicon glyphicon-asterisk" style="padding-right:5px;"></span>个人资料</a>
					</li>
					<li role="presentation" class="managerMenu">内容管理</li>
					<li role="presentation"><a href="<%=basePath%>user/managercenter/vote-manager.action"><span
							class="glyphicon glyphicon-tasks" style="padding-right:5px;"></span>投票管理</a>
					</li>
					<li role="presentation"><a href="<%=basePath%>user/managercenter/image-manager.action"><span
							class="glyphicon glyphicon-picture" style="padding-right:5px;"></span>图片管理</a>
					</li>
				</ul>
			</div>

			<div class="col-md-10">
				<div class="row">
					<div class="col-md-12">&nbsp;</div>
				</div>

				<div class="row">
					<div class="col-md-3">
						<h4 style="color:#666666;">个人资料</h4>
					</div>
					<div class="col-md-9"></div>
				</div>

				<div class="row">
					<div class="col-md-3"></div>
					<div class="col-md-9">

						<div class="form-horizontal">
							<div class="form-group">
								<label for="inputEmail" class="col-md-2 control-label">Email</label>
								<div class="col-md-6">
									<input id="email" type="email" class="form-control" placeholder="Email">
								</div>
								<div class="profileState">必填,请输入您的常用邮箱作为登录名</div>
							</div>
							<div class="form-group">
								<label for="nickName" class="col-md-2 control-label">昵称</label>
								<div class="col-md-6">
									<input id="nickName" type="text" class="form-control" placeholder="请输入昵称">
								</div>
							</div>

							<div class="form-group">
								<label for="oldPassworf" class="col-md-2 control-label">原密码</label>
								<div class="col-md-6">
									<input id="oldPassword" type="password" class="form-control"
										placeholder="请输入原密码">
								</div>
								<div class="profileState">如果修改密码，请输入原密码</div>
							</div>

							<div class="form-group">
								<label for="newPassworf" class="col-md-2 control-label">新密码</label>
								<div class="col-md-6">
									<input id="newPassword" type="password" class="form-control" placeholder="输入新密码">
								</div>
								<div class="profileState">不少于5个字符</div>
							</div>
							<div class="form-group">
								<label for="identifyPassworf" class="col-md-2 control-label">确认密码</label>
								<div class="col-md-6">
									<input id="newPassword2" type="password" class="form-control"
										placeholder="请重复输入新密码">
								</div>
								<div class="profileState">不少于5个字符</div>
							</div>

							<div class="form-group">
								<div class="col-md-10">
									<button  class="btn btn-default" onclick="changePassword();">保存</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>