<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>

<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>



<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

<html>

<head>

<base href="<%=basePath%>">



<title>My JSP 'fileUpLoad.jsp' starting page</title>



<meta http-equiv="pragma" content="no-cache">

<meta http-equiv="cache-control" content="no-cache">

<meta http-equiv="expires" content="0">

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">

<meta http-equiv="description" content="This is my page">

<!--  
  
     <link rel="stylesheet" type="text/css" href="styles.css" mce_href="styles.css">  
  
     -->



</head>



<body>

	<center>

		<s:form action="common/file-up-load!doee.action" method="POST" enctype="multipart/form-data">

			<s:file name="myImage" label="Image File1" />

			<s:textfield name="caption" label="Caption" />

			<s:submit />

		</s:form>

	</center>
	<span><a href="\test\vp\page\20160227102507-f7a82357-a27b-4032-8fa6-5a02020f9fbe.html">haha</a></span>

</body>

</html>
