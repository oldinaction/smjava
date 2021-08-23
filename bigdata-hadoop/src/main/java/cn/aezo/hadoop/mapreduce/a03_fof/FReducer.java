package cn.aezo.hadoop.mapreduce.a03_fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;

public class FReducer extends Reducer<Text, IntWritable,Text,IntWritable> {

    IntWritable rval = new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 张三-李四: 0
        // 张三-李四: 0
        // 张三-李四: 1
        // 张三-李四: 1

        // 是否不存在直接好友关系
        int flag = 0;
        // 存在间接好友关系的个数
        int sum = 0;
        for (IntWritable v : values) {
            if(v.get() ==  0){
                flag = 1;
            }
            sum += v.get();
        }

        if(flag == 0) {
            // 非直接好友关系
            rval.set(sum);
            context.write(key,rval);
        }
    }
}
