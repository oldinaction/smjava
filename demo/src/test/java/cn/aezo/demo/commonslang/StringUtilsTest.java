package cn.aezo.demo.commonslang;

import org.apache.commons.lang3.StringUtils;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by smalle on 2017/9/10.
 */
public class StringUtilsTest {
    public static void main(String[] args) {

    }

    // 空判断
    @Test
    public void testEmpty() {
        // 空判断：此处传入一个CharSequence(字符序列, String, StringBuffer均实现了此接口)
        Assert.assertFalse(StringUtils.isEmpty("smalle"));
        Assert.assertTrue(StringUtils.isEmpty(""));
        Assert.assertTrue(StringUtils.isEmpty(null));

        Assert.assertTrue(StringUtils.isNotEmpty("smalle"));

        // 空白字符串判断：空格、\t、\n等都任务是空白字符串
        Assert.assertFalse(StringUtils.isBlank("smalle"));
        Assert.assertTrue(StringUtils.isBlank("    \t\n"));
        Assert.assertTrue(StringUtils.isBlank(null));
    }

    // 转换
    @Test
    public void testTransform() {
        // 首字母大写，可为空
        Assert.assertEquals("Smalle", StringUtils.capitalize("smalle"));
        Assert.assertEquals(null, StringUtils.capitalize(null));
    }

}
