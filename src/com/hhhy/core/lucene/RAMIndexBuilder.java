package com.hhhy.core.lucene;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.ansj.lucene.util.PorterStemmer;
import org.ansj.lucene4.AnsjAnalysis;
import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.CorruptIndexException;
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
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.store.SimpleFSDirectory;
import org.apache.lucene.util.Version;
import org.nlpcn.commons.lang.util.IOUtil;

import com.hhhy.db.beans.Article;

public class RAMIndexBuilder implements IndexBuilder{
    private static final Logger logger = Logger.getLogger(RAMIndexBuilder.class);

    public static void main(String[] args) throws CorruptIndexException,
            LockObtainFailedException, IOException, ParseException {
        RAMIndexBuilder b = new RAMIndexBuilder();
    }

    @Override
    public void addArticle(Article art) {
        
    }

    @Override
    public Article queryArticle(String queryStr) {
        return null;
    }

    
}
