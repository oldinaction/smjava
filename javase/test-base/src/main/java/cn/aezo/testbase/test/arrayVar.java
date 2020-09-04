package cn.aezo.testbase.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 通过变量设置数组的长度
 * <BR>
 * arrayVar <BR>
 *
 * @author oldinaction
 * @version 1.0.0
 * @date 2014年10月23日-上午9:13:14
 */
public class arrayVar {
    public static void main(String[] args) {
        int nums = 0;
        String a = null;
        System.out.println("请输入2或者3后回车:");
        //在控制台输入2或者3
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        try {
            a = bufferedReader.readLine();
            System.out.println("a========" + a);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (a.equalsIgnoreCase("2")) {
            nums = 2;
            System.out.println("nums========" + nums);
        } else if (a.equalsIgnoreCase("3")) {
            nums = 3;
        }

        String[] strs = new String[nums]; // 动态设置数组的长度(一旦设定后也是不能再修改的)
        System.out.println("strs.length=========" + strs.length);
    }
}
