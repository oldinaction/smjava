package cn.aezo.hadoop.mapreduce.a01_word_count;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

/**
 * @author smalle
 * @since 2021-05-30
 */
// Object, Text, Text, IntWritable 分别为Mapper计算过程的输入参数Key类型, 输入参数Value类型, 输出参数Key类型, 输出参数Value类型
public class TestMapper extends Mapper<Object, Text, Text, IntWritable> {

    // hadoop有自己一套可以序列化、反序列化类. 如Test => String, IntWritable => Integer
    // 也可自己开发数据类型，但是必须实现：序列化接口、反序列化接口、比较器接口
    // 排序比较分为：字典序、数值顺序
    private Text k = new Text();
    private IntWritable v = new IntWritable(1);

    // key: 是每一行字符串自己第一个字节面向源文件的偏移量
    // value: hello hadoop 1
    // value: hello hadoop 2
    @Override
    public void map(Object key, Text value, Context context) throws IOException, InterruptedException {
        StringTokenizer itr = new StringTokenizer(value.toString());
        while (itr.hasMoreTokens()) {
            // 将k,v定义为成员变量的原因: 由于大量数据处理时，会重复调用此map方法很多次，如果频繁new对象，则会频繁触发GC，从而计算效率变慢
            // 每次进入map方法, k 中存的数据都会被刷走
            k.set(itr.nextToken());
            context.write(k, v);
        }
    }
}
