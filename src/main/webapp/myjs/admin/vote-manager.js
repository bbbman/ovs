function banOrStart(which){
	var tr = which.parentNode.parentNode;
	
	var tds = tr.getElementsByTagName("td");
	var Status;
	for(var i=0;i<tds.length;i++){
		if(tds[i].getAttribute("name")=='status'){
			Status = tds[i];
			break;
		}
	}
	if(which.innerText=='停止'){
		which.innerText ='重启';
		Status.innerText ='停止'			
	}else{
		which.innerText ='停止';
		Status.innerText ='	投票进行中';
	}
}
function changeDisable(which){
	var tr = which.parentNode.parentNode;
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/admin/vote-manager!changeDisable.action',
		dataType : 'json',
		data : {
			'pageId' : tr.id,
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功
				banOrStart(which);
			}
		},
		error : function(msg) {
			alert("连接异常");
		}
	});
}