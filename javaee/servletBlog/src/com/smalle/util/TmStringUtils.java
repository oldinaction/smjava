package com.smalle.util;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <B>提供如下方法: </B><BR>
 * formatDateToString 格式化日期为字符串 <BR>
 * parseStringToDate 解析字符串为日期型 <BR>
 * formatDoubleToString 格式化Double型数据为字符串 <BR>
 * isEmpty 是否为空字符串 <BR>
 * isNotEmpty 是否为非空字符串 <BR>
 * getPercent <BR>
 * sorts int型数据排序 <BR>
 * encryption 凯德加密 <BR>
 * dencryption 凯德解密 <BR>
 * 
 * <BR>
 * StringUtils<BR>
 * 创建人:oldinaction <BR>
 * 时间：2014年10月20日-下午1:20:20 <BR>
 * @version 1.0.0
 *
 */
public class TmStringUtils {
	
	/**
	 * 格式化日期为字符串(format:格式) <BR>
	 * <BR>
	 * 方法名：formatDateToString <BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月20日-下午1:10:48 <BR> 
	 * @param date
	 * @param pattern
	 * @return String
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String formatDateToString(Date date, String pattern) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.format(date);
	}
	
	/**
	 * 解析字符串为日期型(parse:解析) <BR> 
	 * <BR>
	 * 方法名：parseStringToDate <BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月20日-下午1:11:33 <BR> 
	 * @param dateString
	 * @param pattern
	 * @return
	 * @throws ParseException Date
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Date parseStringToDate(String dateString, String pattern)
			throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		return simpleDateFormat.parse(dateString);
	}
	
	/**
	 * 格式化Double型数据为字符串 <BR> 
	 * <BR>
	 * 方法名：formatDoubleToString<BR> 
	 * 创建人：oldinaction <BR> 
	 * 时间：2014年10月20日-下午1:13:09 <BR> 
	 * @param doubleData (Double型数据)
	 * @param dataFormat (数据格式,为空的话默认去#.##)
	 * @return String<BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String formatDoubleToString(double doubleData, String dataFormat) {
		if(isEmpty(dataFormat)) dataFormat = "#.##";
		DecimalFormat decimalFormat = new DecimalFormat(dataFormat);
		String string = decimalFormat.format(doubleData);
		return string;
	}
	
	public static boolean isEmpty(String str) {
		return null == str || str.length() == 0 || "".equals(str) || str.matches("\\s*");
	}
	
	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}
	
	public static String getPercent(int num, double totalCount, String...objects){
		String format = "#.##";
		if(objects!=null && objects.length>0){
			format = objects[0];
		}
		return TmStringUtils.formatDoubleToString((num/totalCount)*100,format)+"%";
	}
	
	public static String getPercent(int num,float totalCount,String...objects){
		String format = "#.##";
		if(objects!=null && objects.length>0){
			format = objects[0];
		}
		return TmStringUtils.formatDoubleToString((num/totalCount)*100,format)+"%";
	}
	
	public static int[] sorts(int[] datas, boolean flag){
		for (int i = 0; i < datas.length; i++) {
			for(int j=0; j < datas.length-1; j++){
				if(flag){ 
					if(datas[j] < datas[j+1]){
						int temp = datas[j];
						datas[j] = datas[j+1];
						datas[j+1] = temp;
					}
				}else{
					if(datas[j] < datas[j+1]){
						int temp = datas[j];
						datas[j] = datas[j+1];
						datas[j+1] = temp;
					}
				}
			}
		}
		return datas;
	}
	
	/**
	 * 凯德加密
	 * 方法名：encryption<BR>
	 * 创建人：oldinaction <BR>
	 * 时间：2014年10月25日-下午1:25:09 <BR>
	 * @param str
	 * @param key
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String encryption(String str,int key){
		String string = "";
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += key%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=key%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	
	/**
	 * 凯德解密
	 * 方法名：dencryption<BR>
	 * 创建人：oldinaction <BR>
	 * 时间：2014年10月25日-下午1:27:09 <BR>
	 * @param str
	 * @param key
	 * @return String<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static String dencryption(String str,int key){
		String string = "";
		int k = Integer.parseInt("-"+key);
		for (int i = 0; i < str.length(); i++) {
			char c= str.charAt(i);
			if(c>='a' && c<='z'){
				c += k%26;
				if(c<'a'){
					c+=26;
				}
				if(c>'z'){
					c-=26;
				}
			}else if(c>='A' && c<='Z'){
				c+=k%26;
				if(c<'A'){
					c+=26;
				}
				if(c>'Z'){
					c-=26;
				}
			}
			string+=c;
		}
		return string;
	}
	
}


