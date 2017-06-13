package cn.smalle.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 * <BR>
 * SmDateUtil <BR>
 * @author oldinaction
 * @date 2014年11月25日-下午4:30:55
 * @version 1.0.0
 *
 */
public class SmDateUtils {
	
	/**
	 * 日期转换成格林威治时间
	 * 方法名：dateToString<BR>
	 * 创建人：潭州学院-keke <BR>
	 * 时间：2014年11月11日-下午10:28:41 <BR>
	 * @param time
	 * @return Date<BR>
	 * @exception <BR>
	 * @since  1.0.0
	 */
	public static Date dateToString(String time){
		Date startTime = null;
		try {
			//如将2012-12-12 12:12:12转成Wed Dec 12 12:12:12 CST 2012
			startTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(time);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startTime;
	}
	
	/**
	 * 日期转换(格式化日期)<BR>
	 * dateFormat("2012-12-12 12:12:12", "yyyy"));年份<BR>
	 * dateFormat("2012-12-12 12:12:12", "MM"));月份(MM表示月份，mm表示分钟)<BR>
	 * dateFormat("2012-12-12 12:12:12", "dd"));天<BR>
	 * dateFormat("2012-12-12 12:12:12", "HH:mm:ss"));HH表示24小时制<BR>
	 * dateFormat("2012-12-12 12:12:12", "hh:mm:ss"));hh表示12小时制<BR>
	 * dateFormat("2012-12-12 12:12:12", "yyyy-MM-dd"));年月日<BR>
	 * dateFormat("2012-12-12 12:12:12", "yyyy-MM-dd HH:mm"));年月日 时分<BR>
	 * dateFormat("2012-12-12 12:12:12", "yyyy-MM-dd HH:mm:ss"));年月日 时分秒<BR>
	 * <BR>
	 * @method dateFormat 
	 * @author oldinaction
	 * @date 2014年11月25日-下午4:47:26
	 * @param dateString
	 * @param format
	 * @return String <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String dateFormat(String dateString,String format){
		if(SmStringUtils.isEmpty(dateString))return "";
		try {
			//如将2012-12-12 12:12:12转成Wed Dec 12 12:12:12 CST 2012
			Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
			//再将Wed Dec 12 12:12:12 CST 2012转成2012-12-12等格式(取决于参数format)
			String ds =  new SimpleDateFormat(format).format(date);
			return ds;
		} catch (ParseException e) {
			return "";
		}
	}
	
	/**
	 * 获取几分钟前，几年前(一个时间相当于现在)
	 * <BR>
	 * @method getTimeFormat 
	 * @author oldinaction
	 * @date 2014年11月25日-下午4:33:01
	 * @param startTime
	 * @return String <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String getTimeFormat(Date startTime){
		try{
			long startTimeMills = startTime.getTime();
			long endTimeMills = System.currentTimeMillis();
			long diff = (endTimeMills - startTimeMills)/1000; //秒
			long day_diff  = (long) Math.floor(diff/86400); //天
			StringBuffer buffer = new StringBuffer();
			if(day_diff<0){
				return "[error],时间越界...";
			}else{
				if(day_diff==0 && diff<60){
					if(diff==0)diff=1;
					buffer.append(diff+"秒前");
				}else if(day_diff==0 && diff<120){
					buffer.append("1 分钟前");
				}else if(day_diff==0 && diff<3600){
					buffer.append(Math.round(Math.floor(diff/60))+"分钟以前");
				}else if(day_diff==0 && diff<7200){
					buffer.append("1小时前");
				}else if(day_diff==0 && diff<86400){
					buffer.append(Math.round(Math.floor(diff/3600))+"小时前");
				}else if(day_diff==1){
					buffer.append("1天前");
				}else if(day_diff<7){
					buffer.append(day_diff+"天前");
				}else if(day_diff <30){
					buffer.append(Math.round(Math.ceil( day_diff / 7 )) + " 星期前");
				}else if(day_diff >=30 && day_diff<=179 ){
					buffer.append(Math.round(Math.ceil( day_diff / 30 )) + "月前");
				}else if(day_diff >=180 && day_diff<365){
					buffer.append("半年前");
				}else if(day_diff>=365){
					buffer.append(Math.round(Math.ceil( day_diff /30/12))+"年前");
				}
			}
			return buffer.toString();
		}catch(Exception ex){
			return "";
		}
	}
	
	/**
	 * 获取几分钟前，几年前(重载getTimeFormat方法，参数传字符串类型) 
	 * <BR>
	 * @method getTimeFormat 
	 * @author oldinaction
	 * @date 2014年11月25日-下午4:33:10
	 * @param startTime
	 * @return String <BR> 
	 * @exception <BR> 
	 * @since  1.0.0
	 */
	public static String getTimeFormat(String startTime){
		return getTimeFormat(dateToString(startTime));
	}

	
}
