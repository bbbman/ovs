var grobalUrl ="http://localhost:8080";
function creatInputText() {
	var InputText = document.createElement("div");
	InputText.innerHTML = "<div class='panel-body'>"
			+ "<div>"
			+ "<div style='float:right;'>"
			+ "<button type='button' class='btn btn-default operateButton'>"
			+ "确认</button>"
			+ "<button type='button' class='btn btn-default operateButton'>"
			+ "取消</button>"
			+ "</div>"
			+ "</div>"
			+ "<div>"
			+ "<textarea  placeholder='200字以内' class='form-control' rows='3'></textarea>"
			+ "</div>" + "</div>";
	InputText.setAttribute("name", "InputText");
	// 绑定事件
	document.getElementById("Whole").onmousedown = function(event) {
		var nowObject = event.target;
		var oldInputText = (document.getElementsByName("InputText"))[0];
		if (oldInputText == undefined)
			return;
		var buttons = oldInputText.getElementsByTagName("button");
		var textArea = (oldInputText.getElementsByTagName("textarea"))[0];
		if (nowObject == textArea) {// 鼠标在输入框内，不做处理

		} else if (nowObject == buttons[0]) {// 鼠标点击确认键
			// 检查是否有输入内容
			if (textArea.value == "") {
				textArea.setAttribute("placeholder", "输入不能为空");
			} else {
				$("#Whole").unbind();
				confirmMess();
			}
		} else {// 取消或者点击页面其他
			$("#Whole").unbind();
			cencleMess();
		}

	}
	return InputText;
}

function createNewChild() {
	var inputText = (document.getElementsByName("InputText"))[0];
	var item = inputText.parentNode;
	var inputMess2 = ((inputText.getElementsByTagName("textarea"))[0]).value;
	var newChild = document.createElement("div");
	// 后台把回复主的名字提出来,以及question，以及回复内容
	var answer = "answer";
	var question = "question";
	var inputMess = inputMess2;// 先假设内容一样
	newChild.innerHTML = "<div class='panel-heading' style='padding:0px 0px;background-color:#f5f5f5;'>"
			+ "<span name='name'>"
			+ answer
			+ "</span>"
			+ "<span>回复"
			+ question
			+ "</span>-2015-12-11 11:01:49:"
			+ "<div style='float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;'>"
			+ "<span onclick='answerOther(this);'>回复</span>&nbsp;&nbsp;"
			+ "</div>"
			+ "</div>"
			+ "<div class='panel-body'>"
			+ inputMess
			+ "</div>";
	newChild.setAttribute("name", "item");
	item.replaceChild(newChild, inputText);
}
function confirmMess() {
	// 向后台发送该数据，成功则显示，否则说失败
	// 成功，则执行下面操作
	createNewChild();
}
function cencleMess() {
	var inputText = (document.getElementsByName("InputText"))[0];
	var item = inputText.parentNode;
	item.removeChild(inputText);
}
function answerOther(which) {
	var grandparent = which.parentNode.parentNode;
	var item = grandparent.parentNode;
	var messageItem = item.parentNode;
	var childDIVs = messageItem.getElementsByTagName("div");
	var children = new Array();
	for ( var i = 0; i < childDIVs.length; i++) {

		if (childDIVs[i].getAttribute("name") == "item")
			children.push(childDIVs[i]);
	}
	var j = 0;
	for (j; j < children.length; j++) {
		if (children[j] == item)
			break;
	}
	if (j < children.length) {
		messageItem.insertBefore(creatInputText(), children[j + 1]);
	} else if (j == children.length) {
		messageItem.appendChild(creatInputText());
	}
	;
	// 定位光标
	((document.getElementsByTagName("textarea"))[0]).focus();
}
function delMess(which) {
	// 先向后台发送删除指令，成功后执行
	var grandparent = which.parentNode.parentNode;
	var item = grandparent.parentNode;
	var messageItem = item.parentNode;
	var tab_panel = messageItem.parentNode;
	tab_panel.removeChild(messageItem);
}
function showMess(messageType){	
	 window.location.href=grobalUrl+"/ovs/admin/message-manager!showMess.action?messageType="+messageType;	 
}
function  operator(which,messageType,operaterType){
	var item = which.parentNode.parentNode.parentNode;
	var operatorName;
	switch(operaterType){
	case 0:operatorName = "passExamine";break;
	case 1:operatorName = "agnoredMess";break;
	case 2:operatorName = "moveUnRead";break;
	case 3:operatorName = "delMess";break;
	case 4:operatorName = "moveUnRead";break;
	case 5:operatorName = "clearTeam";break;
	default:return;
	}
	$.ajax({
		//async:false,
		type:'post',
		url:grobalUrl+'/ovs/admin/message-manager!'+operatorName+'.action',
		dataType:'json',
		data:{'messageId':item.id},
		success:function(msg){
			alert(msg.message);
			if(msg.status==1){
				//提交成功
				 window.location.href=grobalUrl+"/ovs/admin/message-manager!showMess.action?messageType="+messageType;
			}
		},
		error:function(msg){
			alert("连接异常");
		}
	});	
}

