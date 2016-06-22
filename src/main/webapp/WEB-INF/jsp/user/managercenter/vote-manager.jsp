<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="../nav/userNav.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>myjs/managerCenter/vote-manager.js"></script>
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
	<div class="panel">
		<div style="padding-bottom:20px;">
			<span style="color:red;">首页</span>&nbsp;<span>/&nbsp;管理中心首页</span>
		</div>

		<div class="row">

			<div class="col-md-2">
				<ul class="nav nav-pills nav-stacked">
					<li role="presentation"><a
						href="<%=basePath%>user/manager-center.action">管理中心首页</a>
					</li>
					<li role="presentation" class="managerMenu">账号配置</li>
					<li role="presentation"><a
						href="<%=basePath%>user/managercenter/profile.action"><span
							class="glyphicon glyphicon-asterisk" style="padding-right:5px;"></span>个人资料</a>
					</li>
					<li role="presentation" class="managerMenu">内容管理</li>
					<li role="presentation" class="active"><a
						href="<%=basePath%>user/managercenter/vote-manager.action"><span
							class="glyphicon glyphicon-tasks" style="padding-right:5px;"></span>投票管理</a>
					</li>
					<li role="presentation"><a
						href="<%=basePath%>user/managercenter/image-manager.action"><span
							class="glyphicon glyphicon-picture" style="padding-right:5px;"></span>图片管理</a>
					</li>
				</ul>
			</div>

			<div class="col-md-10">

				<div class="row">
					<div class="col-md-12"></div>
				</div>

				<div class="row">
					<div class="col-md-12">
						<table class="table">
							<tbody>
								<tr>
									<th>投票标题</th>
									<th>投票数</th>
									<th>投票类型</th>
									<th>创建时间</th>
									<th>截止日期</th>
									<th>操作</th>
								</tr>								
								
								<c:if test="${!empty pageList }">
									<c:forEach items="${pageList }" var="page">
										<tr name="test" id="${page.pageId }">
											<td><a target="_blank" href="<%=basePath%>${page.url}">${page.mainTitle }</a></td>
											<td style="text-align:center;">${page.submitTimes }</td>
											<td>${page.imageVoteString }</td>
											<td>${page.buildTimeString }</td>
											<td>${page.deadLineString }</td>
											<td><button type="button"
													class="btn btn-default operateButton"><a href="<%=basePath%>user/analysis.action?pageSeq=${page.pageId }">统计</a></button>
												<button type="button" class="btn btn-default operateButton"
													onclick="delPage(this);">删除</button>
												<button type="button" class="btn btn-default operateButton"
													onclick="clearZero(this)">清零</button></td>
										</tr>
									</c:forEach>
								</c:if>
							</tbody>
						</table>
						<c:if test="${empty pageList }">
								 暂无数据
						</c:if>
					</div>
				</div>


				<div class="row">
					<div class="col-md-12">
						<nav>
						<ul class="pagination">
							<li><a href="#" aria-label="Previous"><span
									aria-hidden="true">&laquo;</span> </a>
							</li>
							<li><a href="#">1</a>
							</li>
							<li><a href="#">2</a>
							</li>
							<li><a href="#">3</a>
							</li>
							<li><a href="#">4</a>
							</li>
							<li><a href="#">5</a>
							</li>
							<li><a href="#" aria-label="Next"><span
									aria-hidden="true">&raquo;</span> </a>
							</li>
						</ul>
						</nav>
					</div>
				</div>

			</div>

		</div>
	</div>
</body>
</html>