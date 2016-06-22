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

.managerItem {
	text-align: center;
	margin-top: 30px;
}

a:hover,a:active {
	color: red;
	text-decoration: none;
}

.thumbnail>img {
	height: 200px;
}
</style>
</head>
<body>
	<%@ include file="../nav/userNav.jsp"%>
	<script type="text/javascript"
		src="<%=basePath%>myjs/managerCenter/image-manager.js"></script>
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
					<li role="presentation"><a
						href="<%=basePath%>user/managercenter/vote-manager.action"><span
							class="glyphicon glyphicon-tasks" style="padding-right:5px;"></span>投票管理</a>
					</li>
					<li role="presentation" class="active"><a
						href="<%=basePath%>user/managercenter/image-manager.action"><span
							class="glyphicon glyphicon-picture" style="padding-right:5px;"></span>图片管理</a>
					</li>
				</ul>

			</div>

			<div class="col-md-10">
				<c:if test="${empty imageList  }">
					<div class="row" id="ImgMenu" align="center" style="padding-top:20%,padding-bottom:20%">
						<div>暂无上传图片</div>
				</c:if>
				<c:if test="${!empty imageList}">
					<div class="row" id="ImgMenu">
						<c:forEach items="${imageList }" var="image">
							<div class="col-sm-6 col-md-4" value="${image.imageId }">
								<div class="thumbnail">
									<img src="${image.imagePath }" alt="NoPhoto">
									<div class="caption">
										<p>
											<a href="${image.url }">${image.mainTitle }</a>
										</p>
										<p>浏览:${image.browseTimes }</p>
										<p>上传时间:${image.uploadTimeString }</p>
										<p>
											<span class="btn btn-default" role="button"
												onclick="delOneImg(this.parentNode.parentNode.parentNode.parentNode)">删除</span>
											<input type="checkbox" name="imggiud" />
										</p>
									</div>
								</div>
							</div>
						</c:forEach>
				</c:if>
			</div>

			<div class="row" style="text-align:center;">
				<div class="col-md-12">
					<input class="btn btn-default" type="button" value="全选"
						onclick="selectAll();"> <input class="btn btn-default"
						type="button" value="取消全选" onclick="cancleSelected();"> <input
						class="btn btn-default" type="button" value="批量删除"
						onclick="delSelectedImgS();">
				</div>
			</div>

			<div class="row" style="text-align:center;">
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