<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/adminNav.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>myjs/admin/vote-manager.js"></script>
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

.managerItem {
	text-align: center;
	margin-top: 30px;
}

.operateButton {
	padding: 2px 10px;
	font-size: 11.9px;
}

.voteNum {
	text-align: center;
}

a:hover,a:active {
	color: red;
	text-decoration: none;
}
</style>
</head>
<body>

	<div class="panel" style="margin-top:-30px;">
		<div style="padding-bottom:20px;">
			<h2 style="margin-bottom:0px;padding-bottom:0px;"><span style="color:red;">首页</span>&nbsp;<span>/&nbsp;管理中心首页</span></h2>
		</div>

		<div class="row">

			<div class="col-md-3">

				<ul class="nav nav-pills nav-stacked">
						<li role="presentation"><h3 style="margin-top:0px;padding-top:0px;"><span>&nbsp;&nbsp;&nbsp;管理员中心</span></h3>
						</li>
						<li role="presentation" class="managerMenu" style="margin-left:30px;">账号配置</li>
						<li role="presentation"><a
							href="<%=basePath%>admin/people-manager.action"> <span
								class="glyphicon glyphicon-user" style="margin-left:48px;padding-right:5px;"></span>人员管理
						</a></li>
						<li role="presentation" class="managerMenu" style="margin-left:30px;">内容管理</li>
						<li role="presentation"><a
							href="<%=basePath%>admin/vote-manager.action"> <span
								class="glyphicon glyphicon-tasks" style="margin-left:48px;padding-right:5px;"></span>投票管理
						</a></li>
						<li role="presentation"  class="active"><a
							href="<%=basePath%>admin/sum-manager.action"> <span
								class="glyphicon glyphicon-stats" style="margin-left:48px;padding-right:5px;"></span>统计管理
						</a></li>
						<li role="presentation"><a
							href="<%=basePath%>admin/message-manager.action"> <span
								class="glyphicon glyphicon-pencil" style="margin-left:48px;padding-right:5px;"></span>留言管理
						</a></li>
						<li role="presentation"><a
							href="<%=basePath%>admin/help-manager.action"> <span
								class="glyphicon glyphicon-comment" style="margin-left:48px;padding-right:5px;"></span>帮助管理
						</a></li>
					</ul>
			</div>

			<div class="col-md-9" style="margin-left:-8px;">
				<div class="row">
					<div class="col-lg-5" style="float:right;">
						<form action="<%=basePath%>admin/sum-manager!search.action"
							method="post" class="form-group">
							<div class="input-group">
								<input name="searchContent" type="text" class="form-control"
									placeholder="请输入要查找的用户名/投票标题">
								<div class="input-group-addon">
									<button type="submit">搜索</button>
								</div>
							</div>
						</form>
					</div>
				</div>
				<div class="row">
					<table class="table">
						<tbody>
							<tr>
								<th>#</th>
								<th>投票标题</th>
								<th>创建者</th>
								<th>创建时间</th>								
								<th>浏览次数</th>
								<th>投票次数</th>
								<th>操作</th>
							</tr>
							<c:if test="${!empty pageList }">
								<c:forEach items="${pageList }" var="page"
									varStatus="pageStatus">
									<tr>
										<td>${pageStatus.count }</td>
										<td><a target="_blank" href="<%=basePath%>${page.url}">${page.mainTitle
												}</a>
										</td>
										<td>${page.username }</td>
										<td>${page.buildTimeString }</td>										
										<td style="text-align:center;">${page.submitTimes }</td>
										<td style="text-align:center;">${page.submitTimes }</td>
										<td>
											<button type="button" class="btn btn-default operateButton"
												onclick="">统计总览</button>
											<button type="button" class="btn btn-default operateButton"
												onclick="">查看日志</button></td>
										<td></td>
									</tr>
								</c:forEach>
							</c:if>
						</tbody>
					</table>
					<c:if test="${empty pageList }">暂无相关数据</c:if>
				</div>

				<div class="row">
					<div class="col-md-12" style="margin-top:-10px;text-align:center;font-size:25px;">
							<c:if test="${empty pageList }">暂无相关数据</c:if>	
						</div>
				</div>

			</div>

		</div>









	</div>

</body>
</html>