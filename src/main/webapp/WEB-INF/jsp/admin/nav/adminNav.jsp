<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>在线投票系统</title>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<base href="<%=basePath%>">
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>bootstrap/css/bootstrap.min.css">
<script type="text/javascript"
	src="<%=basePath%>/bootstrap/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/bootstrap/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>mycss/mynav.css">
</head>
<body style="background-color: white;padding:81px 0px 0px;">
	<nav class="myheadernav navbar navbar-default navbar-fixed-top"  style="border-bottom:none;">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand myBrand" href="#">在线投票系统</a>
		</div>
		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li <c:if test="${pageActive eq 'people' }">class="active"</c:if>>
					<a href="<%=basePath%>admin/people-manager.action">人员管理</a>
				</li>
				<li <c:if test="${pageActive eq 'vote' }">class="active"</c:if>>
					<a href="<%=basePath%>admin/vote-manager.action">投票管理</a>
				</li>
				<li <c:if test="${pageActive eq 'sum' }">class="active"</c:if>>
					<a href="<%=basePath%>admin/sum-manager.action">统计管理</a>
				</li>
				<li <c:if test="${pageActive eq 'message' }">class="active"</c:if>>
					<a href="<%=basePath%>admin/message-manager.action">留言管理</a>
				</li>
				<li <c:if test="${pageActive eq 'help' }">class="active"</c:if>>
					<a href="<%=basePath%>admin/help-manager.action">帮助管理</a>
				</li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-expanded="false"> <c:if
							test="${!empty user}">${user.username }</c:if> <c:if
							test="${empty user }">Guest</c:if>
						<ul class="dropdown-menu" role="menu">
							<c:if test="${!empty user}">
								<li><a href="<%=basePath%>login!loginOut.action">注销</a>
								</li>
							</c:if>
							<c:if test="${empty user }">
								<li><a href="<%=basePath%>login.action">登录</a>
								</li>
								<li><a href="<%=basePath%>register.action">注册</a>
								</li>
							</c:if>
						</ul>
				</li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
</body>
</html>