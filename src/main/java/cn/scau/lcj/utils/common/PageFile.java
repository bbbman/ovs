package cn.scau.lcj.utils.common;

import java.text.SimpleDateFormat;

import cn.scau.common.UUIDUtil;

public class PageFile {
	
	public static String buildFile(){
		
		return null;
	}
	public static String buildUrl(){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
		sdf.format(System.currentTimeMillis());
		String uuid = UUIDUtil.biuldUUID().replaceAll("-", "");
		//pageFile/用户标识符/xxx文件
		return new StringBuilder().append("test\\pageFile\\")
				           .append(sdf.format(System.currentTimeMillis()))
				           .append("-")
					       .append(uuid)					       					       
					       .append(".html").toString();

	}
}
