package com.hhhy.core.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import org.ansj.lucene4.AnsjAnalysis;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;

import com.hhhy.common.utils.StopWordsUtils;
import com.hhhy.db.beans.Article;

public class FSIndexBuilder implements IndexBuilder {
    private static final Logger logger = Logger.getLogger(FSIndexBuilder.class);
    private Analyzer ansjHeightAnalyzer = new AnsjAnalysis();
    private String indexDir;
    private Directory directory;
    private IndexWriter iWriter;
    private Analyzer analyzer;

    public FSIndexBuilder(String indexDir) {
        this.indexDir = indexDir;
        try {
            this.directory = new SimpleFSDirectory(new File(indexDir));
            analyzer = new AnsjAnalysis(StopWordsUtils.getStopWords(), false);
            IndexWriterConfig ic = new IndexWriterConfig(Version.LUCENE_4_9,
                    analyzer);
            iWriter = new IndexWriter(directory, ic);
        } catch (IOException e) {
            logger.error(e.getMessage().replaceAll("\n", ";"));
        }
    }

    @Override
    public void addArticle(Article art) {
        String content = art.getContent();
        Document doc = new Document();
        Field f = new TextField("text", content, Field.Store.YES);
        doc.add(f);
        try {
            this.iWriter.addDocument(doc);
            iWriter.commit();
        } catch (IOException e) {
            logger.warn(e.getMessage());
        }
    }

    @Override
    public Article queryArticle(String queryStr) {
        try {
            IndexSearcher isearcher;
            // 查询索引
            DirectoryReader reader = DirectoryReader.open(directory);
            isearcher = new IndexSearcher(reader);
            QueryParser tq = new QueryParser(Version.LUCENE_4_9, "text",
                    ansjHeightAnalyzer);
            Query query = tq.parse(queryStr);
            System.out.println(query);
            TopDocs hits = isearcher.search(query, 5);
            System.out.println(queryStr + ":共找到" + hits.totalHits + "条记录!");
            for (int i = 0; i < hits.scoreDocs.length; i++) {
                int docId = hits.scoreDocs[i].doc;
                Document document = isearcher.doc(docId);
                // System.out.println(toHighlighter(ansjHeightAnalyzer, query,
                // document));
                System.out.println(document.get("text"));
            }
        } catch (IOException e) {
            logger.warn(e);
        } catch (ParseException e) {
            logger.warn(e);
        }
        return null;
    }

    private String toHighlighter(Analyzer analyzer, Query query, Document doc) {
        String field = "text";
        try {
            SimpleHTMLFormatter simpleHtmlFormatter = new SimpleHTMLFormatter(
                    "<font color=\"red\">", "</font>");
            Highlighter highlighter = new Highlighter(simpleHtmlFormatter,
                    new QueryScorer(query));
            TokenStream tokenStream1 = analyzer.tokenStream("text",
                    new StringReader(doc.get(field)));
            String highlighterStr = highlighter.getBestFragment(tokenStream1,
                    doc.get(field));
            return highlighterStr == null ? doc.get(field) : highlighterStr;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidTokenOffsetsException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException,
            IOException {
        for (int i = 0; i < 10; i++) {
            FSIndexBuilder indexBuilder = new FSIndexBuilder("D://index");
            indexBuilder.addArticless();
            indexBuilder.queryArticle("和谐社会");
            indexBuilder.close();
        }
    }

    public void close() throws IOException {
        iWriter.close();
    }

    public void addArticless() {
        Article art = new Article();
        art.setContent("和谐社会，全稱社会主义和谐社会，是中国共产党");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("构建社会主义和谐社会”概念的首次完整提出");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("马克思主义政党不懈追求的一种理想。");
        addArticle(art);
        // Thread.sleep(10000);
        art.setContent("社会主义和谐社会是人类孜孜以求的一种美好社会，马克");
        addArticle(art);
        // Thread.sleep(10000);
    }

}
