package cn.aezo.hadoop.mapreduce.a01_word_count;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

/**
 * @author smalle
 * @since 2021-05-30
 */
// Text, IntWritable, Text, IntWritable 分别为Reducer计算过程的输入参数Key类型, 输入参数Value类型, 输出参数Key类型, 输出参数Value类型
public class TestReduce extends Reducer<Text, IntWritable, Text, IntWritable> {
    private IntWritable result = new IntWritable();

    // 相同的key为一组，这一组数据调用一次reduce
    // key value: hello 1
    // key value: hello 1
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum = 0;
        for (IntWritable value : values) {
            sum += value.get();
        }
        result.set(sum);
        context.write(key, result);
    }
}
