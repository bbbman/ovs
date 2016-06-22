function deleteVoteItem(which) {
	var myTbody = document.getElementsByTagName("tbody");
	var tr = which.parentNode.parentNode;
	//先向后台提交请求，然后再进行下面操作
	myTbody[0].removeChild(tr);
}
function clearZero(which) {
	var tr = which.parentNode.parentNode;
	var tds = tr.getElementsByTagName("td");
	var td_num = tds[1];
	//先向后台提交请求，然后再进行下面操作
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/user/managercenter/vote-manager!clearZero.action',
		dataType : 'json',
		data : {
			'pageId' :tr.id
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功	
				td_num.innerText = 0;
			}							
		},
		error : function(msg) {
			alert("连接异常");
		}
	});	

}
function delPage(which){
	var tr = which.parentNode.parentNode;	
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/user/managercenter/vote-manager!delPage.action',
		dataType : 'json',
		data : {
			'pageId' :tr.id
		},
		success : function(msg) {
			alert(msg.message);
			if (msg.status == 1) {
				// 提交成功	
				deleteVoteItem(which);
			}							
		},
		error : function(msg) {
			alert("连接异常");
		}
	});
}