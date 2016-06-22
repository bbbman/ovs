package cn.scau.lcj.action.user;

import cn.scau.lcj.utils.struts.ExtendSupport;

public class ManagerCenterAction extends ExtendSupport{
	
	public String execute(){
		request.setAttribute("pageActive", "managerCenter");
		return SUCCESS;				
	}
	
	public void changeProfile(){
		
	}
}
