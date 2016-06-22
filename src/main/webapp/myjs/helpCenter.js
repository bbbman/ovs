function addMessage(username,buildTime,content)
{   
	var helpBoard = document.getElementById("helpBoard");
	var child = document.getElementsByName("helpItem");

	var mydiv = document.createElement("div");
	mydiv.setAttribute("name","helpItem");
	mydiv.setAttribute("class","panel panel-default");
	mydiv.innerHTML = "<div class='panel-heading' style='padding:0px 0px;'>"+username+" &nbsp; "+buildTime+"</div><div class='panel-body'>"+content+"</div>"	
	if(child.length>0){
		helpBoard.insertBefore(mydiv,child[0]);
	}else{
		helpBoard.appendChild(mydiv);
	}	
}
function submitUrl(){
	var myMessageValue = document.getElementById("messageValue");
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/help-center!insertHelpMessage.action',
		dataType:'json',
		data:{'content':myMessageValue.value},
		success:function(msg){
			if(msg.status==1){
				//提交成功
				addMessage(msg.username,msg.buildTime,myMessageValue.value);
				myMessageValue.value = "";
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(){
			console.log("=========ddd");
		}
	});
}