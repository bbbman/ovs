var grobalUrl = "http://localhost:8080/ovs/";
function change2One(which){
	debugger;
	if(which.value == 0)which.value=1;
	else which.value = 0;
}
function seeMoreSet()
{
	var MoreSetDiv = document.getElementById("moreSet");
	var ViewSetDiv = document.getElementById("ViewOfMoreSet");
	MoreSetDiv.setAttribute("style","display:none;");
	ViewSetDiv.setAttribute("style","display:block;");
}
function addAct()
{
	var addActs = document.getElementById("addActivity");
	var ViewActivity = document.getElementById("Activity");
	addActs.setAttribute("style","display:none;");
	ViewActivity.setAttribute("style","display:block;");
}
function addResultAfterVote()
{
	var addActs = document.getElementById("seeResultAfterVote");
	var ViewActivity = document.getElementById("ResultAfterVote");
	addActs.setAttribute("style","display:none;");
	ViewActivity.setAttribute("style","display:block;");
}



var titleTemp=undefined;
var optionTemp = undefined;
function loadTemp(){
	titleTemp = document.getElementsByName("title")[0].innerHTML;
	optionTemp = document.getElementsByName("option")[0].innerHTML;
	var timestamp=new Date().getTime();
	var title = document.getElementsByName("title")[0];
	var inputs = title.getElementsByTagName("input");
    inputs[1].setAttribute("name",timestamp);
    inputs[2].setAttribute("name",timestamp);
}
function orderTitle(){
	var titlePositions = document.getElementsByName("titlePosition");
	for(var i= 0 ; i< titlePositions.length ;i++){
		titlePositions[i].innerText = i+1;
	}
}
function addTitle(which){
	var titleParent = document.getElementById("titleList");
	var titles = document.getElementsByName("title");
	var whichTitle = which.parentNode.parentNode.parentNode.parentNode.parentNode;


	var newTitle = document.createElement("div");
	newTitle.setAttribute("name","title");
	newTitle.innerHTML = titleTemp;
	var i=0;
	for(;i< titles.length;i++){
		if(titles[i] == whichTitle)
			break;
	}
	if (i==titles.length-1) {
		titleParent.appendChild(newTitle);
	}else{
		titleParent.insertBefore(newTitle,titles[i+1]);
	}
	var timestamp=new Date().getTime();
	var inputs = newTitle.getElementsByTagName("input");
	    inputs[1].setAttribute("name",timestamp);
	    inputs[2].setAttribute("name",timestamp);
	orderTitle();
}
function removeTitle(which){

	var titleParent = document.getElementById("titleList");
	var titles = document.getElementsByName("title");
	var whichTitle = which.parentNode.parentNode.parentNode.parentNode.parentNode;
	if(titles.length==1){
		alert("不能删除唯一的标题");		
	}else{
		titleParent.removeChild(whichTitle);
	    orderTitle();
	}	
}

function moveUpTitle(which){
	var titleParent = document.getElementById("titleList");
	var titles = document.getElementsByName("title");
	var whichTitle = which.parentNode.parentNode.parentNode.parentNode.parentNode;
	if(titles[0]==whichTitle){

	}else{
		var i=0;
		for(;i<titles.length;i++){
			if(titles[i]==whichTitle)
				break;
		}
		downOne = titles[i-1].cloneNode(true);
		upOne   = whichTitle.cloneNode(true);
		titleParent.replaceChild(downOne,whichTitle);
		titleParent.replaceChild(upOne,titles[i-1]);
		 orderTitle();
	}
}
function moveDownTitle(which){
	var titleParent = document.getElementById("titleList");
	var titles = document.getElementsByName("title");
	var whichTitle = which.parentNode.parentNode.parentNode.parentNode.parentNode;
	if(titles[titles.length-1]==whichTitle){

	}else{
		var i=0;
		for(;i<titles.length;i++){
			if(titles[i]==whichTitle)
				break;
		}
		 upOne   = titles[i+1].cloneNode(true);
		 downOne = whichTitle.cloneNode(true);
		titleParent.replaceChild(downOne,titles[i+1]);
		titleParent.replaceChild(upOne,whichTitle);
		 orderTitle();
	}
}

function getOptionList(){}

function orderOption(which){
	var whichOption = which.parentNode.parentNode.parentNode.parentNode;
	var optionList = whichOption.parentNode;
	var optionPositions = document.getElementsByName("optionPosition");
	var opArr = new Array();
	for(var i=0 ; i<optionPositions.length;i++){
		if (optionPositions[i].parentNode.parentNode.parentNode.parentNode == optionList)
			opArr.push(optionPositions[i]);
	}
	for(var j=0;j<opArr.length;j++){
		opArr[j].innerText = j+1;
	}
}
function addOption(which){
	var whichOption = which.parentNode.parentNode.parentNode.parentNode;
	var optionList = whichOption.parentNode;
	var options = optionList.children;	
	var newOption = document.createElement("div");
	newOption.setAttribute("name","option");
	newOption.setAttribute("class","form-inline");
	newOption.innerHTML = optionTemp;
	var i=0;
	for(;i<options.length-1;i++){
		if (options[i]==whichOption) {
			break;
		}
	}
	optionList.insertBefore(newOption,options[i+1]);
	orderOption(which);
}
function removeOption(which){
	var whichOption = which.parentNode.parentNode.parentNode.parentNode;
	var optionList = whichOption.parentNode;
	var options = optionList.children;
	if(options.length==2){
		alert("选项不能为空");
		return;
	}
	optionList.removeChild(whichOption);
	orderOption(which);
}
function moveUpOption(which){
	var whichOption = which.parentNode.parentNode.parentNode.parentNode;
	var optionList = whichOption.parentNode;
	var options = optionList.children;
	if(options[0]==whichOption){

	}else{
		var i =0;
		for(;i<options.length;i++){
			if (options[i]==whichOption)
				break;
		}
		var upOne = whichOption.cloneNode(true);
		var downOne = options[i-1].cloneNode(true);
		optionList.replaceChild(upOne,options[i-1]);
		optionList.replaceChild(downOne,whichOption);
		orderOption(which);
	}
}
function moveDownOption(which){
	var whichOption = which.parentNode.parentNode.parentNode.parentNode;
	var optionList = whichOption.parentNode;
	var options = optionList.children;
	if(options[options.length-2]==whichOption){

	}else{
		var i =0;
		for(;i<options.length;i++){
			if (options[i]==whichOption)
				break;
		}
		var  downOne = whichOption.cloneNode(true);
		var upOne = options[i+1].cloneNode(true);
		optionList.replaceChild(upOne,whichOption);
		optionList.replaceChild(downOne,options[i+1]);
		orderOption(which);
	}
}

function showPhoto(){
	var test = document.getElementById("exampleInputFile");
	var img = document.getElementById("showChosePhoto");


	var fso, f, s;
    fso = new ActiveXObject("Scripting.FileSystemObject");
    f = fso.GetFile(test);
    s = f.DateCreated;
}
function removeImage(){
	var hfThumbnail = document.getElementById("hfThumbnail");
	var uploadImage = document.getElementById("uploadImage");
	hfThumbnail.value="\\test\\userImages\\cc\\20160216084116.jpeg";
	uploadImage.value="";
	
}
//获取表单元素
//提交数据
function getSingleChose(choseName){
	var radioArray = document.getElementsByName(choseName);
	for(var i = 0;i<radioArray.length;i++){
		if(radioArray[i].checked)
			return radioArray[i];
	}
}

function successPath(path){
	var createHtmlDiv = document.getElementById("createHtml");
	var parent = createHtmlDiv.parentNode;
	var pathDiv = document.createElement("div");
	pathDiv.innerHTML="<div class='input-group' style='margin-right:30%;'>"
			+"<span class='input-group-addon' id='basic-addon1'>投票地址</span> " 
			+"<input type='text' class='form-control' placeholder='Username' aria-describedby='basic-addon1' value='"+path+"'>"
			+"<span class='input-group-btn'><button class='btn btn-default' style='background-color:#eeeeee;'type='button'>复制</button> </span>"
		    +"</div>";
	parent.replaceChild(pathDiv, createHtmlDiv);
	
}

function Page(){
	  pageTitle:undefined;
	  fastVote:undefined;
	  privateVote:undefined;
	  displayAfterVote:undefined;
	  deadLine :undefined;
	  voteDesc :undefined;
	  seeAfterVote :undefined;
	  addCollect :undefined;
	  agreeTerm  :undefined;
	
	  titleList :undefined;
}
function Title(){
	  titleType :undefined;
	  titleContent :undefined;
	  titlePosition :undefined;
	  otherOption :undefined;
	  otherOptionContent :undefined;	
	  optionList :undefined;
}
function Option(){
	  optionContent :undefined;
	  optionPosition :undefined;
	  imageId :undefined;
}
function getOption(optionList){
	var optionArr = new Array();
	var options = optionList.children;
	for(var i=0;i<options.length-1;i++){
		optionDiv = options[i];
		var option =  new Option();
		input = optionDiv.getElementsByTagName("input")[0];
		option.optionContent = input.value;
		option.optionPosition = input.parentNode.children[0].innerText;
		option.imageId = input.parentNode.children[2].getAttribute("value");
		optionArr.push(option);
	}
	return optionArr;
}
function getOtherOption(otherOption,title){
	var inputs = otherOption.getElementsByTagName("input");
	title.otherOption = inputs[1].value;
	title.otherOptionContent = inputs[0].value;
}
function getTitleContent(titleContent){
	var title = new Title();
	var inputs = titleContent.getElementsByTagName("input");
	title.titleContent = inputs[0].value;
	if(inputs[1].checked){
		title.titleType=0
	}else{
		title.titleType=1
	}
	title.titlePosition = titleContent.children[0].children[1].children[0].innerText;
	return title;
}
function getTitleList(){
	var titleArr = new Array();
	var titleDivs = document.getElementsByName("title");
	for(var i=0;i<titleDivs.length;i++){
		var titleContent = titleDivs[i].children[0];
		var optionList =  titleDivs[i].children[1];
		var otherOption = optionList.children[optionList.children.length-1];
		var title = getTitleContent(titleContent);
		    getOtherOption(otherOption,title);
		    title.optionList = getOption(optionList);
		    titleArr.push(title);
	}
	return titleArr;
}
function getPageDetail(){	
	var page = new Page();	
	page.pageTitle = document.getElementsByName("titleContent")[0].value;
	page.fastVote = getSingleChose("fastVote").value;
	page.privateVote = getSingleChose("privateVote").value;
	page.displayAfterVote =  document.getElementById("displayAfterVote").value;
	page.deadLine = document.getElementById("deadLine").value;
	page.voteDesc = document.getElementById("voteDesc").value;
	page.seeAfterVote = getSingleChose("seeAfterVote").value;
	page.addCollect = document.getElementById("addCollect").value;
	page.agreeTerm = document.getElementById("agreeTerm").value;
	page.titleList = getTitleList();
	return page;
}

function submitFormData(){	
	var page = getPageDetail();
	console.log(JSON.stringify(page));
	jQuery.ajaxSettings.traditional = true;
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/create-vote!createHtml.action',
		dataType:'json',
		data:{'requestParam':JSON.stringify(page)},
		success:function(msg){
			if(msg.status==1){
				//提交成功
				alert("成功收集数据");
				successPath(msg.data);
				console.log(msg);
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(msg){
			console.log(msg);
		}
	});			
}
