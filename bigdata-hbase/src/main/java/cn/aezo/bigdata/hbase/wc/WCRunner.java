package cn.aezo.bigdata.hbase.wc;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.NullWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;

/**
 *
 * MR是分布式计算框架，对于数据源和数据目的地没有限制，用户可以任意选择，只不过需要实现两个类
 * InputFormat
 *          getsplits()
 *          createRecordReader()
 * OutputFormat
 *         getRecordWriter():返回值：RecordWriter
 *                                      write()
 *                                      close()
 * 注意：
 *      当需要从hbase读取数据的时候，必须使用 TableMapReduceUtil.initTableMapperJob()
 *      当需要写数据到hbase的时候，必须使用 TableMapReduceUtil.initTableReduceJob()
 *              如果再代码逻辑进行实现的时候，不需要reduce，只要是向hbase写数据，那么上面的方法必须存在(reducer=null)
 *
 * 实现wordcount：从hdfs读取数据，处理后写入到hbase(反向也可实现，此处略)
 */
public class WCRunner {
    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
        conf.set("hbase.zookeeper.quorum","node01,node02,node03");
        // 在Windows下执行需要设置，参考[hadoop.md#MapReduce测试代码](/_posts/bigdata/hadoop.md#MapReduce测试代码)
        // conf.set("mapreduce.app-submission.cross-platform","true");
        // conf.set("mapreduce.framework.name","local");

        // 创建job对象
        Job job = Job.getInstance(conf);
        job.setJarByClass(WCRunner.class);

        // 设置mapper类
        job.setMapperClass(WCMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        // 设置reduce类，此处不需要reduce，只要是向hbase写数据，因此只需要 TableMapReduceUtil.initTableReducerJob
        // job.setReducerClass();
        // TableMapReduceUtil.initTableMapperJob();
        // 需提前创建 hbase 表 wc: create 'wc', 'cf'
        TableMapReduceUtil.initTableReducerJob("wc", WCReducer.class, job,
                null,null,null,null,false);
        job.setOutputKeyClass(NullWritable.class);
        job.setOutputValueClass(Put.class);

        // 指定hdfs存储数据的目录
        FileInputFormat.addInputPath(job, new Path("/data/twc/input"));
        job.waitForCompletion(true);
    }
}
