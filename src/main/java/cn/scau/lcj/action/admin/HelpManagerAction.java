package cn.scau.lcj.action.admin;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.StringUtil;
import cn.scau.lcj.utils.struts.ExtendSupport;

import cn.scau.lcj.entity.HelpBoard;
import cn.scau.lcj.entity.HelpCenter;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.service.HelpBoardService;
import cn.scau.lcj.service.HelpCenterService;

public class HelpManagerAction extends ExtendSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1816053372941813630L;

	private String helpTitle;

	private String helpContent;

	private Integer helpBoardSeq[];

	private Integer replySeq;

	private String replyContent;

	private Integer questionSeq;

	@Autowired
	private HelpCenterService helpCenterService;

	@Autowired
	private HelpBoardService helpBoardService;

	public String execute() {
		Log.log("帮助板块开始:");
		request.setAttribute("pageActive", "help");
		Integer countHelpCenter;
		Integer countHelpBoard;
		List<HelpCenter> helpCenterList = helpCenterService.findUnRead();
		List<HelpBoard> HelpBoardList = helpBoardService.findAll();

		countHelpCenter = (helpCenterList == null || helpCenterList.isEmpty()) ? 0
				: helpCenterList.size();
		countHelpBoard = (HelpBoardList == null || HelpBoardList.isEmpty()) ? 0
				: HelpBoardList.size();

		request.setAttribute("countHelpCenter", countHelpCenter);
		request.setAttribute("countHelpBoard", countHelpBoard);
		request.setAttribute("helpCenterList", helpCenterList);
		request.setAttribute("HelpBoardList", HelpBoardList);
		Log.log("帮助板块结束");
		return SUCCESS;
	}

	public void addHelpBoard() {
		Log.log("添加帮助项开始:");
		JSONObject json = new JSONObject();
		if (StringUtil.isEmpty(helpTitle)) {
			setReturnJson(json, 2, 1, "标题不能为空", "");
			responseJson(json);
			Log.log("添加帮助项结束");
			return;
		}
		if (StringUtil.isEmpty(helpContent)) {
			setReturnJson(json, 2, 2, "内容不能为空", "");
			responseJson(json);
			Log.log("添加帮助项结束");
			return;
		}
		User user = (User) session.get(Constant.USER_SESSION);
		HelpBoard helpBoard = new HelpBoard();
		helpBoard.setUserId(user.getUserId());
		helpBoard.setHelpTitle(helpTitle);
		helpBoard.setHelpContent(helpContent);
		helpBoard.setHelpBuildTime(new Timestamp(System.currentTimeMillis()));
		Integer helpBoardSeq = helpBoardService.saveHelpBoard(helpBoard);
		setReturnJson(json, 1, 0, "添加成功", helpBoardSeq);
		responseJson(json);
		Log.log("添加帮助项结束");
		return;
	}

	public void delHelpBoard() {
		Log.log("删除帮助项开始:");
		JSONObject json = new JSONObject();
		if (helpBoardSeq.length == 0) {
			setReturnJson(json, 2, 1, "该帮助项不存在", "");
			responseJson(json);
			Log.log("删除帮助项结束");
			return;
		}
		for (int i = 0; i < helpBoardSeq.length; i++) {
			helpBoardService.deleteBySeq(helpBoardSeq[i]);
		}
		setReturnJson(json, 1, 0, "删除帮助项成功", "");
		responseJson(json);
		Log.log("删除帮助项结束");
	}

	public void replyHelp() {
		Log.log("回复提问开始:");
		JSONObject json = new JSONObject();
		if (StringUtil.isEmpty(replyContent)) {
			setReturnJson(json, 2, 1, "回复内容不能空", "");
			responseJson(json);
			Log.log("回复提问结束");
		}
		HelpCenter helpCenter = helpCenterService.getBySeq(replySeq);
		if (helpCenter == null) {
			setReturnJson(json, 2, 2, "该项不存在", "");
			responseJson(json);
			Log.log("回复提问结束");
		}

		User user = (User) session.get(Constant.USER_SESSION);
		HelpCenter reply = new HelpCenter();
		reply.setUserId(user.getUserId());
		reply.setContent(replyContent);
		reply.setParentId(helpCenter.getHelpId());
		reply.setBuildTime(new Timestamp(System.currentTimeMillis()));
		reply.setHelpDealType(3);// 管理员所发
		helpCenterService.save(reply);
		helpCenter.setHelpDealType(1);// 已回复
		helpCenterService.updateHelpCenterBy(helpCenter);
		setReturnJson(json, 1, 0, "回复成功", "");
		responseJson(json);
		Log.log("回复提问结束:");
	}

	public void delQuestion() {
		Log.log("删除提问开始");
		JSONObject json = new JSONObject();
		if (questionSeq == null) {
			setReturnJson(json, 1, 0, "该项不存在", "");
			responseJson(json);
			Log.log("删除提问结束");
			return;
		}
		HelpCenter helpCenter =  helpCenterService.getBySeq(questionSeq);
		helpCenter.setHelpDealType(2);//删除即使忽略
		helpCenterService.deleteBySeq(questionSeq);
		setReturnJson(json, 1, 0, "删除成功", "");
		responseJson(json);
		Log.log("删除提问结束");
		return;
	}

	public String getHelpTitle() {
		return helpTitle;
	}

	public void setHelpTitle(String helpTitle) {
		this.helpTitle = helpTitle;
	}

	public String getHelpContent() {
		return helpContent;
	}

	public void setHelpContent(String helpContent) {
		this.helpContent = helpContent;
	}

	public Integer[] getHelpBoardSeq() {
		return helpBoardSeq;
	}

	public void setHelpBoardSeq(Integer[] helpBoardSeq) {
		this.helpBoardSeq = helpBoardSeq;
	}

	public Integer getReplySeq() {
		return replySeq;
	}

	public void setReplySeq(Integer replySeq) {
		this.replySeq = replySeq;
	}

	public String getReplyContent() {
		return replyContent;
	}

	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}

	public Integer getQuestionSeq() {
		return questionSeq;
	}

	public void setQuestionSeq(Integer questionSeq) {
		this.questionSeq = questionSeq;
	}

}
