package cn.scau.common;


import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class StringUtil {
	private static SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	public static boolean isEmpty(String str){
		if(str==null || str.equals(""))
			return true;
		return false;
	}
	
	public static String Date2String(Date date){
		if(date==null)
			return null;
		return sdf.format(date);
	}
	public static String Timestamp2String(Timestamp timestamp){
		if(timestamp==null)
			return null;
		return sdf.format(timestamp);
	}
	public static boolean isBlank(String str){
		if(str ==null || str.equals(""))
			return true;
		return false;
	}
	
	public static Timestamp Str2Timestamp(String dateStr){
		return Timestamp.valueOf(dateStr);
	}
	public static List<String> getYYYYMDStrList(List<Timestamp> stList){
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<stList.size();i++){
    		Timestamp st = stList.get(i);
    		String str = Timestamp2String(st);
    		list.add(str.substring(0, 10));    		
    	}
    	return list;
    }
	public static List<String> getYYYYMDHList(List<Timestamp> stList){
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<stList.size();i++){
    		Timestamp st = stList.get(i);
    		String str = Timestamp2String(st);
    		list.add(str.substring(0, 13));    		
    	}
    	return list;
    }
	public static List<String> getMD_TimeStrList(List<Timestamp> stList){
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<stList.size();i++){
    		Timestamp st = stList.get(i);
    		String str = Timestamp2String(st);
    		list.add(str.substring("-".indexOf(str), " ".indexOf(str)));    		
    	}
    	return list;
    }
	public static List<String> getMDH_TimeStrList(List<Timestamp> stList){
    	List<String> list = new ArrayList<String>();
    	for(int i=0;i<stList.size();i++){
    		Timestamp st = stList.get(i);
    		String str = Timestamp2String(st);
    		list.add(str.substring("-".indexOf(str), ":".indexOf(str)));    		
    	}
    	return list;
    }
	public static String getYYYYMD(Timestamp t){
		String str = Timestamp2String(t);
		return str.substring(0, 10);
	}
	public static String getYYYYMDH(Timestamp t){
		String str = Timestamp2String(t);
		return str.substring(0, 13);
	}
}
