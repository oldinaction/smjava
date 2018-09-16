package cn.aezo.demo.commonslang;

import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by smalle on 2017/9/10.
 */
public class TimeTest {

    public static void main(String[] args) {

    }

    @Test
    public void dateUtilsTest() {
        Date date = new Date(1505021318881L);
        System.out.println("=============================基础时间");
        printDate(date); // 2017-09-10 13:28:38.881


        System.out.println("\n=============================时间加减");
        printDate(DateUtils.addYears(date, 1)); // 2018-09-10 13:28:38.881
        printDate(DateUtils.addDays(date, 2)); // 2017-09-12 13:28:38.881
        printDate(DateUtils.addHours(date, 12)); // 2017-09-11 01:28:38.881

        printDate(DateUtils.addMinutes(date, -60)); // 2017-09-10 12:28:38.881


        System.out.println("\n=============================时间进位. ceiling: 天花板、上限");
        printDate(DateUtils.ceiling(date, Calendar.YEAR)); // 进位年份(明年第一天) 2018-01-01 00:00:00.000
        printDate(DateUtils.ceiling(date, Calendar.MONTH)); // 下个月第一天 2017-10-01 00:00:00.000
        printDate(DateUtils.ceiling(date, Calendar.DATE)); // 2017-09-11 00:00:00.000

        System.out.println("\n=============================时间比较");
        System.out.println(DateUtils.isSameDay(date, new Date(1505021318882L))); // true. 精确到天
        System.out.println(DateUtils.isSameInstant(date, new Date(1505021318882L))); // false. 精确到毫秒



    }

    private void printDate(Date d) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        System.out.println(sdf.format(d));
    }
}
