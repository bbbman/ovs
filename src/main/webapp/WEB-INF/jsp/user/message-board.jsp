<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body style="background-color: white;padding:81px 0px 0px;">
<%@ include file="nav/userNav.jsp"%>
	<div id="Whole">		
		<script type="text/javascript"
			src="<%=basePath%>/myjs/messageBoard.js"></script>
		<div class="panel" style="background:url('<%=basePath%>images/messageBoard.jpg') top center no-repeat;
background-size:100% 100%; height:180px; position:relative; margin-top:30px; padding-bottom: 10px; border: 1px solid #DDDDDD;">
			<h2 style="margin-left: 8px;">留言板</h2>
			<div style="position: absolute; right: 5px; bottom:5px;">
				<button type="button" class="btn btn-default" data-toggle="modal"
					data-target="#addMyMessage">我要留言</button>
			</div>
		</div>

		<div id="messageBoard">
			<c:if test="${empty mBList }">
				<div style="text-align:center;">暂无留言</div>
			</c:if>
			<c:if test="${!empty mBList}">
				<c:forEach items="${mBList }" var="parent" >
				 <div name="messageItem" class="panel panel-default">
					<c:forEach items="${parent }" var="child" varStatus="childStatus" begin="0">						
						<c:if test="${childStatus.count == 1 }">
						<div id="${child.messageId }" name="item">
							<div class="panel-heading"
								style="padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;">
								${child.username }-创建于:${child.buildTimeString }
								
								<div
									style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
									<span onclick="answerOther(this);" style="cursor: pointer;">评论</span> &nbsp;&nbsp;
								</div>
								
							</div>
							<div class="panel-body" style="background-color:white;">${child.content }</div>
						</div>
						</c:if>
						<c:if test="${childStatus.count != 1 }">
							<div name="item" id="${child.messageId }">
									<div class="panel-heading"
										style="padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;">
										<span name="name">${child.username }</span><span>回复${child.receiveName}</span> ${child.buildTimeString }
										<c:if test="${user.userId != child.userId }">
											<div
												style="float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;">
												<span onclick="answerOther(this);" style="cursor: pointer;">评论</span>&nbsp;&nbsp;
											</div>
										</c:if>
									</div>
									<div class="panel-body" style="background-color:white;">${child.content }</div>
								</div>
						</c:if>									
					</c:forEach>
				 </div>
				</c:forEach>
			</c:if>
		</div>


		<div id="addMyMessage" class="modal fade">
			<div class="modal-dialog">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">我的留言</h4>
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
	</div>
</body>
</html>