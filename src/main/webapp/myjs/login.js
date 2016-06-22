function submitLogin(){

	var email = document.getElementsByName("email")[0].value;
	var password = document.getElementsByName("password")[0].value;
    if(email==""){
		
	}
	if(password==""){
		
	}
	$.ajax({
		//async:false,
		type:'post',
		url:'http://localhost:8080/ovs/login!doLogin.action',
		dataType:'json',
		//data:{'requestParam':formData},
		data:{'email':email,'password':password},
		success:function(msg){
			console.log(msg);
			if(msg.status==1){
				window.location.href=msg.url;
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