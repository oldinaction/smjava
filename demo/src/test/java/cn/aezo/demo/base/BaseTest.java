package cn.aezo.demo.base;

import org.junit.Test;

/**
 * Created by smalle on 2017/6/5.
 */
public class BaseTest {

    /**
     * & 和 && 的区别
     */
    @Test
    public void test1() {
        // 按位与(&)：只有两个位都是1，结果才是1；按位或(|)；按位非(~)、按位异或(^)：相同则结果为0，不同则结果为1
        // a 10000001
        // b 10000000
        int a = 129;
        int b = 128;
        System.out.println(a & b); // 128

        // 逻辑与(&)：只有两个操作数都是true，结果才是true；短路与(&&)：如果左边操作数为false，就不计算右边的表达式，直接得出false
        System.out.println((1 > 2) & (2 > 1));
        System.out.println((1 > 2) && (2 > 1));
    }

    /**
     * 取整
     */
    @Test
    public void test2() {
        // 四舍五入取整：基于参数加0.5后向下取整
        System.out.println(Math.round(11.4)); // 11
        System.out.println(Math.round(-11.4)); // -11

        // 向下运算
        System.out.println(Math.floor(3.5)); // 3.0
        // 四舍五入
        System.out.println(Math.rint(3.4)); // 3.0
        System.out.println(Math.rint(3.5)); // 4.0
        // 向上运算
        System.out.println(Math.ceil(3.1)); // 4.0
    }


}
