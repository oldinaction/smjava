package cn.aezo.bigdata.lucene.demo;

import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.junit.Test;

import java.io.File;

public class SearchIndex {
    @Test
    public void search() {
        try {
            Directory dir = FSDirectory.open(new File(WriterIndex.indexDir));
            IndexReader reader = DirectoryReader.open(dir);
            IndexSearcher searcher = new IndexSearcher(reader);

            StandardAnalyzer standardAnalyzer = new StandardAnalyzer(Version.LUCENE_4_9);
            QueryParser qp = new QueryParser(Version.LUCENE_4_9, "content", standardAnalyzer);
            Query query = qp.parse("sitemap");
            TopDocs search = searcher.search(query, 10); // 获取前10个文档

            ScoreDoc[] scoreDocs = search.scoreDocs;
            for(ScoreDoc sc : scoreDocs) {
                int docId = sc.doc;
                Document document = reader.document(docId);
                System.out.println(document.get("fileName"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
