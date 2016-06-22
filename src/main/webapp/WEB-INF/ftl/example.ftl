<!DOCTYPE html>
<html>
<head>
<title>在线投票</title>

<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="online vote">
<meta http-equiv="content-type" content="text/html; charset=UTF-8">

<link rel="stylesheet" type="text/css"
	href="${basePath}/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/mycss/mynav.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/mycss/example.css">	
<script type="text/javascript"
	src="${basePath}/bootstrap/js/jquery-1.11.1.js"></script>
<script type="text/javascript"
	src="${basePath}/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="${basePath}/myjs/example/example.js"></script>
</head>
<body onload="getVoteResult();" style="background-color: white;padding:11px 0px 0px;">
	<div class="panel">
		<div>
			<h1>${page.mainTitle}</h1>
		</div>
		<div style="background-color:#d9edf7;padding:6px 12px;">创建者&nbsp;${user.username}
			&nbsp;${page.buildTime}&nbsp; <span id="joinNum">0</span>人参与 &nbsp;浏览:<span id="browesNum">0</span>
		</div>
	</div>
	<#if page.voteDesc?? >
	 <#if page.voteDesc !="">
	 <div class="panel">
		<div>本次投票时针对民众对网络的认识</div>
	</div>
	 </#if>
	</#if>
	<div name="page" id="${page.pageId}" class="panel">


		<#list titleList as titleMap>

		<div id="${titleMap.title.titleId}" name="title">
		    <div name="titleContent">
               <span><span style='font-size:25px;'>${titleMap.title.titlePosition}.${titleMap.title.titleContent}</span></span>
            </div>
            <div name="optionList" style="margin-left:50px;">
              <#if titleMap.title.titleType == 0 >
			 <#list titleMap.optionList as option>
			  <div id="${option.optionId}" name="option">
				<div class="radio">
					<label class="voteItem"> 
					  <input type="radio" name="singleButton${titleMap.title.titleId}">
					  <span name="countOption">${option.optionPosition}</span> 
					  <span>.${option.optionContent}</span>
					</label>
				</div>
				<#if option.imagePath !="">
				<div class="thumbnail">
					<img src="${option.imagePath}">
					<div class="imgNum">1</div>
				</div>
				</#if>
			</div>			
		   </#list> 
		   </#if> 
		   
		   <#if titleMap.title.titleType == 1 > 
		      <#list titleMap.optionList as option>
			   <div id="${option.optionId}" name="option">
				  <div class="checkbox">
					<label class="voteItem">
					  <input type="checkbox" name="moreButton${ titleMap.title.titleId}">
					  <span name="countOption">${option.optionPosition}</span>
					  <span>.${option.optionContent}</span>
					</label>
				</div>
				<#if option.imagePath !="">
				<div class="thumbnail">
					<img src="${option.imagePath}">
					<div class="imgNum">1</div>
				</div>
				</#if>
			</div>
			</#list>
		  </#if> 
		  <#if titleMap.title.otherOption == 1>
			<br/>
			<div class="input-group" style="margin-right:30%;" name="otherOption">
				<span class="input-group-addon" id="basic-addon1">其他选择</span> 
				<input type="text" class="form-control" placeholder="${titleMap.title.titleOtherContent}" aria-describedby="basic-addon1" value="">
			</div>
			<br/>
		  </#if>
            </div>
			
		</div>
		</#list>
		
	 <#if page.displayAfterVote?? >
	   <#if page.displayAfterVote !="">
	    <div id="showAfterVote" style="display:none;">
			<div>${page.displayAfterVote}</div>
		</div>
	   </#if>
	</#if>

		<div id="submitMyVote">
			<button
				style="width:250px;height:60px;padding:0 0;margin-top:30px;margin-bottom:50px;font-size:25px;"
				type="button" class="btn btn-primary" onclick="submitSingleVote();">提交投票</button>
		</div>
		
		<div class="input-group" style="margin-right:30%;">
			<span class="input-group-addon" id="basic-addon1">投票地址</span> <input
				type="text" id="url" class="form-control" placeholder="Username"
				aria-describedby="basic-addon1"
				value="${url}">
			<span class="input-group-btn">
				<button class="btn btn-default" style="background-color:#eeeeee;"
					type="button">复制</button> </span>
		</div>
	</div>
	</div>
</body>
</html>
