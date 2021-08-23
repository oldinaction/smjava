package cn.aezo.hadoop.mapreduce.a03_fof;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.TextInputFormat;
import org.apache.hadoop.mapreduce.lib.output.TextOutputFormat;
import org.apache.hadoop.util.GenericOptionsParser;

/**
 * 简版好友推荐系统:
 * 1.直接好友不推荐，只推荐间接好友，间接好友越多越应该推荐，相当于算某个两个人的间接好友数
 * hdfs dfs -mkdir -p /data/fof/input
 * hdfs dfs -D dfs.blocksize=1048576 -put fof.txt /data/fof/input
 * 在IDEA中设置Programe arguments=/data/fof/input /data/fof/output, 并启动此程序
 *
 * 输入数据(第一个为当前用户，后面为当前用户的好友列表), /data/fof/input/fof.txt:
 * 张三 李四 王五 大牛 小红
 * 李四 张三 王五 大牛
 * 王五 张三 李四
 * 大牛 张三 李四 小红
 * 小红 张三 大牛 小明
 * 小明 小红 小强
 * 小强 小明
 *
 * 直接结果:
 * 小明-大牛	1
 * 小红-小强	1
 * 张三-小明	1
 * 李四-小红	2
 * 王五-大牛	2
 * 王五-小红	1
 *
 * @author smalle
 * @since 2021/5/30
 */
public class MyFof {
    public static void main(String[] args) throws Exception {

        Configuration conf = new Configuration(true);
        conf.set("mapreduce.app-submission.cross-platform","true");

        String[] other = new GenericOptionsParser(conf, args).getRemainingArgs();

        Job job = Job.getInstance(conf);
        job.setJarByClass(MyFof.class);
        job.setJobName("fof");
        job.setJar("D:\\gitwork\\smjava\\hadoop\\target\\hadoop-0.0.1-SNAPSHOT.jar");

        TextInputFormat.addInputPath(job, new Path(other[0]));
        Path outPath = new Path(other[1]);
        if(outPath.getFileSystem(conf).exists(outPath))  outPath.getFileSystem(conf).delete(outPath,true);
        TextOutputFormat.setOutputPath(job,outPath);

        job.setMapperClass(FMapper.class);
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);

        job.setReducerClass(FReducer.class);

        job.waitForCompletion(true);
    }
}
