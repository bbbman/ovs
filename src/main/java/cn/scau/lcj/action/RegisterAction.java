package cn.scau.lcj.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class RegisterAction extends ExtendSupport {
	
	private String username;
	
	private String password;
	
	private String confPassword;
	
	private String email;
	
	private JSONObject biz_content = null;
	
	@Autowired
	private UserService userService;
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
			
	public String getConfPassword() {
		return confPassword;
	}

	public void setConfPassword(String confPassword) {
		this.confPassword = confPassword;
	}

	@Override
	public String execute(){
		Log.log("注册接口--------------------------");
		return SUCCESS;
	}
	//注册请求
	public void doRegister(){
		biz_content = new JSONObject();
		if(StringUtil.isEmpty(email)){
			setReturnJson(biz_content,2,1,"email不能为空","");
			responseJson(biz_content);
			return;
		}
		if(userService.isContain("email", email)){			
			setReturnJson(biz_content,2,2,"该邮箱已被注册","");
			responseJson(biz_content);
			return;
		}
		
		if(StringUtil.isEmpty(username)){
			setReturnJson(biz_content,2,3,"用户名不能为空","");
			responseJson(biz_content);
			return;
		}
		if(userService.isContain("username",username )){			
			setReturnJson(biz_content,2,4,"该用户名已被注册","");
			responseJson(biz_content);
			return;
		}
	
		if(StringUtil.isEmpty(password)){
			setReturnJson(biz_content,2,5,"密码不能为空","");
			responseJson(biz_content);			
			return;
		}
		if(StringUtil.isEmpty(confPassword)){
			setReturnJson(biz_content,2,6,"确认密码不能为空","");
			responseJson(biz_content);			
			return;
		}
		if(!password.equals(confPassword)){
			setReturnJson(biz_content,2,7,"密码与确认密码不相同","");
			responseJson(biz_content);			
			return;
		}		
		//正式注册
		User user = new User(username,password,email,0,0,0);//0代表普通用户
		Integer userId = userService.insertUser(user);
		if(userId!=null){
			user.setUserId(userId);
			//把该对象放入session中
			session.put(Constant.USER_SESSION, user);
			setReturnJson(biz_content,1,0,"注册成功","");	
			//跳转到它想登陆的页面
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
		}else{
			setReturnJson(biz_content,3,0,"系统异常，注册失败","");
			responseJson(biz_content);
		}		
		return ;
	}	
}
