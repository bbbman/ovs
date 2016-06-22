package cn.scau.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletContext;

public class UpLoadUtil {
	
	private static final int BUFFER_SIZE = 16 * 1024;
	
	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmss");
	
	public static void copy(File src, File dst) {  
	    try {  
	        InputStream in = null;  
	        OutputStream out = null;	      
	        try {  
	            in = new BufferedInputStream(new FileInputStream(src),  
	                    BUFFER_SIZE);  
	            out = new BufferedOutputStream(new FileOutputStream(dst),  
	                    BUFFER_SIZE);  
	            byte[] buffer = new byte[BUFFER_SIZE];  
	            while (in.read(buffer) > 0) {  
	                out.write(buffer);  
	            }  
	        } finally {  
	            if (null != in) {  
	                in.close();  
	            }  
	            if (null != out) {  
	                out.close();  
	            }  
	        }  
	    } catch (Exception e) {  
	        e.printStackTrace();  
	    }  
	}  
	  
	public static String getExtention(String fileName) {  
	    int pos = fileName.lastIndexOf(".");  
	    return new Date().getTime()+fileName.substring(pos);  
	}
	
	public static String biuldImagePath(String userID,String contentType){
		String[] s = contentType.split("/");
		sdf.format(System.currentTimeMillis());
		//userImages/用户标识符/xxx文件
		return new StringBuilder().append("\\test\\userImages\\")
					       .append(userID)
					       .append("\\")
					       .append(sdf.format(System.currentTimeMillis()))
					       .append(".").append(s[s.length-1]).toString();		
	}
	public static String biuldFileName(String contentType){
		String[] s = contentType.split("/");
		sdf.format(System.currentTimeMillis());
		//userImages/用户标识符/xxx文件
		return new StringBuilder()
					       .append(sdf.format(System.currentTimeMillis())+"-")
					       .append(UUIDUtil.biuldUUID())
					       .append(".").append(s[s.length-1]).toString();		
	}
}
