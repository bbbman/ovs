package cn.scau.lcj.action.user;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.UUIDUtil;
import cn.scau.lcj.entity.HelpCenter;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.HelpCenterService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class HelpCenterAction extends ExtendSupport{
	
	@Autowired
	private HelpCenterService helpCenterService;
	private String content;
	
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String execute(){
		Log.log("帮助中心开始");
		request.setAttribute("pageActive", "helpCenter");		
		User user = (User) session.get(Constant.USER_SESSION);		
		List<HelpCenter> list = helpCenterService.findAll();
		if(list == null || list.size()==0){
			request.setAttribute("data", null);
		}else{
			request.setAttribute("data", list);
		}		
		Log.log("帮助中心结束");
		return SUCCESS;				
	}
	public void insertHelpMessage(){
		Log.log("请求帮助开始");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(content==null || StringUtil.isEmpty(content)){
			setReturnJson(json,2,1,"输入不能为空!","");
			responseJson(json);
			return;
		}		
		HelpCenter helpCenter = new HelpCenter();
		helpCenter.setBuildTime(new Timestamp(System.currentTimeMillis()));
		helpCenter.setContent(content);
		helpCenter.setUserId(user.getUserId());
		helpCenter.setUsername(user.getUsername());
		helpCenterService.save(helpCenter);
		json.put("username", user.getUsername());
		json.put("buildTime", helpCenter.getBuildTimeString());
		setReturnJson(json,1,0,"帮助项成功","");
		responseJson(json);
		Log.log("请求帮助开始");
		return;
	}
}
