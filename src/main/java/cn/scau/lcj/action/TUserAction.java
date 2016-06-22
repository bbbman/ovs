package cn.scau.lcj.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;

import cn.scau.lcj.entity.TUser;
import cn.scau.lcj.service.HtmlService;
import cn.scau.lcj.service.TUserService;
import cn.scau.lcj.utils.struts.ExtendSupport;

public class TUserAction extends ExtendSupport implements ModelDriven<TUser>, Preparable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5342408047139216765L;
	private static final Logger LOGGER = Logger.getLogger(TUserAction.class);
	private Integer id;
	private TUser tUser;
	private List<TUser> tUserList;
	@Autowired
	private TUserService tUserService;
	@Autowired
	private HtmlService htmlService;
	public void testFreemaker(){
		
		//htmlService.createHtml(request.getSession().getServletContext());
	}
	
	public String execute() throws Exception{
		LOGGER.info("查询所有用户");
		tUserList = tUserService.findAll();
		HttpServletRequest  request = ServletActionContext.getRequest();
		request.setAttribute("tUserList", tUserList);
		request.setAttribute("nihao", "nihao");
		return SUCCESS;
	}
	public void detail() throws IOException{
		String id = ServletActionContext.getRequest().getParameter("id");
		LOGGER.info("查看用户详情:"+id);
		tUser = tUserService.get(Integer.valueOf(id));
		HttpServletResponse response= ServletActionContext.getResponse();
		//AjaxUtil.ajaxJSONResponse(tUser);
		response.getWriter().write(tUser.getUsername());
	}

	@Override
	public void prepare() throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public TUser getModel() {
		// TODO Auto-generated method stub
		
		return null;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public TUser gettUser() {
		return tUser;
	}

	public void settUser(TUser tUser) {
		this.tUser = tUser;
	}

	public List<TUser> gettUserList() {
		return tUserList;
	}

	public void settUserList(List<TUser> tUserList) {
		this.tUserList = tUserList;
	}

}
