package cn.aezo.bigdata.lucene.demo;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongField;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class WriterIndex {
    public static final String indexDir = System.getProperty("user.dir") + "/demo_index"; // 存放索引的文件夹
    public static final String dataDir = System.getProperty("user.dir") + "/qq"; // 数据文件夹

    @Test
    public void writerIndex() {
        try {
            // 将索引保存到硬盘中
            Directory dir = FSDirectory.open(new File(indexDir));
            // Directory directory = new RAMDirectory(); // 将索引保存到内存中

            // 默认分词器(只支持英文，中文需要中文分词器，如：IKAnalyzer2012_FF.jar)
            Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_4_9, analyzer);
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND); // 增量添加索引(之前的索引数据不会覆盖)

            // 索引生成器
            IndexWriter writer = new IndexWriter(dir, config);

            File fileData = new File(dataDir);
            // 列出目录下所有文件
            Collection<File> files = FileUtils.listFiles(fileData, TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            for(File  f : files) {
                // 文档
                Document doc = new Document();
                // 字段
                doc.add(new StringField("fileName", f.getAbsolutePath(), Field.Store.YES)); // 文件名
                doc.add(new TextField("content", FileUtils.readFileToString(f), Field.Store.YES)); // 文件内容
                doc.add(new LongField("lastModify", f.lastModified(), Field.Store.YES)); // 上次修改时间

                writer.addDocument(doc);
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
