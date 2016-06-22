package cn.scau.lcj.utils.struts;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import cn.scau.lcj.entity.TUser;
import cn.scau.lcj.utils.common.Log;

import com.alibaba.fastjson.JSONObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class ExtendSupport extends ActionSupport implements ServletRequestAware,ServletResponseAware,SessionAware {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -339867933365181917L;
	public ActionContext context = ActionContext.getContext();
	public ServletContext scontext;
	 public  HttpServletRequest  request;
	 public HttpServletResponse response;
	 public Map<String,Object> session;
	public ExtendSupport(){
		super();
	}
	public HttpServletRequest getRequest() {
		return request;
	}
	public void setRequest(HttpServletRequest request) {
		this.request = request;
		scontext = request.getSession().getServletContext();
	}
	public HttpServletResponse getResponse() {
		return response;
	}
	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}
	public Map<String, Object> getSession() {
		return session;
	}
	@Override
	public void setServletResponse(HttpServletResponse response) {
		// TODO Auto-generated method stub
		this.response = response;
	}
	@Override
	public void setServletRequest(HttpServletRequest request) {
		// TODO Auto-generated method stub
		this.request = request;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		// TODO Auto-generated method stub
		this.session = session;
	}
	//返回json数据
	public void responseJson(JSONObject json){
		try{
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println(json.toJSONString());
			out.flush();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}				
	}
   //返回json状态
	public void setReturnJson(JSONObject json,int status,int resultCode,String message,Object data){
		json.put("status",status);
		json.put("resultcode", resultCode);
		json.put("message", message);
		json.put("data", data);
	}
}
