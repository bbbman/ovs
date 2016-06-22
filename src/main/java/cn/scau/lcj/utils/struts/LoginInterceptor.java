package cn.scau.lcj.utils.struts;

import java.util.Map;

import cn.scau.lcj.entity.User;
import cn.scau.lcj.utils.common.Constant;
import cn.scau.lcj.utils.common.Log;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor {  
	 
    /**
	 * 
	 */
	private static final long serialVersionUID = -2802675879378482933L;

	@Override  
    public String intercept(ActionInvocation invocation) throws Exception { 
		Log.log("登录拦截开始===========================");
    	//return invocation.invoke();
        // 取得请求相关的ActionContext实例  
        ActionContext ctx = invocation.getInvocationContext();  
        Map<String, Object> session = ctx.getSession();  
        User user = (User) session.get(Constant.USER_SESSION);  
        String actionName = invocation.getProxy().getActionName();
        String actionMethod = invocation.getProxy().getMethod();
        if(actionName.equals("login")||actionName.equals("index")||actionName.equals("register")||actionName.equals("t-user")||actionName.equals("vote")){
        	Log.log("登录拦结束===========================");
        	return invocation.invoke();
        }
        // 如果登陆了
        if(user != null){//详细信息以后配置  
        	Log.log("登录拦结束===========================");
        	return invocation.invoke();
        }
        ctx.getName();
        invocation.getProxy().getActionName();
        invocation.getProxy().getMethod();
        Log.log(ctx.getName()+"  "+invocation.getProxy().getActionName()+" "+invocation.getProxy().getMethod());        
        ctx.put("tip", "你还没有登录");  
       // return Action.LOGIN; 
        Log.log("登录拦结束===========================");
        return "cc";
  
    } 
}  
