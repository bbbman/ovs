<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ include file="nav/userNav.jsp"%>
<link rel="stylesheet" type="text/css"
	href="<%=basePath%>mycss/analis.css">
	<link rel="stylesheet" type="text/css"
	href="<%=basePath%>mycss/metroStyle.css">
<style type="text/css">
	#buttonArea {
		position:absolute; 
		top: 56px; 
		right:48px;
	}
	
	#buttonArea > select {
		float:left;
		width:110px; 
	}
	
	.aButton {
		margin-left: 6px;
		background-color: white;
		border: 1px solid #DDDDDD;
	}
	
	.aButton:hover {
		color: white;
		background-color: red;
	}
</style>
<script type="text/javascript">
		</script>
</head>
<body>
	<script src="<%=basePath%>dist/Highcharts-4.2.3/js/highcharts.js"></script>
	<script src="<%=basePath%>dist/Highcharts-4.2.3/js/modules/data.js"></script>
	<script
		src="<%=basePath%>dist/Highcharts-4.2.3/js/modules/drilldown.js"></script>
	<script src="<%=basePath%>myjs/analis.js"></script>
	<script src="<%=basePath%>dist/tree.js"></script>
	<script src="<%=basePath%>dist/jquery.ztree.core-3.5.min.js"></script>
	
	<div class="main-warpper">
	<section class="main-con clearfix">
	<div class="menu-tree-container pull-left">
		<div class="tree-box">
			<ul id="tree-item" class="ztree"></ul>
		</div>
		<div class="tree-bar-switch">
			<i class="fa fa-angle-left fa-2x tree-open"></i>
		</div>
	</div>
	<div id="outerContainer">
		<div id="buttonArea">
			
    	</div>	
		<div id="container" page=${pageSeq } style="min-width: 310px; height: 450px;">
			
		</div>
	</div>
	</section>
	</div>		
</body>
</html>