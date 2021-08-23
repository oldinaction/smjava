package cn.aezo.hadoop.mapreduce.a03_fof;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.StringUtils;

import java.io.IOException;

public class FMapper extends Mapper<LongWritable, Text,Text, IntWritable> {

    Text mkey = new Text();
    IntWritable mval = new IntWritable();

    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        // value: 张三 李四 王五 大牛 小红
        String[] strs = StringUtils.split(value.toString(), ' ');
        for (int i = 1; i < strs.length; i++) {
            // 直接好友关系: 张三-李四 张三-王五 ...
            mkey.set(getFof(strs[0], strs[i]));
            mval.set(0);
            context.write(mkey, mval);
            for (int j = i+1; j < strs.length; j++) {
                // 间接好友关系: 李四-王五(间接好友是张三)
                mkey.set(getFof(strs[i],strs[j]));
                mval.set(1);
                context.write(mkey, mval);
            }
        }
    }

    // 获取Key: 某两个人
    public static String getFof(String s1,String s2) {
        // 按顺序放置两个人放置key重复: 张三-李四/李四-张三, 实际应该认为是相同的key
        if(s1.compareTo(s2) > 0){
            return  s1+"-"+s2;
        } else {
            return  s2+"-"+s1;
        }
    }
}
