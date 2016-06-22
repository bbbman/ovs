package cn.scau.common;

import java.util.UUID;

public class UUIDUtil {
	
	public static String biuldUUID(){
		
		return UUID.randomUUID().toString();
	}
	public static String buildTouristName(){
		String userID = UUID.randomUUID().toString();
		return userID.substring(userID.length()-10);
	}
}
