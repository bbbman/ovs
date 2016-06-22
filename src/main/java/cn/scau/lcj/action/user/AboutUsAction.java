package cn.scau.lcj.action.user;

import cn.scau.lcj.utils.struts.ExtendSupport;

public class AboutUsAction extends ExtendSupport{
	
	public String execute(){
		request.setAttribute("pageActive", "aboutUs");
		return SUCCESS;				
	}
}
