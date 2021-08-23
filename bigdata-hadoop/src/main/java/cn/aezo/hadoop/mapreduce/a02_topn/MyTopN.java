package cn.aezo.hadoop.mapreduce.a02_topn;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 *
 * 找到每个月温度最高的两天，并翻译出城市名
 * 1.此方案在map阶段进行码表翻译(适合码表较小的情况)；当码表较大的情况，可以通过执行两次任务完成
 * 2.创建目录并上传文件
 * hdfs dfs -mkdir -p /data/topn/input
 * hdfs dfs -D dfs.blocksize=1048576 -put topn.txt /data/topn/input
 * hdfs dfs -mkdir -p /data/topn/dict
 * hdfs dfs -D dfs.blocksize=1048576 -put dict.txt /data/topn/dict
 * 3.在IDEA中设置Programe arguments=/data/topn/input /data/topn/output, 并启动此程序
 *
 * 数据如下：
 * 2021-6-1 05:21:23	1	39
 * 2021-5-21 12:02:02	3	33
 * 2021-6-1 21:24:24	1	38
 * 2021-6-2 09:29:17	2	31
 * 2020-3-11 02:56:28	3	18
 * 2020-4-23 12:47:19	1	22
 * 1970-8-23 11:51:05	2	23
 * 1970-8-8 17:39:11	1	32
 *
 * 字典如下(/data/topn/dict/dict.txt)，直接复制到linux可能会出现\t丢失：
 * 1   beijing
 * 2   shanghai
 * 3   guangzhou
 *
 * 执行后返回结果
 * 1970-8-8@beijing	32
 * 1970-8-23@shanghai	23
 * 2020-3-11@guangzhou	18
 * 2020-4-23@beijing	22
 * 2021-5-21@guangzhou	33
 * 2021-6-1@beijing	39
 * 2021-6-2@shanghai	31
 *
 * @author smalle
 * @since 2021/5/30
 */
public class MyTopN {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);
        conf.set("mapreduce.app-submission.cross-platform", "true");

        String[] other = new GenericOptionsParser(conf, args).getRemainingArgs();

        Job  job = Job.getInstance(conf);
        job.setJarByClass(MyTopN.class);
        job.setJobName("TopN");

        job.setJar("D:\\gitwork\\smjava\\hadoop\\target\\hadoop-0.0.1-SNAPSHOT.jar");
        // 客户端规划的时候将join的右表cache到mapTask出现的节点上
        job.addCacheFile(new Path("/data/topn/dict/dict.txt").toUri());

        //初学者，关注的是client端的代码梳理：因为把这块写明白了，其实你也就真的知道这个作业的开发原理；

        // mapTask
        // input output
        TextInputFormat.addInputPath(job,new Path(other[0]));
        Path outPath = new Path(other[1]);
        if(outPath.getFileSystem(conf).exists(outPath))  outPath.getFileSystem(conf).delete(outPath,true);
        TextOutputFormat.setOutputPath(job,outPath);

        // map
        job.setMapperClass(TMapper.class);
        job.setMapOutputKeyClass(TKey.class);
        job.setMapOutputValueClass(IntWritable.class);

        // partitioner 分区器，满足相同的key获得相同的分区号就可以
        job.setPartitionerClass(TPartitioner.class);
        // sortComparator 排序器，默认使用Map输出数据类型中的排序器
        job.setSortComparatorClass(TSortComparator.class);
        // combine 聚合器，在map阶段进行一次小的reduce(此阶段计算成本低时才定义)
        // job.setCombinerClass();

        // reduceTask
        // groupingComparator 分组器
        job.setGroupingComparatorClass(TGroupingComparator.class);
        // reduce
        job.setReducerClass(TReducer.class);

        // 提交任务并等待
        job.waitForCompletion(true);
    }


}
