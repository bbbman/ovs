package cn.scau.common;

import java.security.MessageDigest;

import cn.scau.lcj.utils.common.Log;

public class Md5Util {
	 private static MessageDigest md5 = null;
	    static {
	        try {
	            md5 = MessageDigest.getInstance("MD5");
	        } catch (Exception e) {
	           Log.log(e.getMessage());
	        }
	    }

	    /**
	     * 用于获取一个String的md5值
	     * @param string
	     * @return
	     */
	    public static String getMd5String(String str) {
	        byte[] bs = md5.digest(str.getBytes());
	        StringBuilder sb = new StringBuilder(40);
	        for(byte x:bs) {
	            if((x & 0xff)>>4 == 0) {
	                sb.append("0").append(Integer.toHexString(x & 0xff));
	            } else {
	                sb.append(Integer.toHexString(x & 0xff));
	            }
	        }
	        return sb.toString();
	    }
	    public static void main(String[] args) {
	        System.out.println(getMd5String("123"));
	    }
}
