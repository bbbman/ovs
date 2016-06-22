package cn.scau.common;

import java.util.List;

public class ObjectUtil {
	
	public static boolean isEmptyList(List<?> list){
		if(list == null || list.size()<0 || list.isEmpty())
			return true;
		return false;
	}

}
