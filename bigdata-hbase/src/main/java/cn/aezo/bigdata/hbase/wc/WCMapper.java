package cn.aezo.bigdata.hbase.wc;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class WCMapper extends Mapper<LongWritable,Text,Text,IntWritable> {
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        System.out.println("key = " + key);
        String[] splits = value.toString().split(" ");
        for (String str: splits) {
            // 此处应该将new Text定义成成员变量来提供性能，略
            context.write(new Text(str), new IntWritable(1));
        }
    }
}
