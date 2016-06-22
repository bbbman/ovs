package cn.scau.lcj.action.user;

import java.io.File;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.common.StringUtil;
import cn.scau.common.UUIDUtil;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Image;
import cn.scau.lcj.entity.createVote.Option;
import cn.scau.lcj.entity.createVote.OtherOption;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.entity.createVote.Title;
import cn.scau.lcj.service.HtmlService;
import cn.scau.lcj.service.ImageService;
import cn.scau.lcj.service.OptionService;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.TitleService;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.common.PageFile;
import cn.scau.lcj.utils.struts.ExtendSupport;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class CreateVoteAction extends ExtendSupport {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1157552411253285488L;
	
	private String requestParam;
	
	public String getRequestParam() {
		return requestParam;
	}

	public void setRequestParam(String requestParam) {
		this.requestParam = requestParam;
	}

	private Integer pageId;
	
	private User user;
	
	private boolean has_image = false;

	@Autowired
	private PageService pageService;
	@Autowired
	private TitleService titleService;
	@Autowired
	private OptionService optionService;
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private HtmlService htmlService;

	

	private Page buildPage(JSONObject pageJson) {
		Page page = new Page();
		page.setAddCollect(pageJson.getInteger("addCollect"));// 设置用户信息收集功能单
		page.setAgreeTerm(pageJson.getInteger("agreeTerm"));// 是否同意协议
		page.setBuildTime(new Timestamp(System.currentTimeMillis()));// 设置建page时间
		if(!StringUtil.isEmpty(pageJson.getString("deadLine")))
		   page.setDeadLine(StringUtil.Str2Timestamp(pageJson.getString("deadLine")+" 00:00:00"));
		// page.setDisable(disable);//是否禁用
		page.setDisplayAfterVote(pageJson.getString("displayAfterVote"));// 投票后显示的内容
		page.setFastVote(pageJson.getInteger("fastVote"));// 快速投票
		page.setMainTitle(pageJson.getString("pageTitle"));// 投票主题
		page.setModifyTime(null);// 修改时间
		page.setPrivateVote(pageJson.getInteger("privateVote"));// 投票隐私
		page.setSeeAfterVote(pageJson.getInteger("seeAfterVote"));// 投票后可见
		page.setVoteDesc(pageJson.getString("voteDesc"));// 活动介绍
		page.setUserId(user.getUserId());// 页面创建者
		//page.setUrl(PageFile.buildUrl());// 页面路径
		pageId = pageService.savePage(page);
		if(pageId ==null)
			return null;
		page.setPageId(pageId);
		return page;
	}

	private List<Map<String,Object>> buildTitle(JSONArray titleArr) {
		List<Map<String,Object>> titleList = new ArrayList<Map<String,Object>>();		
		for(int i=0 ; i<titleArr.size();i++){
			JSONObject titleJson = (JSONObject) titleArr.get(i);
			Map<String,Object> titleMap = new HashMap<String,Object>();
			Title title = new Title();
			title.setOtherOption(titleJson.getInteger("otherOption"));
			title.setPageId(pageId);
			title.setTitleContent(titleJson.getString("titleContent"));
			title.setTitleOtherContent(titleJson.getString("otherOptionContent"));
			title.setTitlePosition(titleJson.getInteger("titlePosition"));
			title.setTitleType(titleJson.getInteger("titleType"));
			// 插入数据库
			 Integer titleId = titleService.saveTitle(title);
			 JSONArray optionArr = titleJson.getJSONArray("optionList");
			 List<Option> optionList = buildOption(optionArr,titleId); 
			 titleMap.put("title", title);
			 titleMap.put("optionList", optionList);
			 titleList.add(titleMap);
		}
		return titleList;
	}

	private List<Option> buildOption(JSONArray optionArray,Integer titleId) {
		
		List<Option> optionList = new ArrayList<Option>();
		
		for (int i = 0; i < optionArray.size(); i++) {
			JSONObject json = optionArray.getJSONObject(i);
			Option option = new Option();
			option.setOptionContent(json.getString("optionContent"));
			option.setOptionPosition(json.getInteger("optionPosition"));
			option.setTitleId(titleId);
			Integer optionId = optionService.save(option);			
			if (optionId != null) {
				Integer imageId = json.getInteger("imageId");
				if (imageId != null) {
					Image image = imageService.getImageById(imageId);
					if (image != null) {
						image.setOptionId(optionId);
						image.setPageId(pageId);
						image.setUserId(user.getUserId());
						imageService.updateImage(image);						
						option.setImagePath(image.getImagePath());
						has_image = true;//该页面有图片
					}
				}
			}			
			option.setOptionId(optionId);
			optionList.add(option);
		}						
		return optionList;
	}

	public String execute() {
		Log.log("创建表单网页开始");
		request.setAttribute("pageActive", "createVote");
		Log.log("创建表单网页结束");
		return SUCCESS;
	}

	public void createHtml() {
		Log.log("收集表单数据开始");
		JSONObject json = new JSONObject();
		try{
			String path = request.getContextPath();
			String basePath = request.getScheme() + "://"
					+ request.getServerName() + ":" + request.getServerPort()
					+ path + "/";
			user = (User) session.get(Constant.USER_SESSION);
			if(user ==null)return;
			JSONObject pageJson = JSONObject.parseObject(requestParam);
			JSONArray titleArr  = pageJson.getJSONArray("titleList");
			
			Page page = buildPage(pageJson);
			List<Map<String,Object>> titleData = buildTitle(titleArr);
			
			Map<String,Object> pageData = new HashMap<String,Object>();
						
			pageData.put("page", page);	
			pageData.put("user", user);
			pageData.put("basePath", basePath);
			pageData.put("titleList", titleData);
			
			String htmlPath = htmlService.createHtml(request.getSession().getServletContext(),pageData);
			if(htmlPath == null){
				setReturnJson(json, 2, 3, "创建投票失败", "");
				responseJson(json);
				Log.log("收集表单数据结束");
				return;
			}
			//更新路径
			page.setUrl(htmlPath);
			if(has_image)
				page.setIsImageVote(1);//有图片
			pageService.updatePage(page);
			setReturnJson(json, 1, 0, "创建成功", basePath+htmlPath);
			responseJson(json);			
			Log.log("收集表单数据结束");
			return;
		}catch(Exception e){
			setReturnJson(json, 2, 4, "创建投票失败", "");
			responseJson(json);
			Log.log("收集表单数据结束");
			return;
		}			
	}	
}
