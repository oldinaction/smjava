package cn.aezo.hadoop.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.BlockLocation;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;

public class TestHDFS {

    public Configuration conf = null;
    public FileSystem fs = null;

    //C/S
    @Before
    public void conn() throws Exception {
        // 读取当前classpath下的core-site.xml、hdfs-site.xml配置
        conf = new Configuration(true);
        fs = FileSystem.get(conf);
        // fs = FileSystem.get(URI.create("hdfs://aezocn/"), conf, "test"); // 也可基于配置文件覆盖配置
    }

    @Test
    public void mkdir() throws Exception {
        Path dir = new Path("/idea-client");
        if(fs.exists(dir)) {
            fs.delete(dir,true);
        }
        fs.mkdirs(dir);
    }

    @Test
    public void upload() throws Exception {
        // 本地文件流
        BufferedInputStream input = new BufferedInputStream(new FileInputStream(new File("./data/hello.txt")));
        // 目标文件流
        Path outfile = new Path("/idea-client/hello-word.txt");
        FSDataOutputStream output = fs.create(outfile);
        IOUtils.copyBytes(input, output, conf,true);
    }

    @Test
    public void blocks() throws Exception {
        Path file = new Path("/bigdata/data"); // 一个1.8M的文件，并设定一个Block为1M
        FileStatus fss = fs.getFileStatus(file);

        // 获取文件块信息。第一个的内容为`hello hadoop 1...hello hadoop 5`，第二个的内容为`5773...hello hadoop 100000`
        BlockLocation[] blks = fs.getFileBlockLocations(fss, 0, fss.getLen());
        for (BlockLocation b : blks) {
            // 0,1048576,node02,node03
            // 1048576,840319,node02,node03
            System.out.println(b);
        }

        FSDataInputStream in = fs.open(file);
        // 设置偏移为1M，相当于从第二个Block开始读。因此多个客户端可设置不同的偏移来同时读取一个文件，最后合并
        //计算向数据移动后，期望的是分治，只读取自己关心（通过seek实现），同时具备距离的概念（优先和本地的DN获取数据--框架的默认机制）
        in.seek(1048576);
        System.out.println((char)in.readByte()); // 5
        System.out.println((char)in.readByte()); // 7
        System.out.println((char)in.readByte()); // 7
        // ...
    }

    @After
    public void close() throws Exception {
        fs.close();
    }
}
