<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">

    .managerView{
      margin-top: 5px;
      margin-bottom: 5px;
    }
    .managerMenu{
      padding:3px 15px;
      font-size:15px;
      font-weight:bold;
      line-height:20px;
      color:#999999;
    }
    .managerItem{
      text-align:center;
      margin-top:30px;

    }
a:hover, a:active{
 color:red;
 text-decoration:none;
}
</style>
</head>
<body>
<%@ include file="nav/userNav.jsp" %>
    <div class="panel" style="background-color: white;">
  <div style="padding-bottom:20px;"><span style="color:red;">首页</span>&nbsp;<span>/&nbsp;管理中心首页</span></div>

<div class="row">
  <div class="col-md-2">

    <ul class="nav nav-pills nav-stacked">
      <li role="presentation" class="active"><a href="<%=basePath%>user/manager-center.action">管理中心首页</a></li>
      <li role="presentation" class="managerMenu">账号配置</li>
      <li role="presentation"><a href="<%=basePath%>user/managercenter/profile.action"><span class="glyphicon glyphicon-asterisk" style="padding-right:5px;"></span>个人资料</a></li>
      <li role="presentation" class="managerMenu">内容管理</li>
      <li role="presentation"><a href="<%=basePath%>user/managercenter/vote-manager.action"><span class="glyphicon glyphicon-tasks" style="padding-right:5px;"></span>投票管理</a></li>
      <li role="presentation"><a href="<%=basePath%>user/managercenter/image-manager.action"><span class="glyphicon glyphicon-picture" style="padding-right:5px;"></span>图片管理</a></li>
    </ul>
  </div>

  <div class="col-md-10" style="color:blue;">
    <div class="row">
      <div class="col-md-12"></div>
    </div>

    <div class="row">
      <div class="col-md-3" style="text-align:center;">
        <a href="<%=basePath%>user/managercenter/profile.action">
        <span class="glyphicon glyphicon-asterisk" style="font-size:60px"></span>
        <br>个人资料
        </a>
      </div>
      <div class="col-md-9"></div>
    </div>

    <div class="row">   
      <div class="col-md-3 managerItem">
        <a href="<%=basePath%>user/managercenter/vote-manager.action">
        <span class="glyphicon glyphicon-tasks" style="font-size:60px"></span>
        <br>投票管理
        </a>
      </div>
      <div class="col-md-3 managerItem">
        <a href="<%=basePath%>user/managercenter/image-manager.action">
        <span class="glyphicon glyphicon-picture"  style="font-size:60px"></span>
        <br>图片管理
        </a>
      </div>  
      <div class="col-md-6"></div>   
    </div>
  </div>
</div>
</div>
</body>
</html>