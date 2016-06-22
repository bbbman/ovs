package cn.scau.common;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {
	
	public static Date getCurDate(){
		return new Date(System.currentTimeMillis());
	}
	
	public static Timestamp getCurTimestamp(){
		return new Timestamp(System.currentTimeMillis());
	}
	//获取某个Timestamp的年月日
	public static Timestamp getYMD(Timestamp timestamp){
		 Calendar t = Calendar.getInstance();
         t.setTime(timestamp);
         t.set(Calendar.HOUR_OF_DAY, 0);
         t.set(Calendar.MINUTE, 0);
         t.set(Calendar.SECOND, 0);
         t.set(Calendar.MILLISECOND, 0);
         String strTime =StringUtil.Date2String(t.getTime()); 
         return Timestamp.valueOf(strTime);
	}
	//获取某个Timestamp的年月日时
	public static Timestamp getYMDH(Timestamp timestamp){
		 Calendar t = Calendar.getInstance();
         t.setTime(timestamp);
         t.set(Calendar.MINUTE, 0);
         t.set(Calendar.SECOND, 0);
         t.set(Calendar.MILLISECOND, 0);
         String strTime =StringUtil.Date2String(t.getTime()); 
         return Timestamp.valueOf(strTime);
	}
	//比较两个Timestamp是否相等
	public static boolean compareA2B(Timestamp s1,Timestamp s2){		
		return s1.equals(s2);
	}
		
	
	 //获取某2个时刻的前后时间段，天为单位
    public static List<Timestamp> getDayPeriod(Timestamp startStamp,Timestamp endStamp) {
    	 Calendar t1 = Calendar.getInstance();
         t1.setTime(startStamp);
         
         t1.set(Calendar.HOUR_OF_DAY, 0);
         t1.set(Calendar.MINUTE, 0);
         t1.set(Calendar.SECOND, 0);
         t1.set(Calendar.MILLISECOND, 0);
         Calendar t2 = Calendar.getInstance();
         t2.setTime(endStamp);
         List<Timestamp> dateList = new ArrayList<Timestamp>();
         while(t1.compareTo(t2)<=0){
        	String strTime =StringUtil.Date2String(t1.getTime()); 
          	dateList.add(Timestamp.valueOf(strTime));          	
         	t1.add(Calendar.FRIDAY, 1);         	         	
         }
        return dateList;
    }
  //获取某2个时刻的前后时间段，小时为单位
    public static List<Timestamp> getHourPeriod(Timestamp startStamp,Timestamp endStamp) {
   	 Calendar t1 = Calendar.getInstance();
        t1.setTime(startStamp);                
        t1.set(Calendar.MINUTE, 0);
        t1.set(Calendar.SECOND, 0);
        t1.set(Calendar.MILLISECOND, 0);
        Calendar t2 = Calendar.getInstance();
        t2.setTime(endStamp);
        List<Timestamp> dateList = new ArrayList<Timestamp>();
        while(t1.compareTo(t2)<=0){
       	String strTime =StringUtil.Date2String(t1.getTime()); 
         	dateList.add(Timestamp.valueOf(strTime));         	
        	t1.add(Calendar.HOUR_OF_DAY, 1);         	         	
        }
       return dateList;
   }        
    
    public static void main(String[]args){
    	String startDate = "2016-01-12 11:20:11";
    	String endDate = "2016-01-12 11:20:11";
    	System.out.println(compareA2B(StringUtil.Str2Timestamp(startDate),StringUtil.Str2Timestamp(endDate)));
    }
}
