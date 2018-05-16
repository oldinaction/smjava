package cn.aezo.bigdata.lucene.spider;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wltea.analyzer.lucene.IKAnalyzer;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

// spring3不兼容jdk1.8，需要使用1.7及以下
// 先执行创建索引，然后全文检索
@Controller
public class LuceneController {
    @Autowired
    private WriterIndex index;

    @RequestMapping("/write.do")
    public String createIndex() {
        File file = new File(WriterIndex.indexDir);
        if(file.exists()) {
            file.delete();
            file.mkdirs();
        }

        index.writerIndex();

        return "index.jsp";
    }

    @RequestMapping("/search.do")
    public String search(String keywords, int num, Model model)throws Exception{
        if(keywords == null || "".equals(keywords))
            return "search.jsp";

        Directory dir = FSDirectory.open(new File(WriterIndex.indexDir));

        IKAnalyzer analyzer = new IKAnalyzer();
        MultiFieldQueryParser mq = new MultiFieldQueryParser(Version.LUCENE_4_9, new String[]{"title", "content"}, analyzer);
        Query query = mq.parse(keywords);

        IndexReader reader = DirectoryReader.open(dir);
        IndexSearcher searcher = new IndexSearcher(reader);

        TopDocs td = searcher.search(query, 10*num);
        ScoreDoc[] scoreDocs = td.scoreDocs;

        System.out.println(td.totalHits);

        int count = td.totalHits;
        PageUtil<HtmlBean> page = new PageUtil<HtmlBean>(num+"", 10+"", count);
        List<HtmlBean> list = new ArrayList<HtmlBean>();
        for (int i = (num-1)*10; i < Math.min(num*10, count) ; i++) {
            ScoreDoc sd = scoreDocs[i];
            int docId = sd.doc;
            Document document = reader.document(docId);

            HtmlBean hb = new HtmlBean();

            SimpleHTMLFormatter formatter = new SimpleHTMLFormatter("<font color=\"red\">","</font>");
            QueryScorer qs = new QueryScorer(query);
            Highlighter highlighter = new Highlighter(formatter, qs);
            String title = highlighter.getBestFragment(analyzer, "title", document.get("title"));
            String content = highlighter.getBestFragments(analyzer.tokenStream("content", document.get("content")), document.get("content"), 3, "...");

            hb.setContent(content);
            hb.setTitle(title);
            hb.setUrl(document.get("url"));
            list.add(hb);
        }

        page.setList(list);
        model.addAttribute("page", page);
        model.addAttribute("keywords", keywords);

        return "search.jsp";
    }
}
