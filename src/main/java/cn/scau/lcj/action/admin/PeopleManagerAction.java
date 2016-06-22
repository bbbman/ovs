package cn.scau.lcj.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class PeopleManagerAction extends ExtendSupport {

	private String username;

	private Integer userId;

	@Autowired
	private UserService userService;

	public String execute() {
		Log.log("人员管理开始:");
		List<User> userList = userService.selectAll();
		request.setAttribute("userList", userList);
		request.setAttribute("pageActive", "people");
		Log.log("人员管理结束:");
		return SUCCESS;
	}

	public String searchdUser() {
		Log.log("查找用户开始:");
		JSONObject json = new JSONObject();
		if (StringUtil.isEmpty(username))
			username = "";
		List<User> userList = userService.selectByUsername(username);
		request.setAttribute("condition", username);
		request.setAttribute("userList", userList);
		request.setAttribute("pageActive", "people");
		Log.log("查找用户结束:");
		return SUCCESS;
	}

	public void banUser() {
		Log.log("禁用用户开始:");
		JSONObject json = new JSONObject();
		User user = userService.selectByPrimaryKey(userId);
		if (user == null || user.getIsDelete() == 1) {
			setReturnJson(json, 2, 1, "用户不存在", "");
			responseJson(json);
			Log.log("禁用用户结束:");
			return;
		}
		if (user.getUserType() == 1 || user.getEnable() == 1) {
			// 特殊用户或被删除的用户
			setReturnJson(json, 2, 2, "不能禁用该用户", "");
			responseJson(json);
			Log.log("禁用用户结束:");
			return;
		}
		user.setEnable(1);
		userService.updateUserByObject(user);
		setReturnJson(json, 1, 0, "禁用成功", "");
		responseJson(json);
		Log.log("禁用用户结束:");
		return;
	}

	public void openUser() {
		Log.log("启用用户开始:");
		JSONObject json = new JSONObject();
		User user = userService.selectByPrimaryKey(userId);
		if (user == null || user.getIsDelete() == 1) {
			setReturnJson(json, 2, 1, "用户不存在", "");
			responseJson(json);
			Log.log("启用用户结束:");
			return;
		}
		if (user.getUserType() == 1 || user.getEnable() == 0) {
			// 特殊用户或被删除的用户
			setReturnJson(json, 2, 2, "不能启用该用户", "");
			responseJson(json);
			Log.log("启用用户结束:");
			return;
		}
		user.setEnable(0);
		userService.updateUserByObject(user);
		setReturnJson(json, 1, 0, "启用成功", "");
		responseJson(json);
		Log.log("启用用户结束:");
		return;
	}

	public void delUser() {
		Log.log("删除用户开始:");
		JSONObject json = new JSONObject();
		User user = userService.selectByPrimaryKey(userId);
		if (user == null || user.getIsDelete() == 1) {
			setReturnJson(json, 2, 1, "用户不存在", "");
			responseJson(json);
			Log.log("删除用户结束:");
			return;
		}
		if (user.getUserType() == 1 || user.getEnable() == 0) {
			// 特殊用户或被删除的用户
			setReturnJson(json, 2, 2, "不能删除该用户", "");
			responseJson(json);
			Log.log("删除用户结束:");
			return;
		}
		userService.deleteUserByPrimaryKey(user.getUserId());
		setReturnJson(json, 1, 0, "删除成功", "");
		responseJson(json);
		Log.log("删除用户结束:");
		return;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

}
