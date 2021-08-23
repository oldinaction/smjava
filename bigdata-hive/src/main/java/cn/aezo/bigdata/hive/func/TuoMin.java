package cn.aezo.bigdata.hive.func;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.Text;

/**
 * hive自定义函数案例
 * 需打成jar包，创建自定义函数
 * @author smalle
 * @since 2021-07-14
 */
public class TuoMin extends UDF {

    public Text evaluate(final Text s) {
        if (s == null) {
            return null;
        }
        String str = s.toString().substring(0, 1) + "***";
        return new Text(str);
    }

}