package cn.scau.lcj.action.user.managercenter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.alibaba.fastjson.JSONObject;

import cn.scau.common.FileUtil;
import cn.scau.common.ObjectUtil;
import cn.scau.common.StringUtil;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.ImageService;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.TitleService;
import cn.scau.lcj.service.OptionService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class VoteManagerAction extends ExtendSupport {

	private Integer pageId;

	@Autowired
	private PageService pageService;

	@Autowired
	private TitleService titleService;

	@Autowired
	private OptionService OptionService;

	@Autowired
	private ImageService imageService;

	public String execute() {
		Log.log("投票管理开始");
		User user = (User) session.get(Constant.USER_SESSION);
		List<Page> list = pageService.findByUserId(user.getUserId());
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
			}
		}
		request.setAttribute("pageList", list);
		request.setAttribute("pageActive", "managerCenter");
		Log.log("投票管理结束");
		return SUCCESS;
	}

	public void delPage() {
		Log.log("删除页面开始");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if (pageId == null) {
			setReturnJson(json, 2, 1, "该投票不存在", "");
			responseJson(json);
			Log.log("删除页面结束");
			return;
		}
		Page page = pageService.selectPageByPrimaryKey(pageId);
		if (page == null) {
			setReturnJson(json, 2, 2, "该投票不存在", "");
			responseJson(json);
			Log.log("删除页面结束");
			return;
		}
		if (page.getUserId() != user.getUserId()) {
			setReturnJson(json, 2, 3, "无权限删除该投票", "");
			responseJson(json);
			Log.log("删除页面结束");
			return;
		}
		pageService.deleteByPrimaryKey(pageId);
		
		List<Title> titleList = titleService.selectByPageSeq(pageId);
		if (!ObjectUtil.isEmptyList(titleList))
			for (int i = 0; i < titleList.size(); i++) {
				Title title = titleList.get(i);
				OptionService.deleteByTitleSeq(title.getTitleId());
				OptionService.deleteOtherOptionByTitleSeq(title.getTitleId());
			}
		titleService.deleteByPageSeq(pageId);
		
		List<Image> imageList = imageService.selectByPageSeq(pageId);
		if (!ObjectUtil.isEmptyList(imageList)) {
			List<String> filePaths = new ArrayList<String>();
			for (int j = 0; j < imageList.size(); j++) {
				filePaths.add(imageList.get(j).getImagePath());
			}
			FileUtil.delFiles(filePaths);
		}
		imageService.deleteByPageSeq(pageId);
		
		String pagePath = request.getSession().getServletContext().getRealPath("/")+page.getUrl();
		FileUtil.delFile(pagePath);
		
		setReturnJson(json, 1, 0, "删除成功", "");
		responseJson(json);
		Log.log("删除页面结束");
	}
	
	public void clearZero(){
		Log.log("清零开始");
		JSONObject json = new JSONObject();
		User user = (User) session.get(Constant.USER_SESSION);
		if (pageId == null) {
			setReturnJson(json, 2, 1, "该投票不存在", "");
			responseJson(json);
			Log.log("清零页面结束");
			return;
		}
		Page page = pageService.selectPageByPrimaryKey(pageId);
		if (page == null) {
			setReturnJson(json, 2, 2, "该投票不存在", "");
			responseJson(json);
			Log.log("清零页面结束");
			return;
		}
		if (page.getUserId() != user.getUserId()) {
			setReturnJson(json, 2, 3, "无权清零该投票", "");
			responseJson(json);
			Log.log("清零页面结束");
			return;
		}
		
		page.setBrowseTimes(0);
		page.setSubmitTimes(0);
		pageService.updatePage(page);
		
		List<Title> titleList = titleService.selectByPageSeq(pageId);
		if (!ObjectUtil.isEmptyList(titleList))
			for (int i = 0; i < titleList.size(); i++) {
				Title title = titleList.get(i);
				title.setTitleSelectTimes(0);
				title.setOtherOptionSelectTimes(0);
				titleService.updateTitleByObject(title);
				OptionService.update2ZreoByTitleSeq(title.getTitleId());
				OptionService.deleteOtherOptionByTitleSeq(title.getTitleId());
			}				
		imageService.update2ZeroByPageSeq(pageId);
		
		setReturnJson(json, 1, 0, "清零成功", "");
		responseJson(json);
		Log.log("清零页面结束");
		return;
	}

	public Integer getPageId() {
		return pageId;
	}

	public void setPageId(Integer pageId) {
		this.pageId = pageId;
	}
}
