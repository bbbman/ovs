package cn.scau.common;

import java.util.HashMap;
import java.util.Map;

import cn.scau.lcj.entity.User;

public class SessionMap {
	
	private static Map<String,User> map = new HashMap<String,User>();
	
	public static void put(String key,User user){
		map.put(key, user);
	}
	public static User get(String key){
		return map.get(key);
	}
	public static void remove(String key){
		map.remove(key);
	}
	public static boolean isContain(String key){
		return map.containsKey(key);
	}
}
