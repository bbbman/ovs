package cn.scau.common;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;

import javax.servlet.http.HttpServletRequest;

public class ClientIp {
	
	/** 
	 * 获取访问用户的客户端IP（适用于公网与局域网）. 
	 */
	public static final String getIpAddr(final HttpServletRequest request)  
	        throws Exception {  
	    if (request == null) {  
	        throw (new Exception("getIpAddr method HttpServletRequest Object is null"));  
	    }  
	    String ipString = request.getHeader("x-forwarded-for");  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("Proxy-Client-IP");  
	    }  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getHeader("WL-Proxy-Client-IP");  
	    }  
	    if (StringUtil.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {  
	        ipString = request.getRemoteAddr();  
	    }  	      
	    // 多个路由时，取第一个非unknown的ip  
	    final String[] arr = ipString.split(",");  
	    for (final String str : arr) {  
	        if (!"unknown".equalsIgnoreCase(str)) {  
	            ipString = str;  
	            break;  
	        }  
	    }  	      
	    return ipString;  
	}
	
	/** 
     * 获取当前<a href="https://www.baidu.com/s?wd=%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1dBmWPWP1bLmhF9m163uWmv0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPH6LnjTLPHfd" target="_blank" class="baidu-highlight">操作系统</a>名称. return <a href="https://www.baidu.com/s?wd=%E6%93%8D%E4%BD%9C%E7%B3%BB%E7%BB%9F&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1dBmWPWP1bLmhF9m163uWmv0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPH6LnjTLPHfd" target="_blank" class="baidu-highlight">操作系统</a>名称 例如:windows xp,linux 等. 
     */  
    public static String getOSName() {  
        return System.getProperty("os.name").toLowerCase();  
    }  
    /** 
     * 获取unix网卡的mac地址. 非windows的系统默认调用本方法获取. 
     * 如果有特殊系统请继续扩充新的取mac地址方法. 
     *  
     * @return mac地址 
     */  
    public static String getUnixMACAddress() {  
        String mac = null;  
        BufferedReader bufferedReader = null;  
        Process process = null;  
        try {  
            // linux下的命令，一般取eth0作为本地主网卡  
            process = Runtime.getRuntime().exec("ifconfig eth0");  
            // 显示信息中包含有mac地址信息  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                // 寻找标示字符串[hwaddr]  
                index = line.toLowerCase().indexOf("hwaddr");  
                if (index >= 0) {// 找到了  
                    // 取出mac地址并去除2边空格  
                    mac = line.substring(index + "hwaddr".length() + 1).trim();  
                    break;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null;  
        }  
        return mac;  
    }  
    /** 
     * 获取widnows网卡的mac地址. 
     *  
     * @return mac地址 
     */  
    public static String getWindowsMACAddress() {  
        String mac = null;  
        BufferedReader bufferedReader = null;  
        Process process = null;  
        try {  
            // windows下的命令，显示信息中包含有mac地址信息  
            process = Runtime.getRuntime().exec("ipconfig /all");  
            bufferedReader = new BufferedReader(new InputStreamReader(  
                    process.getInputStream()));  
            String line = null;  
            int index = -1;  
            while ((line = bufferedReader.readLine()) != null) {  
                System.out.println(line);  
                // 寻找标示字符串[physical  
                index = line.toLowerCase().indexOf("physical address");  
                   
                if (index >= 0) {// 找到了  
                    index = line.indexOf(":");// 寻找":"的位置  
                    if (index >= 0) {  
                        System.out.println(mac);  
                        // 取出mac地址并去除2边空格  
                        mac = line.substring(index + 1).trim();  
                    }  
                    break;  
                }  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
        } finally {  
            try {  
                if (bufferedReader != null) {  
                    bufferedReader.close();  
                }  
            } catch (IOException e1) {  
                e1.printStackTrace();  
            }  
            bufferedReader = null;  
            process = null;  
        }  
   
        return mac;  
    }  
    /** 
     * windows 7 专用 获取MAC地址 
     *  
     * @return 
     * @throws Exception 
     */  
    public static String getMACAddress() throws Exception {  
           
        // 获取本地IP对象  
        InetAddress ia = InetAddress.getLocalHost();  
        // 获得<a href="https://www.baidu.com/s?wd=%E7%BD%91%E7%BB%9C%E6%8E%A5%E5%8F%A3&tn=44039180_cpr&fenlei=mv6quAkxTZn0IZRqIHckPjm4nH00T1dBmWPWP1bLmhF9m163uWmv0ZwV5Hcvrjm3rH6sPfKWUMw85HfYnjn4nH6sgvPsT6KdThsqpZwYTjCEQLGCpyw9Uz4Bmy-bIi4WUvYETgN-TLwGUv3EPH6LnjTLPHfd" target="_blank" class="baidu-highlight">网络接口</a>对象（即网卡），并得到mac地址，mac地址存在于一个byte数组中。  
        byte[] mac = NetworkInterface.getByInetAddress(ia).getHardwareAddress();  
   
        // 下面代码是把mac地址拼装成String  
        StringBuffer sb = new StringBuffer();  
   
        for (int i = 0; i < mac.length; i++) {  
            if (i != 0) {  
                sb.append("-");  
            }  
            // mac[i] & 0xFF 是为了把byte转化为正整数  
            String s = Integer.toHexString(mac[i] & 0xFF);  
            sb.append(s.length() == 1 ? 0 + s : s);  
        }  
   
        // 把字符串所有小写字母改为大写成为正规的mac地址并返回  
        return sb.toString().toUpperCase();  
    } 
}
