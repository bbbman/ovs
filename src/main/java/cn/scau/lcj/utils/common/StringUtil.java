package cn.scau.lcj.utils.common;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class StringUtil {
	
	public static boolean isEmpty(String str){
		
		return (str == null || str.equals(""));
	}
	
	//检查邮箱格式
	public static boolean isEmail(String email){
		return email==null?false:true;
	}
	//检查用户名格式
	public static boolean isUsername(String username){
		return username==null?false:true;
	}
	//检查密码格式
	public static boolean isPassword(String password){
		return password==null?false:true;
	}
	public static String getStringFromTimestamp(Timestamp tsp,String defaultValue){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return tsp==null?defaultValue:sdf.format(tsp);
	}
}
