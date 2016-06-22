<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%@ include file="nav/userNav.jsp"%>
	<script type="text/javascript" src="<%=basePath%>/myjs/helpCenter.js"></script>
	
	<div class="panel" style="background:url('<%=basePath%>images/help.jpg') top center no-repeat;
background-size:100% 100%; height:180px; position:relative; margin-top:30px; padding-bottom: 10px; border: 1px solid #DDDDDD;">
			<h2 style="margin-left: 8px;">帮助中心</h2>
			<div style="position: absolute; right: 5px; bottom:5px;">
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#addMyMessage">我有疑惑</button>
			</div>
		</div>			
	<div id="helpBoard">
		<c:if test="${data == NULL }">
			<div style="text-align:center;">暂无帮助项</div>
		</c:if>
		<c:if test="${data != NULL }">
			<c:forEach items="${data}" var="helpCenter">
				<div name="helpItem" class="panel panel-default">
					<div class="panel-heading" style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">${helpCenter.username}
						&nbsp; ${helpCenter.buildTimeString}</div>
					<div class="panel-body" style="background-color:white;">${helpCenter.content}</div>
				</div>
			</c:forEach>
		</c:if>		
	</div>



	<div id="addMyMessage" class="modal fade">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">向管理员求助</h4>
				</div>

				<div class="modal-body">
					<form class="form-horizontal">

						<div class="form-group">

							<div style="padding-left:10px;padding-right:10px;">
								<textarea id="messageValue" placeholder="200字以内"
									class="form-control" rows="6"></textarea>
							</div>
						</div>

						<div class="form-group">
							<div style="padding-left:275px;">
								<button type="button" class="btn btn-default"
									data-dismiss="modal" onclick="submitUrl()">确认</button>
								<button type="button" class="btn btn-default"
									data-dismiss="modal">取消</button>
							</div>
						</div>

					</form>
				</div>

			</div>
		</div>
	</div>
</body>
</html>