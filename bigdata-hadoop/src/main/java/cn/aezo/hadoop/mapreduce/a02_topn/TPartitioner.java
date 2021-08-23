package cn.aezo.hadoop.mapreduce.a02_topn;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Partitioner;

public class TPartitioner extends Partitioner<TKey, IntWritable> {
    @Override
    public int getPartition(TKey key, IntWritable value, int numPartitions) {
        // 分区器只需满足相同的key获得相同的分区号就可以
        // 分组器需要考量：不应该太复杂，否则增加计算成本；还应该考量数据倾斜，减少每个节点计算任务量分配不均
        // 此案例本身按照年月分区，减少复杂度可按照年分区(则相同年月的数据也一定在一个分区)
        return key.getYear() % numPartitions;
    }
}
