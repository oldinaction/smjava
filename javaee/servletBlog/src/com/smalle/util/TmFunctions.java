package com.smalle.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TmFunctions {

	
	public static boolean isEmpty(String content){
		return TmStringUtils.isEmpty(content);
	}
	
	/**
	 * 处理性别方法
	 * <BR>
	 * @method sex 
	 * @author oldinaction
	 * @date 2014年10月25日-上午11:58:02
	 * @param male
	 * @return String <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String sex(int male){
		String result = null;
		if(male==1){
			result = "男";
		}else if(male==2){
			result = "女";
		}else if(male==0){
			result = "保密";
		}
		return result;
	}
	

	
	/**
	 * 格式化日期
	 * <BR>
	 * @method formatDate 
	 * @author oldinaction
	 * @date 2014年10月25日-上午11:58:13
	 * @param date
	 * @param pattern
	 * @return String <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String formatDate(Date date,String pattern){
		if(pattern==null || pattern.equals(""))pattern = "yyyy-MM-dd HH:mm:ss";
		java.text.SimpleDateFormat format = new SimpleDateFormat(pattern);
		return format.format(date);
	}

}
