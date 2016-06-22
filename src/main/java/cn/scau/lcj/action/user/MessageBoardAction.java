package cn.scau.lcj.action.user;



import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.DateUtil;
import cn.scau.common.ObjectUtil;
import cn.scau.common.UUIDUtil;
import cn.scau.lcj.entity.MessageBoard;
import cn.scau.lcj.entity.User;


import cn.scau.lcj.service.MessageBoardService;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class MessageBoardAction extends ExtendSupport{
	
	private Integer parentSeq;
	private Integer receiveSeq;
	private String content;
	private Integer itemPosition;
	private MessageBoard messageBoard;
	
	@Autowired
	private MessageBoardService messageBoardService;
	
	@Autowired
	private UserService userService;
		
	public String execute(){
		Log.log("获取话题面板开始:");
		User user = (User) session.get(Constant.USER_SESSION);				
		request.setAttribute("pageActive", "messageBoard");
		//List<MessageBoard> list = messageBoardService.findAll();
		List<List<MessageBoard>> children = new ArrayList<List<MessageBoard>>();
		List<MessageBoard> parentList = messageBoardService.findParents();
		if(!ObjectUtil.isEmptyList(parentList)){
			for(int i = 0; i<parentList.size();i++){
				List<MessageBoard> child  = messageBoardService.findChildren(parentList.get(i).getMessageId());
				if(ObjectUtil.isEmptyList(child))
					child = new ArrayList<MessageBoard>();
				child.add(0, parentList.get(i));
				children.add(child);
			}
		}
		request.setAttribute("mBList", children);
		request.setAttribute("userId", user.getUserId());
		Log.log("获取话题面板结束:");
		return SUCCESS;				
	}
	
	public void insertMessageBoard(){
		Log.log("创建话题开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(StringUtil.isEmpty(content)){
			setReturnJson(json,2,1,"输入不能为空!","");
			responseJson(json);
			Log.log("创建话题结束");
			return;
		}
		messageBoard = new MessageBoard();
		messageBoard.setBuildTime(new Timestamp(System.currentTimeMillis()));
		messageBoard.setContent(content);
		messageBoard.setUserId(user.getUserId());
		messageBoard.setUsername(null);//username会变，不能存
		Integer mBSeq = messageBoardService.save(messageBoard);
		json.put("mBSeq", mBSeq);
		json.put("username", user.getUsername());
		json.put("buildTime", messageBoard.getBuildTimeString());
		setReturnJson(json,1,0,"留言成功","");
		responseJson(json);
		Log.log("创建话题结束");
		return;
	}
	
	public void comment(){
		Log.log("记录评论开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if(StringUtil.isEmpty(content)){
			setReturnJson(json,2,1,"输入不能为空!","");
			responseJson(json);
			Log.log("记录评论结束！");
			return;
		}
		MessageBoard parentMB = messageBoardService.selectByPrimaryKey(parentSeq);
		if(parentMB==null){
			setReturnJson(json,2,2,"该话题已被删除，不能评论","");
			responseJson(json);
			Log.log("记录评论结束！");
			return;
		}
		MessageBoard receiveMB = messageBoardService.selectByPrimaryKey(receiveSeq);
		if(receiveMB==null){
			setReturnJson(json,2,3,"该评论已被删除，不能评论","");
			responseJson(json);
			Log.log("记录评论结束:");
			return;
		}
		MessageBoard commentMB = new MessageBoard();
		commentMB.setContent(content);
		commentMB.setBuildTime(DateUtil.getCurTimestamp());
		commentMB.setParentId(parentMB.getMessageId());
		commentMB.setReceiveId(receiveMB.getMessageId());
		commentMB.setPosition(receiveMB.getPosition()+1);
		commentMB.setUserId(user.getUserId());
		Integer mbSeq = messageBoardService.save(commentMB);
		User receiver =  userService.selectByPrimaryKey(receiveMB.getUserId());
		setReturnJson(json,1,0,"评论成功","");
		json.put("mBSeq", mbSeq);
		json.put("username", user.getUsername());
		json.put("buildTime", commentMB.getBuildTimeString());
		json.put("receivername", receiver.getUsername());
		responseJson(json);
		Log.log("记录评论结束:");
		return;						
	}

	public Integer getParentSeq() {
		return parentSeq;
	}

	public void setParentSeq(Integer parentSeq) {
		this.parentSeq = parentSeq;
	}

	public Integer getReceiveSeq() {
		return receiveSeq;
	}

	public void setReceiveSeq(Integer receiveSeq) {
		this.receiveSeq = receiveSeq;
	}	

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getItemPosition() {
		return itemPosition;
	}

	public void setItemPosition(Integer itemPosition) {
		this.itemPosition = itemPosition;
	}
	
	
	
	
	
}
