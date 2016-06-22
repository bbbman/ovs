package cn.scau.lcj.action;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.Md5Util;
import cn.scau.common.SessionMap;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class LoginAction extends ExtendSupport {

	private String email;

	private String password;

	private JSONObject biz_content = null;

	@Autowired
	private UserService userService;
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String execute() {
		request.setAttribute("loginType", "user");
		return SUCCESS;
	}

	public void doLogin() {
		biz_content = new JSONObject();
		if(StringUtil.isEmpty(email)){
			setReturnJson(biz_content,2,1,"用户名不能为空！","");
			responseJson(biz_content);
			return;
		}
		if(StringUtil.isEmpty(password)){
			setReturnJson(biz_content,2,2,"密码不能为空！","");
			responseJson(biz_content);
			return;
		}
		//登录验证
		List<User> userList = userService.doLogin(email,password);
		if(userList==null||userList.isEmpty()){
			setReturnJson(biz_content,2,3,"用户名或密码错误！","");
			responseJson(biz_content);
			return;
		}
		User user = userList.get(0);
		//登录成功
		session.put(Constant.USER_SESSION, user);
		
		setReturnJson(biz_content,1,0,"登陆成功","");
		//responseJson(biz_content);
		//页面跳转
		if(user.getUserType()==0){
			//普通用户
			biz_content.put("url", "user/index.action");
		}else if(user.getUserType()==1){
			//管理员
			biz_content.put("url", "admin/people-manager.action");			
		}else{
			//未知身份
		}
		responseJson(biz_content);
		return;
	}
	
	public String loginOut(){
		Log.log("登出开始:");
		session.remove(Constant.USER_SESSION);		
		Log.log("登出结束");
		return "cc";
	}
}
