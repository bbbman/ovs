<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/adminNav.jsp"%>
<script type="text/javascript"
	src="<%=basePath%>/myjs/admin/message-manager.js"></script>
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
<script type="text/javascript">
	
</script>
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
						<li role="presentation" class="active"><a
							href="<%=basePath%>admin/message-manager.action"> <span
								class="glyphicon glyphicon-pencil" style="margin-left:48px;padding-right:5px;"></span>留言管理
						</a></li>
						<li role="presentation" ><a
							href="<%=basePath%>admin/help-manager.action"> <span
								class="glyphicon glyphicon-comment" style="margin-left:48px;padding-right:5px;"></span>帮助管理
						</a></li>
					</ul>
				</div>

				<div class="col-md-9" style="margin-left:-8px;">
					<div class="row" style="margin-bottom:20px;">
						<ul class="nav nav-tabs">
							<li <c:if test="${!empty allMess }" > class="active"</c:if> onclick="showMess(3)"><a
								style="font-size:20px;" href="#tab" data-toggle="tab">全部留言板(${dataCount })</a>
							</li>
							<li <c:if test="${!empty unReadMess }" > class="active"</c:if> onclick="showMess(0)"><a
								style="font-size:20px;" href="#tab" data-toggle="tab">未审核留言板(${unReadCount })</a>
							</li>
							<li <c:if test="${!empty repliedMess }" > class="active"</c:if> onclick="showMess(2)"><a
								style="font-size:20px;" href="#tab" data-toggle="tab">审核通过的留言板(${repliedCount })</a>
							</li>
							<li <c:if test="${!empty ignoredMess }" > class="active"</c:if> onclick="showMess(1)"><a
								style="font-size:20px;" href="#tab" data-toggle="tab">已屏蔽的留言板(${ignoredCount })</a>
							</li>
						</ul>
					</div>
					<div class="row tab-content">
						<div>							
							<c:if test="${!empty mBList}">

								<c:if test="${!empty unReadMess }">
									<c:if test="${!empty mBList}">
										<c:forEach items="${mBList }" var="parent" varStatus="parentStatus">
											<div name="messageItem" class="panel panel-default">
												<c:forEach items="${parent }" var="child"
													varStatus="childStatus" begin="0">
													<c:if test="${childStatus.count == 1 }">													
														<div id="${child.messageId }" name="item">															
															<div class="panel-heading"
																style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
																<div style="float:left;color:red;font-size:18px;line-height: 20px">${parentStatus.count}</div>:${child.username }-创建于:${child.buildTimeString }
																 <div style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
									                               <span onclick="operator(this,0,0);" style="cursor: pointer;">通过审核</span> &nbsp;&nbsp;
									                               <span onclick="operator(this,0,1);" style="cursor: pointer;">屏蔽</span>
								                                 </div>																																	
															</div>
															<div class="panel-body"  style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
													<c:if test="${childStatus.count != 1 }">
														<div name="item" id="${child.messageId }">
															<div class="panel-heading"
																style="padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;">
																<span name="name">${child.username }</span><span>回复${child.receiveName}</span>
																${child.buildTimeString }																																																
															</div>
															<div class="panel-body" style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:forEach>
									</c:if>
								</c:if>	
								
								<c:if test="${!empty allMess }">
									<c:if test="${!empty mBList}">
										<c:forEach items="${mBList }" var="parent">
											<div name="messageItem" class="panel panel-default">
												<c:forEach items="${parent }" var="child"
													varStatus="childStatus" begin="0">
													<c:if test="${childStatus.count == 1 }">
														<div id="${child.messageId }" name="item">
															<div class="panel-heading"
																style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
																${child.username }-创建于:${child.buildTimeString }																	
															</div>
															<div class="panel-body"  style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
													<c:if test="${childStatus.count != 1 }">
														<div name="item" id="${child.messageId }">
															<div class="panel-heading"
																style="padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;">
																<span name="name">${child.username }</span><span>回复${child.receiveName}</span>
																${child.buildTimeString }																																
															</div>
															<div class="panel-body" style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:forEach>
									</c:if>
								</c:if>		
								
								<c:if test="${!empty repliedMess }">
									<c:if test="${!empty mBList}">
										<c:forEach items="${mBList }" var="parent">
											<div name="messageItem" class="panel panel-default">
												<c:forEach items="${parent }" var="child"
													varStatus="childStatus" begin="0">
													<c:if test="${childStatus.count == 1 }">
														<div id="${child.messageId }" name="item">
															<div class="panel-heading"
																style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
																${child.username }-创建于:${child.buildTimeString }																
																	<div style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
																		<span onclick="operator(this,2,4);" style="cursor: pointer;">回退</span>
																		<span onclick="operator(this,2,1);" style="cursor: pointer;">屏蔽</span>
																		&nbsp;&nbsp;
																	</div>
															</div>
															<div class="panel-body" style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
													<c:if test="${childStatus.count != 1 }">
														<div name="item" id="${child.messageId }">
															<div class="panel-heading"
																style="padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;">
																<span name="name">${child.username }</span><span>回复${child.receiveName}</span>
																${child.buildTimeString }																																																
															</div>
															<div class="panel-body" style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:forEach>
									</c:if>
								</c:if>	
								
								<c:if test="${!empty ignoredMess }">
									<c:if test="${!empty mBList}">
										<c:forEach items="${mBList }" var="parent">
											<div name="messageItem" class="panel panel-default">
												<c:forEach items="${parent }" var="child"
													varStatus="childStatus" begin="0">
													<c:if test="${childStatus.count == 1 }">
														<div id="${child.messageId }" name="item">
															<div class="panel-heading"
																style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
																${child.username }-创建于:${child.buildTimeString }																
																	<div style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
																		<span onclick="operator(this,1,4);" style="cursor: pointer;">回退</span>
																		<span onclick="operator(this,1,3);" style="cursor: pointer;">删除</span>
																		&nbsp;&nbsp;
																	</div>
															</div>
															<div class="panel-body"  style="background-color:white;">${child.content }</div>
														</div>
													</c:if>
													<c:if test="${childStatus.count != 1 }">
														<div name="item" id="${child.messageId }">
															<div class="panel-heading"
																style="padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;">
																<span name="name">${child.username }</span><span>回复${child.receiveName}</span>
																${child.buildTimeString }																																																
															</div>
															<div class="panel-body">${child.content }</div>
														</div>
													</c:if>
												</c:forEach>
											</div>
										</c:forEach>
									</c:if>
								</c:if>
								
								
								
													

							</c:if>


						</div>

					</div>

					<div class="row">
						<div class="col-md-12" style="margin-top:-10px;text-align:center;font-size:25px;">
							<c:if test="${empty mBList }">
							<c:if test="${empty executeFlag }">
								<div>暂无相关信息</div>
							</c:if>
							</c:if>	
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
</body>
</html>