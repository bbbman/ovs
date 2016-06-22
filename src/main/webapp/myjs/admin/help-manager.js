var grobalUrl = "http://localhost:8080";
function hidenHelpButton() {
	document.getElementById("addHelp").setAttribute("style",
			"float:right;visibility:hidden;");
}
function showHelpButton() {
	document.getElementById("addHelp").setAttribute("style",
			"float:right;visibility:visible;");
}
function delHelpItem() {
	var helpboard = document.getElementById("HelpMarket");
	var helpItemCheckBoxes = document.getElementsByName("HelpChecks");
	var selectedCheckBoxes = new Array();
	var delSeq = new Array();
	for ( var i = 0; i < helpItemCheckBoxes.length; i++) {
		if (helpItemCheckBoxes[i].checked == true)
			selectedCheckBoxes.push(helpItemCheckBoxes[i]);
	}
	for(var j = 0 ;j < selectedCheckBoxes.length;j++){
		var seq = selectedCheckBoxes[j].parentNode.parentNode.id;
		delSeq.push(seq);
	}
	if(delSeq.length==0)return;
	
	// 记录对应的id然后发送到后台，如果删除成功，则执行下面代码
	jQuery.ajaxSettings.traditional = true;
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/admin/help-manager!delHelpBoard.action',
		dataType : 'json',
		data : {
			'helpBoardSeq' : delSeq,
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功
				for ( var i = 0; i < selectedCheckBoxes.length; i++) {
					HelpMarket.removeChild(selectedCheckBoxes[i].parentNode.parentNode);
				}
				var spanCounts = document.getElementsByName("spanCount");
				var i = 0
				for (i = 0; i < spanCounts.length; i++) {
					spanCounts[i].innerText = i + 1+'-';
				}		
				document.getElementsByName("countHelpBoard")[0].innerText=i;
			}
		},
		error : function(msg) {
			alert("连接异常");
		}
	});
}

function addHelpItem() {
	var helpboard = document.getElementById("HelpMarket");
	var firstHelpItem = (document.getElementsByName("helpItem"))[0];
	var newChild = document.createElement("div");
	newChild.innerHTML = "<div id='helpTitle' class='panel-heading' style='padding:10px;'>"
			+ "<input type='text' class='form-control' placeholder='请输入标题'/>"
			+ "</div>"
			+ "<div id='helpContent' class='panel-body'>"
			+ "<textarea class='form-control' style='resize:none;' rows='5' placeholder='请输入内容'></textarea>"
			+ "<div style='float:right;margin-top:5px;'>"
			+ "<button type='button' class='btn btn-default operateButton' style='margin-right:5px;'>保存</button>"
			+ "<button type='button' class='btn btn-default operateButton'>取消</button>"
			+ "</div>" + "</div>";
	newChild.setAttribute("name", "enterBoard");
	newChild.setAttribute("class", "panel panel-default");
	helpboard.insertBefore(newChild, firstHelpItem);

	document.getElementById("Whole").onmousedown = function(event) {
		var nowObject = event.target;
		var enterBoard = (document.getElementsByName("enterBoard"))[0];
		if (enterBoard == undefined)
			return;
		var helpTextarea = (enterBoard.getElementsByTagName("textarea"))[0];
		var input = (enterBoard.getElementsByTagName("input"))[0];
		var buttons = enterBoard.getElementsByTagName("button");
		var helpTitle = document.getElementById("helpTitle");
		var helpContent = document.getElementById("helpContent");
		if (nowObject == helpTextarea || nowObject == input
				|| nowObject == helpTitle || nowObject == helpContent) {

		} else if (nowObject == buttons[0]) {// 保存操作
			if (input.value == "" || helpTextarea.value == "") {
				if (input.value == "")
					input.setAttribute("placeholder", "标题不能为空");
				if (helpTextarea.value == "")
					helpTextarea.setAttribute("placeholder", "标题不能为空");
			} else {
				$("#Whole").unbind();
				submitUrl(enterBoard);
			}
		} else {// 取消或点击其他地方
			var helpboard = document.getElementById("HelpMarket");
			helpboard.removeChild(enterBoard);
			$("#Whole").unbind();
		}
	}

}
function submitUrl(which) {
	var helpTitle = (which.getElementsByTagName("input"))[0];
	var helpContent = (which.getElementsByTagName("textarea"))[0];
	// 先把数据发向后台，获取id
		$.ajax({
				// async:false,
				type : 'post',
				url : 'http://localhost:8080/ovs/admin/help-manager!addHelpBoard.action',
				dataType : 'json',
				data : {
					'helpTitle' : helpTitle.value,
					'helpContent' : helpContent.value
				},
				success : function(msg) {
					alert(msg.message);
					if (msg.status == 1) {
						// 提交成功
						saveEnterHelpItem(msg.data, helpTitle.value,
								helpContent.value,which);
					}
				},
				error : function(msg) {
					alert("连接异常");
				}
			});
}

function saveEnterHelpItem(seq, title, content,which) {
	var helpboard = document.getElementById("HelpMarket");
	var newChild = document.createElement("div");
	newChild.innerHTML = "<div class='checkbox' style='float:left'>"
			+ "<input type='checkbox' name='HelpChecks'>" + "</div>"
			+ "<div class='panel-heading' style='padding:0px 0px;'>" + "<h4>"
			+ "<span name='spanCount'></span>" + title + "</h4>" + "</div>"
			+ "<div class='panel-body'>" + content + "</div>";
	newChild.id = seq;
	newChild.setAttribute("name", "helpItem");
	newChild.setAttribute("class", "panel panel-default");
	helpboard.replaceChild(newChild, which);
	// 排序
	var spanCounts = document.getElementsByName("spanCount");
	var i = 0;
	for (i = 0; i < spanCounts.length; i++) {
		spanCounts[i].innerText = i + 1+'-';
	}
	document.getElementsByName("countHelpBoard")[0].innerText=i;
}




function creatInputText(which) {
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
				confirmMess(which,textArea);
			}
		} else {// 取消或者点击页面其他
			$("#Whole").unbind();
			cencleMess();
		}

	}
	return InputText;
}
function confirmMess(which,textArea) {
	// 向后台发送该数据，成功则显示，否则说失败
	
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/admin/help-manager!replyHelp.action',
		dataType : 'json',
		data : {
			'replySeq' : which.id,
			'replyContent':textArea.value
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功
				// 成功，则执行下面操作
				var parent = which.parentNode;
				var lastParent = parent.parentNode;
				lastParent.removeChild(parent);
			}
		},
		error : function(msg) {
			alert("连接异常");
		}
	});
	
}
function cencleMess() {
	var inputText = (document.getElementsByName("InputText"))[0];
	var item = inputText.parentNode;
	item.removeChild(inputText);
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
		messageItem.insertBefore(creatInputText(item), children[j + 1]);
	} else if (j == children.length) {
		messageItem.appendChild(creatInputText(item));
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
	$.ajax({
		// async:false,
		type : 'get',
		url : 'http://localhost:8080/ovs/admin/help-manager!delQuestion.action',
		dataType : 'json',
		data : {'questionSeq':item.id},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功
				tab_panel.removeChild(messageItem);
			}
		},
		error : function(msg) {
			alert("连接异常");
		}
	});	
}