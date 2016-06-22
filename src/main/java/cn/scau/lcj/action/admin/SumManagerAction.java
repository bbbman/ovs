package cn.scau.lcj.action.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import cn.scau.common.ObjectUtil;
import cn.scau.common.StringUtil;
import cn.scau.lcj.entity.User;
import cn.scau.lcj.entity.createVote.Page;
import cn.scau.lcj.service.PageService;
import cn.scau.lcj.service.UserService;
import cn.scau.lcj.utils.common.Log;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class SumManagerAction extends ExtendSupport {
	
	private String searchContent;
	
	@Autowired
	private PageService pageService;
	
	@Autowired
	private UserService userService;

	
	public String execute(){
		Log.log("统计开始:");
		List<Page> list = pageService.findAll();
		dealPageList(list);
		request.setAttribute("pageList", list);
		request.setAttribute("pageActive", "sum");
		Log.log("统计结束");
		return SUCCESS;
	}
	
	public String search() {
		Log.log("模糊统计页面开始:");
		List<Page> list;
		if (StringUtil.isEmpty(searchContent)) {
			list = pageService.findAll();
		}else{
			list = pageService.findBysearchContent(searchContent);
		}
		dealPageList(list);
		request.setAttribute("pageList", list);
		request.setAttribute("pageActive", "sum");
		Log.log("模糊统计页面结束");
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

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}		
}
