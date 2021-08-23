package cn.aezo.hadoop.mapreduce.a01_word_count;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * 计算单词出现频率
 *
 * hdfs dfs -mkdir -p /data/twc/input
 * # for i in `seq 100000`;do echo "hello hadoop $i" >> data.txt ;done
 * hdfs dfs -D dfs.blocksize=1048576 -put data.txt /data/twc/input
 *
 * # 使用本地提交任务方式
 * # 编写相应代码，并打包成jar
 * # 在IDEA中设置Programe arguments=/data/twc/input /data/twc/output
 * # 执行main方法
 * # 到YARN后台查看执行结果
 *
 * @author smalle
 * @since 2021-05-30
 */
public class TestWordCount {

    public static void main(String[] args) throws Exception {
        Configuration conf = new Configuration(true);

        // hadoop command [genericOptions] [commandOptions]
        // eg: hadoop jar test.jar myTest -D name=test inpath outpath
        // args 包含2类参数: genericOptions commandOptions
        // 工具类会把-D类型的属性(genericOptions)直接set到conf，会留下commandOptions
        GenericOptionsParser parser = new GenericOptionsParser(conf, args);
        String[] remainingArgs = parser.getRemainingArgs();

        // 在本地运行MR程序，任务不会提交到YARN
        // conf.set("mapreduce.framework.name", "local");
        // windows上执行必须配置: 从而可得知文件分隔符
        conf.set("mapreduce.app-submission.cross-platform", "true");

        Job job = Job.getInstance(conf);
        // 在本地提交任务到YARN上需要，否则无需
        job.setJar("D:\\gitwork\\smjava\\hadoop\\target\\hadoop-0.0.1-SNAPSHOT.jar");
        job.setJarByClass(TestWordCount.class);
        job.setJobName("TestWordCount");

        TextInputFormat.addInputPath(job, new Path(remainingArgs[0]));
        Path outFile = new Path(remainingArgs[1]);
        if (outFile.getFileSystem(conf).exists(outFile)) {
            // 如果存在此目录则删除
            outFile.getFileSystem(conf).delete(outFile, true);
        }
        TextOutputFormat.setOutputPath(job, outFile);

        // 设定Map方法类
        job.setMapperClass(TestMapper.class);
        // 设置map方法执行完之后返回的KV类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
        // 设置Reduce方法类
        job.setReducerClass(TestReduce.class);

        // 默认Reduce个数为1；如果只做过滤，即只运行map方法，可设置为0
        // job.setNumReduceTasks(0);

        // 提交任务并等待
        job.waitForCompletion(true);

    }
}
