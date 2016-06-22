package cn.scau.lcj.action.user;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class IndexAction extends ExtendSupport{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5861546093719077793L;
	
	@Autowired
	private PageService pageService;

	public String execute(){
		Log.log("首页开始");
		List<Page> nOImgList =  pageService.findNoImagePage(5);
		List<Page> imgList   =  pageService.findImagePage(5);
		request.setAttribute("nOImgList", nOImgList);
		request.setAttribute("imgList", imgList);
		request.setAttribute("pageActive", "index");
		Log.log("首页结束");
		return SUCCESS;				
	}
}
