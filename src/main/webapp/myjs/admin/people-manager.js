function searchUser() {
	var username = document.getElementById("searchUser").value;
	window.location.href = "http://localhost:8080/ovs/admin/people-manager!searchdUser.action?username="
			+ username + "";
}
function changeUser(which){
	var tr = which.parentNode.parentNode;
	var value = tr.getAttribute("id");
	var choseType = which.value;
	var askUrl= 'http://localhost:8080/ovs/admin/people-manager!openUser.action';
	if(choseType=='1')
		askUrl = 'http://localhost:8080/ovs/admin/people-manager!banUser.action';
	
	$.ajax({
		// async:false,
		type : 'post',
		url : askUrl,
		dataType : 'json',
		data : {
			'userId' : value
		},
		success : function(msg) {
			alert(msg.message);
			if(msg.status==1){
				if(choseType==0){
					which.innerText = "封号";
					which.value='1';
					tr.children[3].innerText="启用中...";
				}else{
					which.innerText = "启用";
					which.value='0';
					tr.children[3].innerText="封号中...";
				}
			}
		},
		error : function(msg) {
			alert(msg);
		}
	});
}
function delUser(which){
	var tr = which.parentNode.parentNode;
	var value = tr.getAttribute("id");
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/admin/people-manager!delUser.action',
		dataType : 'json',
		data : {
			'userId' : value
		},
		success : function(msg) {
			alert(msg.message);
			if(msg.status==1)
				removeTr(tr);			
		},
		error : function(msg) {
			alert(msg);
		}
	});
}
function removeTr(which){
	var parent = which.parentNode;
	parent.removeChild(which);
	var trs = parent.children;
	for(var i=1;i<trs.length;i++){
		trs[i].children[0].innerText = i;
	}	
}
