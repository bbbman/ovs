package cn.scau.lcj.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.ObjectUtil;
import cn.scau.common.StringUtil;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;
import cn.scau.lcj.service.UserService;

public class VoteManagerAction extends ExtendSupport {

	private Integer pageId;

	private String searchContent;

	@Autowired
	private PageService pageService;

	@Autowired
	private UserService userService;

	public String execute() {
		Log.log("系统投票管理开始:");
		List<Page> list = pageService.findAll();
		dealPageList(list);
		request.setAttribute("pageList", list);
		request.setAttribute("pageActive", "vote");
		Log.log("系统投票管理结束:");
		return SUCCESS;
	}

	public void changeDisable() {
		Log.log("改变网页状态开始");
		JSONObject json = new JSONObject();
		if (pageId == null) {
			setReturnJson(json, 2, 1, "该网页不存在", "");
			responseJson(json);
			Log.log("改变网页状态结束");
			return;
		}
		Page page = pageService.selectPageByPrimaryKey(pageId);
		if (page == null) {
			setReturnJson(json, 2, 2, "该网页不存在", "");
			responseJson(json);
			Log.log("改变网页状态结束");
			return;
		}
		if (page.getDisable() == 0) {
			page.setDisable((short) 1);
			setReturnJson(json, 1, 0, "停止成功", "");
		} else {
			page.setDisable((short) 0);
			setReturnJson(json, 1, 0, "重启成功", "");
		}
		pageService.updatePage(page);
		responseJson(json);
		Log.log("改变网页状态结束");
		return;
	}

	public String search() {
		Log.log("模糊匹配页面开始:");
		List<Page> list;
		if (StringUtil.isEmpty(searchContent)) {
			list = pageService.findAll();
		}else{
			list = pageService.findBysearchContent(searchContent);
		}
		dealPageList(list);
		request.setAttribute("pageList", list);
		request.setAttribute("pageActive", "vote");
		Log.log("模糊匹配页面结束");
		return SUCCESS;
	}

	public void dealPageList(List<Page> list) {
		if (!ObjectUtil.isEmptyList(list)) {
			for (int i = 0; i < list.size(); i++) {
				Page page = list.get(i);
				page.setBuildTimeString(StringUtil.Timestamp2String(page
						.getBuildTime()));
				page.setDeadLineString(StringUtil.Timestamp2String(page
						.getDeadLine()));
				String mainTitle = page.getMainTitle();
				if (mainTitle != null && mainTitle.length() > 8) {
					page.setMainTitle(mainTitle.substring(0, 6) + "....");
				}
				if (page.getDeadLineString() == null)
					page.setDeadLineString("--");
				if (page.getIsImageVote() == 1)
					page.setImageVoteString("图片投票");

				User user = userService.selectByPrimaryKey(page.getUserId());
				page.setUsername(user.getUsername());
			}
		}
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}
}
