package cn.scau.lcj.action.user.managercenter;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class ProfileAction extends ExtendSupport{
	
	private String email;
	private String nickName;
	private String oldPassword;
	private String newPassword;
	private String newPassword2;
	
	@Autowired
	private UserService userService;
	
	
	public void setEmail(String email) {
		this.email = email;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public void setNewPassword2(String newPassword2) {
		this.newPassword2 = newPassword2;
	}
	
	public String execute(){
		Log.log("个人资料网页开始======================");
		Log.log("个人资料网页开始======================");
		return SUCCESS;				
	}
	public void changePassword(){
		Log.log("修改密码网页开始======================");
		JSONObject returnJson = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(user == null){
			setReturnJson(returnJson,2,1,"未登陆","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		if(!StringUtil.isEmail(email)){
			setReturnJson(returnJson,2,1,"邮箱格式错误","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		//检查邮箱是否已经存在
		if(userService.isContain("email", email)){
			setReturnJson(returnJson,2,1,"email已经存在","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}		
		if(!StringUtil.isUsername(nickName)){
			setReturnJson(returnJson,2,1,"用户名格式错误","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		//检查用户名是否存在
		if(userService.isContain("username", nickName)){
			setReturnJson(returnJson,2,1,"用户名已存在","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}		
		if(!StringUtil.isPassword(oldPassword)){
			setReturnJson(returnJson,2,1,"密码格式错误","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		//验证密码是否正确
		if(!user.getPassword().equals(oldPassword)){
			setReturnJson(returnJson,2,1,"原密码错误","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		
		if(!StringUtil.isPassword(newPassword)){
			setReturnJson(returnJson,2,1,"新密码格式错误","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		
		if(!newPassword.equals(newPassword2)){
			setReturnJson(returnJson,2,1,"新密码与确认密码不同","");
			Log.log("修改密码网页结束======================");
			responseJson(returnJson);
			return;
		}
		user.setEmail(email);
		user.setPassword(newPassword);
		user.setUsername(nickName);
		userService.updateUserByObject(user);
		session.put(Constant.USER_SESSION, user);
		
		setReturnJson(returnJson,1,0,"修改个人资料成功","");
		Log.log("修改密码网页结束======================");
		responseJson(returnJson);
	}
}
