function creatInputText(whichItem) {
	var InputText = document.createElement("div");
	InputText.innerHTML = "<div class='panel-body' style='background-color:#f5f5f5;'>"
			+ "<div style='float:right; margin-bottom:8px;'>"
			+ "<button type='button' class='btn btn-default operateButton'>确认</button>"
			+ "<button type='button' class='btn btn-default operateButton' style='margin-left: 8px;'>取消</button>"
			+ "</div>"
			+ "<div>"
			+ "<textarea  placeholder='200字以内' class='form-control' rows='3' style='resize:none;'></textarea>"
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
				confirmMess(whichItem,textArea);
			}
		} else {// 取消或者点击页面其他
			$("#Whole").unbind();
			cencleMess();
		}

	}
	return InputText;
}

function createNewChild(msg) {
	var inputText = (document.getElementsByName("InputText"))[0];
	var item = inputText.parentNode;
	var inputMess2 = ((inputText.getElementsByTagName("textarea"))[0]).value;
	var newChild = document.createElement("div");
	// 后台把回复主的名字提出来,以及question，以及回复内容
	var inputMess = inputMess2;// 先假设内容一样
	newChild.innerHTML = "<div class='panel-heading' style='padding:10px;background-color:#f5f5f5;border-top:1px solid #DDDDDD;border-bottom:1px solid #DDDDDD;'>"
			+ "<span name='name'>"
			+  msg.username
			+ "</span>"
			+ "<span>回复"
			+ msg.receivername
			+ "</span>-"+msg.buildTime+":"
//			+ "<div style='float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;'>"
//			+ "<span onclick='answerOther(this);'>评论</span>&nbsp;&nbsp;"
//			+ "</div>"
			+ "</div>"
			+ "<div class='panel-body' style='background-color:white;'>"
			+ inputMess
			+ "</div>";
	newChild.setAttribute("name", "item");
	newChild.id=msg.mBSeq;
	item.replaceChild(newChild, inputText);
}
function confirmMess(whichItem,which) {
	var divInput = document.getElementsByName("InputText")[0];
	var messageItem = divInput.parentNode;
	var titleItems = messageItem.getElementsByTagName("div");
	var titleItem = titleItems[0] ;
	var item =whichItem;
	var itemPosition = 1;//产生的对象的实际所在的位置
	for(var i=0;i<titleItems.length;i++){
		if(whichItem == titleItems[i]){
			receivePosition = i+1;
			break;
		}
	}
	
	console.log(titleItem);
	console.log(item);	
	var parentSeq = titleItem.id;//话题创造seq
	var receiveSeq = item.id;//回复评论对象seq
	var content = which.value;
	// 向后台发送该数据，成功则显示，否则说失败
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/user/message-board!comment.action',
		dataType : 'json',
		data : {
			'parentSeq' : parentSeq,
			'receiveSeq' : receiveSeq,
			'content' : content,
			'itemPosition':itemPosition
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功
				createNewChild(msg);
			} 			
		},
		error : function() {
			alert("连接异常");
		}
	});
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
	messageItem.appendChild(creatInputText(item));
//	if (j < children.length) {
//		messageItem.insertBefore(creatInputText(item), children[j + 1]);
//	} else if (j == children.length) {
//		messageItem.appendChild(creatInputText(item));
//	}
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

function addMessage(msg, content) {
	var messageboard = document.getElementById("messageBoard");
	var child = document.getElementsByName("messageItem");

	var mydiv = document.createElement("div");
	mydiv.setAttribute("name", "messageItem");	
	mydiv.setAttribute("class", "panel panel-default");
	
	mydiv.innerHTML = "<div id='"+msg.mBSeq+"' name='item'>"
			+ "<div class='panel-heading'style='padding:10px;background-color:white; border-bottom: 1px solid #DDDDDD;'>"
			+msg.username+"-创建于"+msg.buildTime+":"
//			+ "<div style='float:right;font-size: 15px;font-weight: bold;line-height: 20px;color: #999999;'>"
//			+ "<span onclick='answerOther(this);'>评论</span> &nbsp;&nbsp;"
//			+ "</div>"
			+ "</div>"
			+ "<div class='panel-body'>"+content+"</div>" + "</div>";
	if (child.length > 0) {
		messageboard.insertBefore(mydiv, child[0]);
	} else {
		messageBoard.appendChild(mydiv);
	}
}



function submitUrl() {
	var myMessageValue = document.getElementById("messageValue");
	
		$.ajax({
				// async:false,
				type : 'post',
				url : 'http://localhost:8080/ovs/user/message-board!insertMessageBoard.action',
				dataType : 'json',
				data : {
					'content' : myMessageValue.value
				},
				success : function(msg) {
					if (msg.status == 1) {
						// 提交成功
						alert(msg.message);
						addMessage(msg,myMessageValue.value);
						myMessageValue.value = "";
					} else {
						alert(msg.message);
					}
				},
				error : function() {
					alert("连接异常");
				}
			});
}