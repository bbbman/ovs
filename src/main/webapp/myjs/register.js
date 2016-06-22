function submitRegister(){
	var email = document.getElementsByName("email")[0].value;
	var username = document.getElementsByName("username")[0].value;
	var password = document.getElementsByName("password")[0].value;
	var confPassword = document.getElementsByName("confPassword")[0].value;
    if(email==""){
		
	}
	if(username==""){
		
	}
	if(password==""){
		
	}
	if(password != confPassword){
		
	}
	
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/register!doRegister.action',
		dataType:'json',
		//data:{'requestParam':formData},
		data:{'email':email,'username':username,'confPassword':confPassword,'password':password},
		success:function(msg){
			console.log(msg);
			if(msg.status==1){
				//提交成功
				window.location.href="user/index.action";
			}else{
				alert(msg.message);
			}		
		},
		error:function(msg){
			console.log(msg);
			alert("error"+msg);
		}
	});
}