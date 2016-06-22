<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 
</head>
<body>
<%@ include file="WEB-INF/jsp/user/nav/userNav.jsp" %>
<script type="text/javascript"
	src="<%=basePath%>/bootstrap/js/jquery.json.js"></script>
<script type="text/javascript"
	src="<%=basePath%>/bootstrap/js/jquery-form.js"></script>
<script type="text/javascript" src="<%=basePath%>myjs/upload.js"></script>
<form id="form1" runat="server">  
<div style="width: 100%; float: left;">  
    <input type="hidden" id="hfThumbnail" value="C:/Users/bman/Desktop/u=2532954842,2646125942&fm=21&gp=0.jpg" />  
    <div class="imgdiv"></div>  
    <span class="img_span">  
        <input type="file" name=myImage />  
    </span>  
       
    <input type="button" value="上传" class="upload" />  
</div>  
<div style="width: 100%; float: left;">  
    <input type="hidden" id="hfThumbnail" value="C:/Users/bman/Desktop/u=2532954842,2646125942&fm=21&gp=0.jpg" />  
    <div class="imgdiv"></div>  
    <span class="img_span">  
        <input type="file" name="myImage" />  
    </span>  
       
    <input type="button" value="上传" class="upload" />  
</div>  
</form>  

</body>
</html>