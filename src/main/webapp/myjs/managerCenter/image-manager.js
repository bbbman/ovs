var checkboxes = document.getElementsByName("imggiud");
function selectAll() {

	for ( var i = 0; i < checkboxes.length; i++)
		checkboxes[i].checked = true;

}

function cancleSelected() {

	for ( var i = 0; i < checkboxes.length; i++)
		checkboxes[i].checked = false;
}

function delSelectedImgS() {
	
	var checkedBox = new Array();
	for ( var i = 0; i < checkboxes.length; i++)
		if (checkboxes[i].checked)
			checkedBox.push(checkboxes[i].parentNode.parentNode.parentNode.parentNode.getAttribute("value"));
	if (checkedBox.length == 0)
		return;
	// 向后台发送请求删除操作，然后执行下面代码
	jQuery.ajaxSettings.traditional = true;
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/user/managercenter/image-manager!deleteMoreImage.action',
		dataType : 'json',
		data : {
			'delImageIds' :checkedBox
		},
		success : function(msg) {
			if (msg.status == 1) {
				// 提交成功
				alert(msg.message);
				
				for ( var i = 0; i < checkboxes.length; i++)
					document.getElementById("ImgMenu").removeChild(checkboxes[i].parentNode.parentNode.parentNode.parentNode);								
			} else {
				alert(msg.message);
			}
			console.log(msg);
		},
		error : function(msg) {
			alert("连接异常");
		}
	});						
}
function delOneImg(which) {
	var delImageId = which.getAttribute("value");
	// 向后台发送请求删除操作，然后执行下面代码
	$.ajax({
		// async:false,
		type : 'post',
		url : 'http://localhost:8080/ovs/user/managercenter/image-manager!deleteOneImage.action',
		dataType : 'json',
		data : {
			'delImageId' : delImageId
		},
		success : function(msg) {
			if (msg.status == 1) {
				// 提交成功
				alert(msg.message);
				document.getElementById("ImgMenu").removeChild(which);				
			} else {
				alert(msg.message);
			}
			console.log(msg);
		},
		error : function(msg) {
			alert(msg.message)
			//alert("连接异常");
		}
	});
}