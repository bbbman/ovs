function changePassword(){
	var email = document.getElementById("email").value;
	var nickName = document.getElementById("nickName").value;
	var oldPassword = document.getElementById("oldPassword").value;
	var newPassword = document.getElementById("newPassword").value;
	var newPassword2 = document.getElementById("newPassword2").value;
	//先js校验
	//传向后台
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/user/managercenter/profile!changePassword.action',
		dataType:'json',
		data:{'email':email,'nickName':nickName,'oldPassword':oldPassword,'newPassword':newPassword,'newPassword2':newPassword2},
		success:function(msg){
			if(msg.status==1){
				//提交成功
				alert("密码修改成功");
				console.log(msg);
			}else{
				alert(msg.message);
			}
			console.log(msg);			
		},
		error:function(msg){
			alert(msg.message);
		}
	});
}