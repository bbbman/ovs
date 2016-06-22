package cn.scau.lcj.utils.common;

import org.apache.log4j.Logger;

public class Log {

	private static Logger log = Logger.getLogger(Log.class);
	private Log() {
		super();
	}
	
	public static void debug(Object o){
		log.debug(o);
	}
	
	public static void warn(Object o){
		StackTraceElement stack[] = (new Throwable()).getStackTrace();
		for (int i = 0; i < stack.length; i++) {
			if (i == 1) {
				log.warn("(" + stack[i].getFileName() + ":"
						+ stack[i].getLineNumber() + ") "
						+ stack[i].getMethodName() + ":" + o);
				break;
			}
		}
	}
	/**
	 * Description :打印开关为True时，打印输出日志 
	 * @param :String
	 * @return :String
	 */
	public static void log(Object str) {
		if(true){
			StackTraceElement stack[] = (new Throwable()).getStackTrace();
			for (int i = 0; i < stack.length; i++) {
				if (i == 1) {
					log.info("(" + stack[i].getFileName() + ":"
							+ stack[i].getLineNumber() + ") "
							+ stack[i].getMethodName() + ":" + str);
					break;
				}
			}
		}
	}

}
