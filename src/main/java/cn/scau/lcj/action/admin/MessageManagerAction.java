package cn.scau.lcj.action.admin;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.ObjectUtil;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;
import cn.scau.lcj.entity.MessageBoard;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.MessageBoardService;

public class MessageManagerAction extends ExtendSupport {
	
	private Integer messageType;
	
	private Integer messageId;

	private Integer unReadCount=0;

	private Integer ignoredCount=0;

	private Integer repliedCount=0;

	private Integer dataCount=0;

	@Autowired
	private MessageBoardService messageBoardService;

	public void count() {
		User user = (User) session.get(Constant.USER_SESSION);
		if(user==null)return;
		unReadCount = messageBoardService.countByDealType(0, user.getUserId());
		ignoredCount = messageBoardService.countByDealType(1, user.getUserId());
		repliedCount = messageBoardService.countByDealType(2, user.getUserId());
		dataCount = messageBoardService.countAllMess(user.getUserId());
		request.setAttribute("unReadCount", unReadCount);
		request.setAttribute("ignoredCount", ignoredCount);
		request.setAttribute("repliedCount", repliedCount);
		request.setAttribute("dataCount", dataCount);
		request.setAttribute("pageActive", "message");
	}

	public String execute() {
		Log.log("留言管理开始:");
		count();
		request.setAttribute("executeFlag", true);
		Log.log("留言管理结束:");
		return SUCCESS;
	}
	
	public String showMess(){
		Log.log("获取留言板开始:");
		User user = (User) session.get(Constant.USER_SESSION);		
		List<MessageBoard> parentList = messageBoardService.selectByDealType(messageType, user.getUserId());
		List<List<MessageBoard>>  mBList = new ArrayList<List<MessageBoard>>();
		if(!ObjectUtil.isEmptyList(parentList)){
			for(int i = 0 ; i<parentList.size();i++){
				List<MessageBoard> child = messageBoardService.findChildren(parentList.get(i).getMessageId());
				if(ObjectUtil.isEmptyList(child))
					child = new ArrayList<MessageBoard>();
				child.add(0, parentList.get(i));
				mBList.add(child);				
			}
		}		
		switch(messageType){
		case 0 :request.setAttribute("unReadMess", true); break;
		case 1 :request.setAttribute("ignoredMess", true); break;
		case 2 :request.setAttribute("repliedMess", true); break;
		default:request.setAttribute("allMess", true); break;
		}
		count();
		request.setAttribute("mBList",mBList);
		Log.log("获取留言板结束:");
		return SUCCESS;
	}
	
	public void passExamine(){
		Log.log("审核留言板开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		MessageBoard messageBoard = messageBoardService.selectByPrimaryKey(messageId);
		if(user.getUserType()!=1){
			setReturnJson(json,2,1,"权限不够","");
			responseJson(json);
			Log.log("审核留言板结束:");
			return;
		}
		if(messageBoard ==  null){
			setReturnJson(json,2,2,"该留言已删除","");
			responseJson(json);
			Log.log("审核留言板结束:");
			return;
		}
		if(messageBoard.getDealType() != 2){//没审核通过的
			messageBoard.setDealType(2);
			messageBoardService.updateByObject(messageBoard);			
		}
		setReturnJson(json,1,0,"审核通过","");
		responseJson(json);
		Log.log("审核留言板结束:");
		return;		
	}
	//移到隐藏
	public void agnoredMess(){
		Log.log("屏蔽留言板开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		MessageBoard messageBoard = messageBoardService.selectByPrimaryKey(messageId);
		if(user.getUserType()!=1){
			setReturnJson(json,2,1,"权限不够","");
			responseJson(json);
			Log.log("屏蔽留言板结束:");
			return;
		}
		if(messageBoard ==  null){
			setReturnJson(json,2,2,"该留言已屏蔽","");
			responseJson(json);
			Log.log("屏蔽留言板结束:");
			return;
		}
		if(messageBoard.getDealType() != 1){//没屏蔽的
			messageBoard.setDealType(1);
			messageBoardService.updateByObject(messageBoard);			
		}
		setReturnJson(json,1,0,"屏蔽成功","");
		responseJson(json);
		Log.log("屏蔽留言板结束:");
		return;		
	}
	//操作回退，变成未处理
	public void moveUnRead(){
		Log.log("回退留言板开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		MessageBoard messageBoard = messageBoardService.selectByPrimaryKey(messageId);
		if(user.getUserType()!=1){
			setReturnJson(json,2,1,"权限不够","");
			responseJson(json);
			Log.log("回退留言板结束:");
			return;
		}
		if(messageBoard ==  null){
			setReturnJson(json,2,2,"该留言已删除","");
			responseJson(json);
			Log.log("回退留言板结束:");
			return;
		}
		if(messageBoard.getDealType() != 0){
			messageBoard.setDealType(0);
			messageBoardService.updateByObject(messageBoard);			
		}
		setReturnJson(json,1,0,"回退成功","");
		responseJson(json);
		Log.log("回退留言板结束:");
		return;		
	}
	
	public void delMess(){
		Log.log("删除留言板开始:");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		
		MessageBoard messageBoard = messageBoardService.selectByPrimaryKey(messageId);
		
		if(user.getUserType()!=1){
			setReturnJson(json,2,1,"权限不够","");
			responseJson(json);
			Log.log("删除留言板结束:");
			return;
		}				
		if(messageBoard ==  null){
			setReturnJson(json,1,0,"该留言已删除","");
			responseJson(json);
			Log.log("删除留言板结束:");
			return;
		}
		if(messageBoard.getDealType() != 1){
			setReturnJson(json,2,2,"非忽略留言不能删除","");
			responseJson(json);
			Log.log("删除留言板结束:");
			return;				
		}		
		messageBoardService.deleteChildByParentSeq(messageBoard.getMessageId());
		messageBoardService.deleteMessage(messageBoard.getMessageId());
		setReturnJson(json,1,0,"该留言已删除","");
		responseJson(json);
		Log.log("删除留言板结束:");
		return;		
	}
	
	public void clearTeam(){
		
	}


	public Integer getMessageType() {
		return messageType;
	}

	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}

	public Integer getMessageId() {
		return messageId;
	}

	public void setMessageId(Integer messageId) {
		this.messageId = messageId;
	}		
}
