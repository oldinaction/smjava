package cn.aezo.bigdata.lucene.spider;

import net.htmlparser.jericho.Element;
import net.htmlparser.jericho.HTMLElementName;
import net.htmlparser.jericho.Source;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.io.IOException;
import java.util.Collection;

@Service
public class WriterIndex {
    public static final String indexDir = "D:/GitRepositories/smjava/lucene/spider_index";
    public static final String dataDir = "D:/GitRepositories/smjava/lucene/qq";

    @Test
    public void writerIndex() {
        Directory dir;
        try {
            dir = FSDirectory.open(new File(indexDir));
            Analyzer an = new IKAnalyzer();
            IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_4_9, an);
            conf.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
            IndexWriter writer = new IndexWriter(dir, conf);

            RAMDirectory rdir = new RAMDirectory();
            IndexWriterConfig conf1 = new IndexWriterConfig(Version.LUCENE_4_9, an);
            conf1.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
            IndexWriter ramWriter = new IndexWriter(rdir,conf1);

            Collection<File> files = FileUtils.listFiles(new File(dataDir), TrueFileFilter.INSTANCE, TrueFileFilter.INSTANCE);
            int count = 0;
            for (File f :files) {
                HtmlBean bean = createBean(f);
                Document doc = new Document();
                if(bean == null) {
                    continue;
                }

                count ++;
                doc.add(new StringField("title", bean.getTitle(), Field.Store.YES));
                doc.add(new StringField("url", bean.getUrl(), Field.Store.YES));
                doc.add(new TextField("content", bean.getContent(), Field.Store.YES));
                ramWriter.addDocument(doc);

                // 将每50个文档索引写到内存，然后一次性移到硬盘
                if(count == 50) {
                    ramWriter.close();
                    writer.addIndexes(rdir);

                    rdir = new RAMDirectory();
                    IndexWriterConfig conf2 = new IndexWriterConfig(Version.LUCENE_4_9, an);
                    conf2.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
                    ramWriter = new IndexWriter(rdir,conf2);
                    count = 0;
                }
            }

            if(count > 0 && count < 50) {
                ramWriter.close();
                writer.addIndexes(rdir);
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 基于jericho解析html文件
     * @param file
     * @return
     */
    public static HtmlBean createBean(File file){
        HtmlBean hb = new HtmlBean();
        try {
            Source sc = new Source(file);
            Element element = sc.getFirstElement(HTMLElementName.TITLE);
            if(element == null) {
                return null;
            }

            hb.setTitle(element.getTextExtractor().toString());
            hb.setContent(sc.getTextExtractor().toString());
            hb.setUrl(file.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return hb;

    }
}
