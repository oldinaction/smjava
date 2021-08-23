package cn.aezo.hadoop.mapreduce.a02_topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class TReducer extends Reducer <TKey, IntWritable, Text, IntWritable>{
    Text rkey = new Text();
    IntWritable rval = new IntWritable();

    @Override
    protected void reduce(TKey key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        // 2021-6 这一组(key不同也可能是一组，此处分组按照TGroupingComparator中定义的分组器逻辑来判断的)
        // 2021-6-1-39-beijing: 39
        // 2021-6-1-38-beijing: 38
        // 2021-6-2-31-shanghai: 31
        // ...
        Iterator<IntWritable> iterator = values.iterator();

        int flag = 0; // 是否为第一条数据
        int day = 0;
        while(iterator.hasNext()) {
            // 此处iterator为假迭代器 -> 实际调用真迭代器中的context.nextKeyValue() -> 会自动更新key和value的值(因此传入的引用TKey key的值会变化)
            iterator.next();

            if(flag == 0) {
                // 降序后，第一条数据直接返回
                rkey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay() + "@" + key.getLocation());
                rval.set(key.getWd());
                context.write(rkey, rval);

                flag++;
                day = key.getDay();
            }

            if(flag != 0 && day != key.getDay()) {
                // 只要和第一条数据的天不同，则直接返回作为第二个结果，并停止循环
                rkey.set(key.getYear() + "-" + key.getMonth() + "-" + key.getDay() + "@" + key.getLocation());
                rval.set(key.getWd());
                context.write(rkey, rval);
                break;
            }
        }
    }
}
