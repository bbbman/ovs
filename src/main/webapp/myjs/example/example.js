
function submitSingleVote() {
	var requestParam = getData();
}
function RequestParam() {
		pageSeq:undefined;
		titleList:undefined;
}
function Title(){
		titleSeq:undefined;
		otherContent:undefined;
		optionList:undefined;
}
function dealOption(whichTitle){
	var optionList = new Array();
	var options =  whichTitle.getElementsByTagName("div");
	for(var i =0 ; i<options.length;i++){
		if(options[i].getAttribute("name")=="option")
		if(options[i].children[0].children[0].children[0].checked){
			optionList.push(options[i].id);
		}
	}
	return optionList;
}
function dealOtherOption(whichTitle){
	var childrens = whichTitle.getElementsByTagName("div");
	var otherOption = undefined;
	for( var i = 0 ; i< childrens.length ;i++){
		if(childrens[i].getAttribute("name") =="otherOption" ){
			otherOption = childrens[i];
			break;
		}
	}
	if(otherOption != undefined){
		
		var input = otherOption.getElementsByTagName("input")[0];
		
		return input.value;
	}
	 return "";
}
function getData(){
	var page = document.getElementsByName("page")[0];
	var titleList = document.getElementsByName("title");
	var titleArr = new Array();
	for(var i = 0 ; i < titleList.length ;i++){
		var optionArr =  dealOption(titleList[i]);
		var otherOptionContent = dealOtherOption(titleList[i]);
		if(otherOptionContent =="" && optionArr.length == 0)
			continue;
		var myTitle = new Title();
		myTitle.titleSeq = titleList[i].id;
		myTitle.otherContent = otherOptionContent;
		myTitle.optionList = optionArr;
		titleArr.push(myTitle);
	}
	
	var requestParam =  new RequestParam();
	requestParam.pageSeq = page.id;
	requestParam.titleList = titleArr;
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/vote!dealData.action',
		dataType:'json',
		data:{'requestParam':JSON.stringify(requestParam)},
		success:function(msg){
			console.log(msg)
			alert(msg.message);
			if(msg.status==1){
				//提交成功,刷新数据
				window.location.href=url;				
			}		
		},
		error:function(msg){
			alert("系统异常");
		}
	});
}
function buildOptionResultView(parent,vote,sum){
	var present=0
	if(sum!=0)
	    present = parseInt(vote/sum*100);
	var view = document.createElement("div");
	view.setAttribute("name","sum");
	view.innerHTML = "<div class='progress'>"
		            +"<div class='progress-bar' role='progressbar' aria-valuenow='"+present+"' aria-valuemin='0' aria-valuemax='100' style='min-width: 4em;width:"+present+"%;'>"
		            +vote+"/"+present+"%"
		            +"</div>"
		            +"</div> "	  	    	  	 
	parent.appendChild(view);
}
function showTitleTimes(titleContent,titleSelectTimes){
	console.log(titleContent)
	var span = titleContent.children[0];
	
	span.innerHTML = span.innerHTML+"("+titleSelectTimes+"选择)";
}
function showVoteResult(page){
	console.log(JSON.stringify(page));
	document.getElementById("joinNum").innerText = page.pageSubmitTimes;
	document.getElementById("browesNum").innerText = page.pageSubmitTimes;
	
	var titles = document.getElementsByName("title");
	var titleArr = page.titleList;
	for(var i=0; i< titles.length;i++){
		var optionList = titles[i].children[1].children;		
		var titleVote = titleArr[i];
		showTitleTimes(titles[i].children[0],titleVote.titleSelectTimes);
		var optionVoteList = titleVote.optionList;
		for(var j=0; j<optionList.length;j++){
			var option = optionList[j];
			var optionVote = optionVoteList[j];
			if(option.getAttribute("name")=="option"){
				buildOptionResultView(option,optionVote,titleVote.optionSelectTimes);
			}else if(option.getAttribute("name")=="otherOption"){
				buildOptionResultView(option.parentNode,titleVote.otherOptionSelectTimes,titleVote.optionSelectTimes);
			}
		}
	}
}
function showDisplayAfterVote(){
	var dav = document.getElementById("showAfterVote");
	if(dav ==undefined)
		return;
	dav.setAttribute("style", "");
}

var url;
function getVoteResult(){
	url = document.getElementById("url").value;
	var page = document.getElementsByName("page")[0];
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/vote!getVoteResult.action',
		dataType:'json',
		data:{'pageId':page.id},
		success:function(msg){
			console.log(msg);
			if(msg.status==1){//投票前显示结果
				showVoteResult(msg.data);
			}else if(msg.status==3){//已经投票后的结果
				showDisplayAfterVote();
				var submitDIV = document.getElementById("submitMyVote");
				submitDIV.innerHTML = "<br/><br/><br/><div class='alert'>已投票</div><br/><br/><br/>";				
				showVoteResult(msg.data);
			}else if(msg.status==4){//投票后显示结果，未投票
				
			}
		},
		error:function(msg){
			alert("系统异常");
		}
	});
}
