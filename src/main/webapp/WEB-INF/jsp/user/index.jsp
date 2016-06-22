<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/userNav.jsp" %>
</head>
<body>
<div class="jumbotron">
  <h1>欢迎您！</h1>
  <p>在这里我们将为您提供免费的投票平台</p>
  <p><a class="btn btn-primary btn-lg" href="<%=basePath%>/user/create-vote.action" role="button">创建投票</a></p>
</div>

  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title">普通投票</h3>
    </div>
    <c:if test="${empty nOImgList }">
    <div class="panel-body">
      <div>暂无数据</div>
    </div>
    </c:if>
    <c:if test="${!empty nOImgList }">
     <c:forEach items="${nOImgList }" var="page">
     <div class="panel-body">
      <div><a target="_Blank" href="<%=basePath%>${page.url}">${page.mainTitle }</a><div class="panel-text">(当前已有${page.submitTimes }人参与投票)</div></div>
    </div>
     </c:forEach>
    </c:if>               
 </div>
  <div class="panel panel-default">
    <div class="panel-heading">
      <h3 class="panel-title">图片投票</h3>
    </div>
    <c:if test="${empty imgList }">
    <div class="panel-body">
      <div>暂无数据</div>
    </div>
    </c:if>
    <c:if test="${!empty imgList }">
     <c:forEach items="${imgList }" var="page">
     <div class="panel-body">
      <div><a target="_Blank" href="<%=basePath%>${page.url}">${page.mainTitle }</a><div class="panel-text">(当前已有${page.submitTimes }人参与投票)</div></div>
    </div>
     </c:forEach>
    </c:if>        
 </div>
</body>
</html>