package cn.aezo.util;

/**
 * 函数的自定义标签，具体业务在这个类
 * <BR>
 * SmFunction <BR>
 * @author oldinaction
 * @date 2014年11月25日-下午3:19:05
 * @version 1.0.0
 *
 */
public class SmFunction {
	public static String toUpper(String str) {
		if(str != null) {
			String upperString = str.toUpperCase();	
			return upperString;
		} else {
			return "";
		}
	}

}


