<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/adminNav.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/myjs/admin/help-manager.js"></script>
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

a:hover,a:active {
	color: red;
	text-decoration: none;
}
</style>
</head>
<body>
	<div id="Whole" style="margin-top:-30px;">
		<div class="panel">
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
						<li role="presentation"><a
							href="<%=basePath%>admin/sum-manager.action"> <span
								class="glyphicon glyphicon-stats" style="margin-left:48px;padding-right:5px;"></span>统计管理
						</a></li>
						<li role="presentation"><a
							href="<%=basePath%>admin/message-manager.action"> <span
								class="glyphicon glyphicon-pencil" style="margin-left:48px;padding-right:5px;"></span>留言管理
						</a></li>
						<li role="presentation" class="active"><a
							href="<%=basePath%>admin/help-manager.action"> <span
								class="glyphicon glyphicon-comment" style="margin-left:48px;padding-right:5px;"></span>帮助管理
						</a></li>
					</ul>
				</div>

				<div class="col-md-9" style="margin-left:-8px;">
					<div class="row" style="margin-bottom:20px;">
						<ul class="nav nav-tabs">
							<li><a style="font-size:25px;" href="#HelpMarket" data-toggle="tab"
								onclick="showHelpButton();" value="${countHelpBoard }">帮助小篮子(<span name="countHelpBoard">${countHelpBoard
									}</span>)</a>
							</li>
							<li><a style="font-size:25px;" href="#tab2" data-toggle="tab"
								onclick="hidenHelpButton();" value="${countHelpCenter }">网友提问(${countHelpCenter
									})</a>
							</li>
						</ul>
						<div id="addHelp" style="float:right;visibility:hidden;">
							<div style="margin-top:5px;margin-left:20px;float:right;">
								<button class="btn btn-default btn-md" onclick="addHelpItem();">新添帮助</button>
							</div>
							<div style="margin-top:5px;margin-left:20px;float:right;">
								<button class="btn btn-default btn-md" onclick="delHelpItem();">删除所选</button>
							</div>
						</div>
					</div>

					<div class="row tab-content">

						<div id="HelpMarket" class="tab-pane">							
							<c:if test="${!empty HelpBoardList }">
								<c:forEach items="${HelpBoardList }" var="helpBoard"
									varStatus="helpBStatus">
									<div id="${helpBoard.helpBoardId }" name="helpItem"
										class="panel panel-default">
										<div class="checkbox" style="float:left">										
											<input type="checkbox" name="HelpChecks">											
										</div>
										<div class="panel-heading" style="font-size:20px;padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;"											
												<span name="spanCount" style="color:red">${helpBStatus.count }-:</span>${helpBoard.helpTitle}											
										</div>
										<div class="panel-body">${helpBoard.helpContent }</div>
									</div>
								</c:forEach>
							</c:if>
						</div>

						<div id="tab2" class="tab-pane">															
								<c:if test="${!empty helpCenterList }">
									<c:forEach items="${helpCenterList }" var="helpCenter" varStatus="helpCStatus">
									<div name="messageItem" class="panel panel-default">
										<div id="${helpCenter.helpId }" name="item">
											<div class="panel-heading"
												style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
												<span name="name">${helpCenter.username }</span>-提问于-${helpCenter.buildTimeString }:
												<div
													style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
													<c:if test="${user.userId != helpCenter.userId }">
														<span onclick="answerOther(this);" style="cursor: pointer;">回复</span> &nbsp;&nbsp;
													</c:if>
													<span onclick="delMess(this)" style="cursor: pointer;">删除</span>
												</div>
											</div>
											<div class="panel-body">${helpCenter.content }</div>
										</div>
									</div>
								</c:forEach>
								</c:if>
							
						</div>
					</div>
					
					<div class="row">
						<div class="col-md-12" style="margin-top:-10px;text-align:center;font-size:25px;">
							<c:if test="${empty HelpBoardList }">暂无帮助项</c:if>
							<c:if test="${empty helpCenterList }">暂无网友提问</c:if>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>